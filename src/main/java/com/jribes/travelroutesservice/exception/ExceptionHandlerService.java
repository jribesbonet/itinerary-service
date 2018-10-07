package com.jribes.travelroutesservice.exception;

import com.jribes.travelroutesservice.response.TravelRouteServiceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerService {

  @ExceptionHandler(IncorrectFormatCityException.class)
  public ResponseEntity<TravelRouteServiceResponse> handleBadFormatCityNameException(
      final Exception exception) {
    return new ResponseEntity<TravelRouteServiceResponse>(TravelRouteServiceResponse
        .createTravelRouteServiceResponseWithNameException(exception.getMessage()),
        HttpStatus.BAD_REQUEST);

  }

  @ExceptionHandler(Throwable.class)
  public ResponseEntity<TravelRouteServiceResponse> handleGenericUnexpectedException(
      final Exception exception) {
    return new ResponseEntity<TravelRouteServiceResponse>(
        TravelRouteServiceResponse.createTravelRouteServiceResponseWithGenericError(),
        HttpStatus.BAD_REQUEST);

  }

}
