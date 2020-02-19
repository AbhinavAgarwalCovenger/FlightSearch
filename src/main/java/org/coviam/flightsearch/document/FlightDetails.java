package org.coviam.flightsearch.document;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class FlightDetails {
    private String source;
    private String destination;
    private String departureTime;
    private String arrivalTime;
    private String duration;
}
