package com.jribes.itineraryservice.service.impl;

import java.util.LinkedList;
import com.jribes.itinerarlib.domain.City;
import com.jribes.itinerarlib.exception.IncorrectFormatCityException;
import com.jribes.itineraryservice.dijkstramodel.DijkstraAlgorithm;
import com.jribes.itineraryservice.dijkstramodel.Graph;
import com.jribes.itineraryservice.dijkstramodel.Vertex;
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
    Graph createdGraph = dijkstraService.generateDijkstraGraph(originCity.getCityName(),
        destinationCity.getCityName());

    DijkstraAlgorithm algorithm = new DijkstraAlgorithm(createdGraph);
    algorithm.execute(new Vertex(originCity.getCityName(), originCity.getCityName()));
    LinkedList<Vertex> path =
        algorithm.getPath(new Vertex(destinationCity.getCityName(), destinationCity.getCityName()));
    for (Vertex vertex : path) {
      logger.info("vertex = {}", vertex);
    }

    return ItineraryDetailResponse.createOkResponseFromVertexPath(path);
  }

}
