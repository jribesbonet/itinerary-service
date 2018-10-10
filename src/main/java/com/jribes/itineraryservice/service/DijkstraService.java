package com.jribes.itineraryservice.service;

import com.jribes.itinerarlib.dijkstra.Graph;
import com.jribes.itinerarlib.exception.IncorrectFormatCityException;

public interface DijkstraService {

  public Graph generateDijkstraGraph(String originCityName, String destinationCityName)
      throws IncorrectFormatCityException;

  public Graph changeAllGraphDistancesToOne(Graph graph);

}
