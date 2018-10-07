package com.jribes.travelroutesservice.exception;

@SuppressWarnings("serial")
public class IncorrectFormatCityException extends Exception {

  /**
   * @param message
   */
  public IncorrectFormatCityException(String cityName) {
    super(String.format(
        "City with name=%s is incorrectly foratted. City must have 3 capital letters", cityName));
  }

}
