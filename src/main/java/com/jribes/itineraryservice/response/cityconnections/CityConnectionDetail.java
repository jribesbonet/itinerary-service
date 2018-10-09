package com.jribes.itineraryservice.response.cityconnections;

public class CityConnectionDetail {

  private Long id;
  private String originCity;
  private String destinationCity;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getOriginCity() {
    return originCity;
  }

  public void setOriginCity(String originCity) {
    this.originCity = originCity;
  }

  public String getDestinationCity() {
    return destinationCity;
  }

  public void setDestinationCity(String destinationCity) {
    this.destinationCity = destinationCity;
  }

  @Override
  public String toString() {
    return "CityConnectionResponse [id=" + id + ", originCity=" + originCity + ", destinationCity="
        + destinationCity + "]";
  }

}
