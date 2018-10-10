package com.jribes.itineraryservice.response.cityconnections;

public class CityConnectionDetail {

  private Long id;
  private String originCity;
  private String destinationCity;
  private Long departureTime;
  private Long arrivalTime;

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

  public Long getDepartureTime() {
    return departureTime;
  }

  public void setDepartureTime(Long departureTime) {
    this.departureTime = departureTime;
  }

  public Long getArrivalTime() {
    return arrivalTime;
  }

  public void setArrivalTime(Long arrivalTime) {
    this.arrivalTime = arrivalTime;
  }

  @Override
  public String toString() {
    return "CityConnectionDetail [id=" + id + ", originCity=" + originCity + ", destinationCity="
        + destinationCity + ", departureTime=" + departureTime + ", arrivalTime=" + arrivalTime
        + "]";
  }

}
