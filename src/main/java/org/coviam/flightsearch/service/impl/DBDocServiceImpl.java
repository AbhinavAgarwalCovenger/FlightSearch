package org.coviam.flightsearch.service.impl;

import org.coviam.flightsearch.document.DBDocument;
import org.coviam.flightsearch.document.RequestData;
import org.coviam.flightsearch.document.ResponseData;
import org.coviam.flightsearch.repository.DBDocRepo;
import org.coviam.flightsearch.service.DBDocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DBDocServiceImpl implements DBDocService {

    @Autowired
    DBDocRepo dbDocRepo;

    @Override
    public void saveData(RequestData requestData, List<ResponseData> responseData) {
        DBDocument dbDocument = new DBDocument();
        dbDocument.setRequestData(requestData);
        dbDocument.setResponseData(responseData);
        dbDocRepo.insert(dbDocument);
    }
}
