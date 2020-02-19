package org.coviam.flightsearch.repository;

import org.coviam.flightsearch.document.DBDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DBDocRepo extends MongoRepository<DBDocument, String> {

}
