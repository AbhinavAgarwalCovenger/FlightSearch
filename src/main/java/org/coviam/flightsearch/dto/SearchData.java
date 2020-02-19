package org.coviam.flightsearch.dto;

import lombok.Data;
import java.util.List;

@Data
public class SearchData {

    private List<Itineraries> itineraries;
    private Price price;
}
