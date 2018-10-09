package com.jribes.itineraryservice.service;

import com.jribes.itinerarlib.exception.IncorrectFormatCityException;
import com.jribes.itineraryservice.dijkstramodel.Graph;

public interface DijkstraService {

  public Graph generateDijkstraGraph(String originCityName, String destinationCityName)
      throws IncorrectFormatCityException;

}
