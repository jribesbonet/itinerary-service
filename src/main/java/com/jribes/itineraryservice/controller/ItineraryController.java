package com.jribes.itineraryservice.controller;

import com.jribes.itinerarlib.domain.City;
import com.jribes.itinerarlib.exception.IncorrectFormatCityException;
import com.jribes.itineraryservice.exception.CityConnectionConnectionException;
import com.jribes.itineraryservice.response.ItineraryDetailResponse;
import com.jribes.itineraryservice.response.ItineraryServiceResponse;
import com.jribes.itineraryservice.service.ItineraryConnectionsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/route")
public class ItineraryController {

  private Logger logger = LoggerFactory.getLogger(ItineraryController.class);

  @Autowired
  private ItineraryConnectionsService itineraryConnectionsService;

  @GetMapping("/origin/{originName}/destination/{destinationName}")
  @ApiOperation(value = "Get the itinerary with less connection from origin to destination")
  public ResponseEntity<ItineraryServiceResponse> getRoutesFromOriginToDestination(
      @PathVariable String originName, @PathVariable String destinationName)
      throws IncorrectFormatCityException, CityConnectionConnectionException {

    City originCity = City.createCity().withCityName(originName);
    City destinationCity = City.createCity().withCityName(destinationName);

    logger.info("originCity -> {}", originCity);
    logger.info("destinationCity -> {}", destinationCity);

    ItineraryDetailResponse itineraryDetailResponse = itineraryConnectionsService
        .getConnectionsItineraryFromOriginToDestination(originCity, destinationCity);

    logger.info("itineraryDetailResponse --> {}", itineraryDetailResponse);

    return new ResponseEntity<ItineraryServiceResponse>(
        ItineraryServiceResponse.createOkTravelRouteServiceResponse(itineraryDetailResponse),
        HttpStatus.OK);
  }

}
