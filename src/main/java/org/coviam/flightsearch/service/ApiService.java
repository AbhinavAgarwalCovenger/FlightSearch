package org.coviam.flightsearch.service;

import org.coviam.flightsearch.document.RequestData;
import org.coviam.flightsearch.document.ResponseData;
import org.coviam.flightsearch.dto.ApiResponse;

import java.net.URI;
import java.util.List;

public interface ApiService {

    URI getUri(String url, RequestData requestData);
    List<ResponseData> sendResponse(ApiResponse apiResponse);
    String getToken(String apiKey, String secret);
}
