package com.jribes.travelroutesservice.response;

import java.io.Serializable;
import org.springframework.http.HttpStatus;
import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
public class TravelRouteServiceResponse implements Serializable {

  private static final String OK_DESCRIPTION = "Travel routes correctly returned";
  private static final String INTERNAL_SERVER_ERROR_DESCRIPTION =
      "Oops, an error has occurred, try again later...";

  @ApiModelProperty(value = "Status of the response", example = "200", required = true)
  private Integer responseStatus;
  @ApiModelProperty(value = "Description of the response", example = "Correct response",
      required = true)
  private String responseDescription;

  private TravelRouteServiceResponse() {

  }

  public static TravelRouteServiceResponse createOkTravelRouteServiceResponse() {
    TravelRouteServiceResponse travelRouteServiceResponse = new TravelRouteServiceResponse();
    travelRouteServiceResponse.setResponseStatus(Integer.parseInt(HttpStatus.OK.toString()));
    travelRouteServiceResponse.setResponseDescription(OK_DESCRIPTION);
    return travelRouteServiceResponse;
  }

  public static TravelRouteServiceResponse createTravelRouteServiceResponseWithNameException(
      String errorDescription) {
    TravelRouteServiceResponse travelRouteServiceResponse = new TravelRouteServiceResponse();
    travelRouteServiceResponse
        .setResponseStatus(Integer.parseInt(HttpStatus.BAD_REQUEST.toString()));
    travelRouteServiceResponse.setResponseDescription(errorDescription);
    return travelRouteServiceResponse;
  }

  public static TravelRouteServiceResponse createTravelRouteServiceResponseWithGenericError() {
    TravelRouteServiceResponse travelRouteServiceResponse = new TravelRouteServiceResponse();
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

  @Override
  public String toString() {
    return "TravelRouteServiceResponse [responseStatus=" + responseStatus + ", responseDescription="
        + responseDescription + "]";
  }

}
