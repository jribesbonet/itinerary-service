package com.jribes.itineraryservice.response.cityconnections;

import java.util.List;

public class CityConnectionServiceResponse {

  private Integer responseStatus;
  private String responseDescription;
  private List<CityConnectionDetail> cityConnectionDetailList;

  public Integer getResponseStatus() {
    return responseStatus;
  }

  public void setResponseStatus(Integer responseStatus) {
    this.responseStatus = responseStatus;
  }

  public String getResponseDescription() {
    return responseDescription;
  }

  public void setResponseDescription(String responseDescription) {
    this.responseDescription = responseDescription;
  }

  public List<CityConnectionDetail> getCityConnectionResponseList() {
    return cityConnectionDetailList;
  }

  public void setCityConnectionResponseList(List<CityConnectionDetail> cityConnectionResponseList) {
    this.cityConnectionDetailList = cityConnectionResponseList;
  }

  @Override
  public String toString() {
    return "CityConnectionServiceResponse [responseStatus=" + responseStatus
        + ", responseDescription=" + responseDescription + ", cityConnectionResponseList="
        + cityConnectionDetailList + "]";
  }

}
