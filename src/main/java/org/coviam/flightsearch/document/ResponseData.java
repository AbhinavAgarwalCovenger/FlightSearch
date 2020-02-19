package org.coviam.flightsearch.document;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class ResponseData {

    private String grandTotal;
    private List<FlightDetails> flightDetails;
}
