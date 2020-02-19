package org.coviam.flightsearch.controller;

import org.coviam.flightsearch.document.RequestData;
import org.coviam.flightsearch.document.ResponseData;
import org.coviam.flightsearch.dto.ApiResponse;
import org.coviam.flightsearch.dto.RequestDataDTO;
import org.coviam.flightsearch.dto.ResponseDataDTO;
import org.coviam.flightsearch.service.ApiService;
import org.coviam.flightsearch.service.DBDocService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/search")
public class FlightSearchController {

    @Autowired
    DBDocService dbDocService;

    @Autowired
    ApiService apiService;

    final String API_KEY = "lsEfyhlUnzk3sTY0MMi27EgC7aG59fEA";
    final String API_SECRET = "GjTEsqu5SCgAGcGA";

    @PostMapping("/flight")
    public ResponseEntity<List<ResponseData>> getFlights(@RequestBody RequestDataDTO requestDataDTO){
        RequestData requestData = new RequestData();
        BeanUtils.copyProperties(requestDataDTO,requestData);

        String uri = "https://test.api.amadeus.com/v2/shopping/flight-offers?originLocationCode={source}" +
                "&destinationLocationCode={destination}&departureDate={date}&returnDate={return}&adults={adult}&max={max}";
        String token = apiService.getToken(API_KEY,API_SECRET);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization",token);
        HttpEntity request = new HttpEntity(httpHeaders);
        URI url = apiService.getUri(uri,requestData);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ApiResponse> apiResponse = restTemplate
                .exchange(url,HttpMethod.GET,request,ApiResponse.class);

        List<ResponseData> responseDataList = apiService.sendResponse(apiResponse.getBody());
        dbDocService.saveData(requestData,responseDataList);
//        List<ResponseDataDTO> responseDataDTOList = new ArrayList<>();
//        BeanUtils.copyProperties(responseDataList,responseDataDTOList);
        return new ResponseEntity<>(responseDataList,HttpStatus.OK);
    }
}
