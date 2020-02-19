package org.coviam.flightsearch.dto;

import lombok.Data;
import java.util.List;

@Data
public class ApiResponse {

    private List<SearchData> data;
}
