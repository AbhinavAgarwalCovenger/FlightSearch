package org.coviam.flightsearch.dto;

import lombok.Data;

@Data
public class RequestDataDTO {
    private String originLocationCode;
    private String destinationLocationCode;
    private String departureDate;
    private String returnDate;
    private String adults;
    private String max;
}
