package com.jribes.itineraryservice.service.impl;

import java.util.LinkedList;
import com.jribes.itinerarlib.dijkstra.DijkstraAlgorithm;
import com.jribes.itinerarlib.dijkstra.Graph;
import com.jribes.itinerarlib.dijkstra.Vertex;
import com.jribes.itinerarlib.domain.City;
import com.jribes.itinerarlib.exception.IncorrectFormatCityException;
import com.jribes.itineraryservice.exception.CityConnectionConnectionException;
import com.jribes.itineraryservice.response.ItineraryDetailResponse;
import com.jribes.itineraryservice.service.ItineraryConnectionsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItineraryConnectionsServiceImpl implements ItineraryConnectionsService {

  private Logger logger = LoggerFactory.getLogger(ItineraryConnectionsServiceImpl.class);

  @Autowired
  private DijkstraServiceImpl dijkstraService;

  @Override
  public ItineraryDetailResponse getConnectionsItineraryFromOriginToDestination(City originCity,
      City destinationCity) throws IncorrectFormatCityException, CityConnectionConnectionException {

    logger.info("Getting Less connections itinerary from {} to {}", originCity.getCityName(),
        destinationCity.getCityName());

    logger.info("Generate Dijkstra Graph");
    // Create the graph querying the city connections servjce,
    Graph createdGraph = dijkstraService.generateDijkstraGraph(originCity.getCityName(),
        destinationCity.getCityName());

    // Executing Dijkstra algorithm to find the itinerary with less time
    DijkstraAlgorithm algorithm = new DijkstraAlgorithm(createdGraph);
    algorithm.execute(new Vertex(originCity.getCityName(), originCity.getCityName()));

    // Get less time path
    LinkedList<Vertex> lessTimeItineraryPath =
        algorithm.getPath(new Vertex(destinationCity.getCityName(), destinationCity.getCityName()));
    logger.info("Less time path");
    for (Vertex vertex : lessTimeItineraryPath) {
      logger.info("vertex = {}", vertex);
    }

    // Change distance from every edge in graph to 1 in order to get the itinerary with less
    // connections
    createdGraph = dijkstraService.changeAllGraphDistancesToOne(createdGraph);
    // Executing Dijkstra algorithm to find the itinerary with less connections
    algorithm = new DijkstraAlgorithm(createdGraph);
    algorithm.execute(new Vertex(originCity.getCityName(), originCity.getCityName()));

    // Get less connections path
    LinkedList<Vertex> lessConnectionsItineraryPath =
        algorithm.getPath(new Vertex(destinationCity.getCityName(), destinationCity.getCityName()));
    logger.info("Less connections path");
    for (Vertex vertex : lessConnectionsItineraryPath) {
      logger.info("vertex = {}", vertex);
    }

    return ItineraryDetailResponse.createOkResponseFromVertexPath(lessTimeItineraryPath,
        lessConnectionsItineraryPath);
  }

}
