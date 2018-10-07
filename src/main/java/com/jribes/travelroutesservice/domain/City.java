package com.jribes.travelroutesservice.domain;

import com.jribes.travelroutesservice.exception.IncorrectFormatCityException;
import org.apache.commons.lang3.StringUtils;

public class City {

  private String cityName;

  private City() {}

  public static City createCity() {
    return new City();
  }

  /**
   * 
   * @param cityName Name of the city
   * @return return the domain city object
   * @throws IncorrectFormatCityException
   */
  public City withCityName(String cityName) throws IncorrectFormatCityException {
    if (StringUtils.isEmpty(cityName) || !cityName.matches("[A-Z]{3}")) {
      throw new IncorrectFormatCityException(cityName);
    }
    this.cityName = cityName;
    return this;
  }

  public String getCityName() {
    return cityName;
  }

  @Override
  public String toString() {
    return "City [cityName=" + cityName + "]";
  }

}
