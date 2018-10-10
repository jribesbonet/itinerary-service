package com.jribes.itineraryservice.response;

import java.io.Serializable;
import java.util.List;
import com.jribes.itinerarlib.dijkstra.Vertex;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
@ApiModel
public class ItineraryDetailResponse implements Serializable {

  @ApiModelProperty(value = "Itinerary with less time", example = "BCN MAD HSK IBZ")
  private String lessTimeItinerary;
  @ApiModelProperty(value = "Itinerary with less connections", example = "BCN ZAZ IBZ")
  private String lessConnectionsItinerary;

  public static ItineraryDetailResponse createOkResponseFromVertexPath(
      List<Vertex> pathWithLessTime, List<Vertex> pathWithLessConnections) {
    ItineraryDetailResponse response = new ItineraryDetailResponse();

    response.lessTimeItinerary = "";
    for (Vertex vertex : pathWithLessTime) {
      response.lessTimeItinerary += vertex.getName() + " ";
    }
    response.lessTimeItinerary = response.lessTimeItinerary.trim();

    response.lessConnectionsItinerary = "";
    for (Vertex vertex : pathWithLessConnections) {
      response.lessConnectionsItinerary += vertex.getName() + " ";
    }
    response.lessConnectionsItinerary = response.lessConnectionsItinerary.trim();

    return response;

  }

  private ItineraryDetailResponse() {}

  public String getPathWithLessTime() {
    return lessTimeItinerary;
  }

  public void setPathWithLessTime(String pathWithLessTime) {
    this.lessTimeItinerary = pathWithLessTime;
  }

  public String getPathWithLessConnections() {
    return lessConnectionsItinerary;
  }

  public void setPathWithLessConnections(String pathWithLessConnections) {
    this.lessConnectionsItinerary = pathWithLessConnections;
  }

  @Override
  public String toString() {
    return "ItineraryDetailResponse [pathWithLessTime=" + lessTimeItinerary
        + ", pathWithLessConnections=" + lessConnectionsItinerary + "]";
  }

}
