package org.coviam.flightsearch.dto;

import lombok.Data;
import org.coviam.flightsearch.document.FlightDetails;

import java.util.List;

@Data
public class ResponseDataDTO {
    private String grandTotal;
    private List<FlightDetails> flightDetails;
}
