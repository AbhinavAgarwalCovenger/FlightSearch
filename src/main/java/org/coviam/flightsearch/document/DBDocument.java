package org.coviam.flightsearch.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "searchData")
public class DBDocument {

    @Id
    private String id;

    private RequestData requestData;
    private List<ResponseData> responseData;
}
