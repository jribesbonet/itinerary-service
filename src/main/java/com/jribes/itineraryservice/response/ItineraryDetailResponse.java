package com.jribes.itineraryservice.response;

import java.io.Serializable;
import java.util.List;
import com.jribes.itineraryservice.dijkstramodel.Vertex;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
@ApiModel
public class ItineraryDetailResponse implements Serializable {

  @ApiModelProperty(value = "Itinerary name", example = "BCN MAD IBZ")
  private String name;

  public static ItineraryDetailResponse createFakeResponse() {
    ItineraryDetailResponse response = new ItineraryDetailResponse();
    response.setName("BCN-BIL-IBZ");
    return response;
  }

  public static ItineraryDetailResponse createOkResponseFromVertexPath(List<Vertex> path) {
    ItineraryDetailResponse response = new ItineraryDetailResponse();
    response.name = "";
    for (Vertex vertex : path) {
      response.name += vertex.getName() + " ";
    }
    response.name = response.name.trim();
    return response;

  }

  private ItineraryDetailResponse() {}


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "ItineraryDetailResponse [name=" + name + "]";
  }

}
