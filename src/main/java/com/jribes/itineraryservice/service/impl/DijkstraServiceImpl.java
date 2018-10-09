package com.jribes.itineraryservice.service.impl;

import java.util.ArrayList;
import java.util.List;
import com.jribes.itinerarlib.exception.IncorrectFormatCityException;
import com.jribes.itineraryservice.dijkstramodel.Edge;
import com.jribes.itineraryservice.dijkstramodel.Graph;
import com.jribes.itineraryservice.dijkstramodel.Vertex;
import com.jribes.itineraryservice.feignproxy.CityConnectionsProxy;
import com.jribes.itineraryservice.response.cityconnections.CityConnectionDetail;
import com.jribes.itineraryservice.response.cityconnections.CityConnectionServiceResponse;
import com.jribes.itineraryservice.service.DijkstraService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DijkstraServiceImpl implements DijkstraService {

  private Logger logger = LoggerFactory.getLogger(DijkstraServiceImpl.class);

  @Autowired
  private CityConnectionsProxy proxy;

  @Override
  public Graph generateDijkstraGraph(String originCityName, String destinationCityName)
      throws IncorrectFormatCityException {

    // Create graph, vertex and edges structures
    List<Vertex> vertexes = new ArrayList<>();
    List<Edge> edges = new ArrayList<>();

    // Nodes that had already been queried. Important to avoid infinite loops
    List<String> queriedNodes = new ArrayList<>();
    // Vertex that has been saved inside VertexList
    List<String> storedVertex = new ArrayList<>();

    // Check if the destination is a reacheble destination
    ResponseEntity<CityConnectionServiceResponse> destinationCityConnectionsResponseEntity =
        proxy.getAllConnectionsByDestinationCity(destinationCityName);
    if (destinationCityConnectionsResponseEntity.getStatusCode() == HttpStatus.OK
        && !destinationCityConnectionsResponseEntity.getBody().getCityConnectionResponseList()
            .isEmpty()) {
      // Recursive method call to create the whole with the info returned from the city connections
      // microservice
      createGraphRecursively(vertexes, edges, originCityName, destinationCityName, queriedNodes,
          storedVertex);
    } else {
      logger.info("Graph creation not required, destination city={} not reachable from any city",
          destinationCityName);
    }

    logger.info("queriedNodes --> {}", queriedNodes);
    logger.info("vertexes --> {}", vertexes);
    logger.info("edges --> {}", edges);

    return new Graph(vertexes, edges);
  }

  private void createGraphRecursively(List<Vertex> vertexes, List<Edge> edges,
      String originCityName, String destinationCityName, List<String> queriedNodes,
      List<String> storedVertex) throws IncorrectFormatCityException {

    if (!queriedNodes.contains(originCityName)) {
      logger.info("Node {} not present inside queriedNodes structure", originCityName);

      // Get adjacent nodes from origin one
      ResponseEntity<CityConnectionServiceResponse> cityConnectionsResponseEntity =
          proxy.getAllConnectionsByOriginCity(originCityName);

      // Check if the response of City Connection MS is correct
      if (cityConnectionsResponseEntity.getStatusCode() == HttpStatus.OK) {

        CityConnectionServiceResponse cityConnectionsResponse =
            cityConnectionsResponseEntity.getBody();

        // Create new node with origin name
        Vertex originVertex = new Vertex(originCityName, originCityName);
        // Add vertex to the vertexes list
        if (!storedVertex.contains(originCityName)) {
          vertexes.add(originVertex);
          // Add to storedVertex to do not save it again
          storedVertex.add(originCityName);
          logger.info("Vertex {} created and added inside queriedNodes structure", originCityName);
        }
        // Add node to list of already queried nodes
        queriedNodes.add(originCityName);

        // Iterate over city connection
        List<CityConnectionDetail> cityConnectionResponseList =
            cityConnectionsResponse.getCityConnectionResponseList();
        for (CityConnectionDetail cityConnectionDetail : cityConnectionResponseList) {
          // Create new node with destination and add it to the origin node.
          Long distance = 1L;
          Vertex destinationVertex = new Vertex(cityConnectionDetail.getDestinationCity(),
              cityConnectionDetail.getDestinationCity());
          if (!storedVertex.contains(cityConnectionDetail.getDestinationCity())) {
            vertexes.add(destinationVertex);
            // Add to storedVertex to do not save it again
            storedVertex.add(cityConnectionDetail.getDestinationCity());
            logger.info("Vertex {} created and added inside queriedNodes structure",
                cityConnectionDetail.getDestinationCity());
          }

          // Create edge between two vertex
          Edge edge = new Edge(originCityName + "-" + cityConnectionDetail.getDestinationCity(),
              originVertex, destinationVertex, distance);
          // Add the edge to the edges list
          edges.add(edge);
          logger.info("Adding edge from {} to {} into the edges list", originVertex.getName(),
              destinationVertex.getName());

          // before continue adding the next edge at same level, let's call recursively to check
          // which
          // connections have this destination.
          // if the destination matches with the destination we want to reach it's not necessary to
          // contnue colling it recursivelly.
          if (!cityConnectionDetail.getDestinationCity().equals(destinationCityName)) {

            logger.info("Making recursive call with {} - {}",
                cityConnectionDetail.getDestinationCity(), destinationCityName);
            createGraphRecursively(vertexes, edges, cityConnectionDetail.getDestinationCity(),
                destinationCityName, queriedNodes, storedVertex);
          } else {
            logger.info("Recursive call not necessary, origin and destination = {}",
                cityConnectionDetail.getDestinationCity());
          }
        }

      } else {
        logger.info(
            "ResponseCode from HTTP Call incorrect, skipping this node construction, code={}",
            cityConnectionsResponseEntity.getStatusCode());
      }
    } else {
      logger.warn("graph already contains node={}", originCityName);
    }
  }

}
