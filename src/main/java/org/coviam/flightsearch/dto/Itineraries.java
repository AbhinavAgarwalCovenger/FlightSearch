package org.coviam.flightsearch.dto;

import lombok.Data;
import java.util.List;

@Data
public class Itineraries {

    private String duration;
    private List<Segments> segments;
}
