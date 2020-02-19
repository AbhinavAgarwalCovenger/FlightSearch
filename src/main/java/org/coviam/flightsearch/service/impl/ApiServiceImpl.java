package org.coviam.flightsearch.service.impl;

import org.coviam.flightsearch.document.FlightDetails;
import org.coviam.flightsearch.document.RequestData;
import org.coviam.flightsearch.document.ResponseData;
import org.coviam.flightsearch.dto.*;
import org.coviam.flightsearch.service.ApiService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ApiServiceImpl implements ApiService {

    @Override
    @Cacheable(value = "token",key = "#apiKey")
    public String getToken(String apiKey, String secret) {
        MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
        map.add("grant_type","client_credentials");
        map.add("client_id",apiKey);
        map.add("client_secret",secret);

        String uri = "https://test.api.amadeus.com/v1/security/oauth2/token";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        TokenDTO tokenDTO = restTemplate.postForObject(uri,new HttpEntity<>(map,httpHeaders),TokenDTO.class);
        String token = "Bearer "+tokenDTO.getAccess_token();
        return token;
    }

    @Override
    public URI getUri(String url, RequestData requestData) {
        requestData.setMax("2");
        Map<String,String> uriVariables = new HashMap<>();
        uriVariables.put("source",requestData.getOriginLocationCode());
        uriVariables.put("destination",requestData.getDestinationLocationCode());
        uriVariables.put("date",requestData.getDepartureDate());
        uriVariables.put("return",requestData.getReturnDate());
        uriVariables.put("adult",requestData.getAdults());
        uriVariables.put("max",requestData.getMax());

        UriComponentsBuilder uri = UriComponentsBuilder.fromUriString(url);
        URI finalUri = uri.build(uriVariables);
        return finalUri;
    }

    @Override
    public List<ResponseData> sendResponse(ApiResponse apiResponse) {
        List<ResponseData> responseDataList = new ArrayList<>();
        List<SearchData> searchDataList = apiResponse.getData();
        for (SearchData searchData:searchDataList){
            ResponseData responseData = new ResponseData();
            responseData.setGrandTotal(searchData.getPrice().getGrandTotal());

            List<Itineraries> itinerariesList = searchData.getItineraries();
            List<FlightDetails> flightDetailsList = new ArrayList<>();

            for (Itineraries itineraries : itinerariesList) {

                int size = itineraries.getSegments().size();
                FlightDetails flightDetails = new FlightDetails();

                flightDetails.setDuration(itineraries.getDuration());
                flightDetails.setSource(itineraries.getSegments().get(0)
                        .getDeparture().getIataCode());
                flightDetails.setDepartureTime(itineraries.getSegments().get(0)
                        .getDeparture().getAt());
                flightDetails.setDestination(itineraries.getSegments().get(size - 1)
                        .getArrival().getIataCode());
                flightDetails.setArrivalTime(itineraries.getSegments().get(size - 1)
                        .getArrival().getAt());
                flightDetailsList.add(flightDetails);
            }
            responseData.setFlightDetails(flightDetailsList);
            responseDataList.add(responseData);
        }
        return responseDataList;
    }
}
