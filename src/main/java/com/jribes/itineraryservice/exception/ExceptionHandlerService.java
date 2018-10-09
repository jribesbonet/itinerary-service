package com.jribes.itineraryservice.exception;

import com.jribes.itinerarlib.exception.IncorrectFormatCityException;
import com.jribes.itineraryservice.response.ItineraryServiceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerService {

  @ExceptionHandler(IncorrectFormatCityException.class)
  public ResponseEntity<ItineraryServiceResponse> handleBadFormatCityNameException(
      final Exception exception) {
    return new ResponseEntity<ItineraryServiceResponse>(ItineraryServiceResponse
        .createTravelRouteServiceResponseWithNameException(exception.getMessage()),
        HttpStatus.BAD_REQUEST);

  }

  @ExceptionHandler(Throwable.class)
  public ResponseEntity<ItineraryServiceResponse> handleGenericUnexpectedException(
      final Exception exception) {
    return new ResponseEntity<ItineraryServiceResponse>(
        ItineraryServiceResponse.createTravelRouteServiceResponseWithGenericError(),
        HttpStatus.BAD_REQUEST);

  }

}
