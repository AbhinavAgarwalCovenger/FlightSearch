package org.coviam.flightsearch.dto;

import lombok.Data;

@Data
public class FLightDetailsDTO {
    private String source;
    private String destination;
    private String departureTime;
    private String arrivalTime;
    private String duration;
}
