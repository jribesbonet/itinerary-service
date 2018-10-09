package com.jribes.itineraryservice.response;

import java.io.Serializable;
import org.springframework.http.HttpStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
@ApiModel
public class ItineraryServiceResponse implements Serializable {

  private static final String OK_DESCRIPTION = "Travel routes correctly returned";
  private static final String INTERNAL_SERVER_ERROR_DESCRIPTION =
      "Oops, an error has occurred, try again later...";

  @ApiModelProperty(value = "Status of the response", example = "200", required = true)
  private Integer responseStatus;
  @ApiModelProperty(value = "Description of the response", example = "Correct response",
      required = true)
  private String responseDescription;
  @ApiModelProperty(value = "Itinerary detail")
  private ItineraryDetailResponse itineraryDetailResponse;

  private ItineraryServiceResponse() {

  }

  public static ItineraryServiceResponse createOkTravelRouteServiceResponse(
      ItineraryDetailResponse itineraryDetailResponse) {
    ItineraryServiceResponse travelRouteServiceResponse = new ItineraryServiceResponse();
    travelRouteServiceResponse.setResponseStatus(Integer.parseInt(HttpStatus.OK.toString()));
    travelRouteServiceResponse.setResponseDescription(OK_DESCRIPTION);
    travelRouteServiceResponse.setItineraryDetailResponse(itineraryDetailResponse);
    return travelRouteServiceResponse;
  }

  public static ItineraryServiceResponse createTravelRouteServiceResponseWithNameException(
      String errorDescription) {
    ItineraryServiceResponse travelRouteServiceResponse = new ItineraryServiceResponse();
    travelRouteServiceResponse
        .setResponseStatus(Integer.parseInt(HttpStatus.BAD_REQUEST.toString()));
    travelRouteServiceResponse.setResponseDescription(errorDescription);
    return travelRouteServiceResponse;
  }

  public static ItineraryServiceResponse createTravelRouteServiceResponseWithGenericError() {
    ItineraryServiceResponse travelRouteServiceResponse = new ItineraryServiceResponse();
    travelRouteServiceResponse
        .setResponseStatus(Integer.parseInt(HttpStatus.INTERNAL_SERVER_ERROR.toString()));
    travelRouteServiceResponse.setResponseDescription(INTERNAL_SERVER_ERROR_DESCRIPTION);
    return travelRouteServiceResponse;
  }

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

  public ItineraryDetailResponse getItineraryDetailResponse() {
    return itineraryDetailResponse;
  }

  public void setItineraryDetailResponse(ItineraryDetailResponse itineraryDetailResponse) {
    this.itineraryDetailResponse = itineraryDetailResponse;
  }

  @Override
  public String toString() {
    return "TravelRouteServiceResponse [responseStatus=" + responseStatus + ", responseDescription="
        + responseDescription + "]";
  }

}
