package com.jribes.travelroutesservice.controller;

import com.jribes.travelroutesservice.domain.City;
import com.jribes.travelroutesservice.exception.IncorrectFormatCityException;
import com.jribes.travelroutesservice.response.TravelRouteServiceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/route")
public class TravelRoutesController {

  private Logger logger = LoggerFactory.getLogger(TravelRoutesController.class);

  @GetMapping("/origin/{originName}/destination/{destinationName}")
  public ResponseEntity<TravelRouteServiceResponse> getRoutesFromOriginToDestination(
      @PathVariable String originName, @PathVariable String destinationName)
      throws IncorrectFormatCityException {

    City originCity = City.createCity().withCityName(originName);
    City destinationCity = City.createCity().withCityName(destinationName);

    logger.info("originCity -> {}", originCity);
    logger.info("destinationCity -> {}", destinationCity);

    return new ResponseEntity<TravelRouteServiceResponse>(
        TravelRouteServiceResponse.createOkTravelRouteServiceResponse(), HttpStatus.OK);
  }

}
