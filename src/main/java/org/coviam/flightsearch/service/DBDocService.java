package org.coviam.flightsearch.service;

import org.coviam.flightsearch.document.RequestData;
import org.coviam.flightsearch.document.ResponseData;

import java.util.List;

public interface DBDocService {
    public void saveData(RequestData requestData, List<ResponseData> responseData);
}
