package com.jribes.itineraryservice.feignproxy;

import com.jribes.itinerarlib.exception.IncorrectFormatCityException;
import com.jribes.itineraryservice.response.cityconnections.CityConnectionServiceResponse;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "city-connections-service")
@RibbonClient(name = "city-connections-service")
@RequestMapping("/cityconnections")
public interface CityConnectionsProxy {

  @GetMapping("/origin/{originCityName}")
  public ResponseEntity<CityConnectionServiceResponse> getAllConnectionsByOriginCity(
      @PathVariable("originCityName") String originCityName) throws IncorrectFormatCityException;

  @GetMapping("/destination/{destinationCityName}")
  public ResponseEntity<CityConnectionServiceResponse> getAllConnectionsByDestinationCity(
      @PathVariable("destinationCityName") String destinationCityName)
      throws IncorrectFormatCityException;

}
