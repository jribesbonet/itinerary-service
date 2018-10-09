package com.jribes.itineraryservice.service;

import com.jribes.itinerarlib.domain.City;
import com.jribes.itinerarlib.exception.IncorrectFormatCityException;
import com.jribes.itineraryservice.exception.CityConnectionConnectionException;
import com.jribes.itineraryservice.response.ItineraryDetailResponse;

public interface ItineraryConnectionsService {

  public ItineraryDetailResponse getConnectionsItineraryFromOriginToDestination(City originCity,
      City destinationCity) throws IncorrectFormatCityException, CityConnectionConnectionException;

}
