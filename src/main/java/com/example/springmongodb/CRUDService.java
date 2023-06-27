package com.example.springmongodb;


import com.mongodb.MongoException;
import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.client.DistinctIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.*;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.BsonDocument;
import org.bson.BsonInt64;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gt;


@Service
public class CRUDService {
    Logger logger = LogManager.getLogger("CRUDService");

    public void updateOps(MongoCollection collection, MongoDatabase database) {
        Document query = new Document().append("title", "Cool Runnings 2");
        Bson updates = Updates.combine(
                Updates.set("runtime", 99),
                Updates.addToSet("genres", "Sports"),
                Updates.currentTimestamp("lastUpdated"));
        UpdateOptions options = new UpdateOptions().upsert(true);
        try {
            UpdateResult result = collection.updateOne(query, updates, options);
            System.out.println("Modified document count: " + result.getModifiedCount());
            System.out.println("Upserted id: " + result.getUpsertedId());
        } catch (MongoException me) {
            System.err.println("Unable to update due to an error: " + me);
        }

        //udpateMany
        Bson query1 = gt("num_mflix_comments", 50);
        Bson updates1 = Updates.combine(
                Updates.addToSet("genres", "Frequently Discussed"),
                Updates.currentTimestamp("lastUpdated"));
        try {
            UpdateResult result = collection.updateMany(query1, updates1);
            System.out.println("Modified document count: " + result.getModifiedCount());
        } catch (MongoException me) {
            System.err.println("Unable to update due to an error: " + me);
        }

        //Replace a document

        Bson query2 = eq("title", "Cool Runnings 2");
        Document replaceDocument = new Document().
                append("title", "50 Violins").
                append("fullplot", " A dramatization of the true story of Roberta Guaspari who co-founded the Opus 118 Harlem School of Music");
        ReplaceOptions opts = new ReplaceOptions().upsert(true);
        UpdateResult result = collection.replaceOne(query2, replaceDocument, opts);
        System.out.println("Modified- replaced document count: " + result.getModifiedCount());
        System.out.println("Upserted id: " + result.getUpsertedId()); // only contains a value when an upsert is performed

        //Delete a document
        Bson query3 = eq("title", "April1Vidudala");
        try {
            DeleteResult result3 = collection.deleteOne(query3);
            System.out.println("Deleted document count: " + result3.getDeletedCount());
        } catch (MongoException me) {
            System.err.println("Unable to delete due to an error: " + me);
        }

        //Bulkwrite
        try {
            BulkWriteResult result4 = collection.bulkWrite(
                    Arrays.asList(
                            new InsertOneModel<>(new Document("name", "A Sample Movie")),
                            new InsertOneModel<>(new Document("name", "Another Sample Movie")),
                            new InsertOneModel<>(new Document("name", "Yet Another Sample Movie")),
                            new UpdateOneModel<>(new Document("name", "A Sample Movie"),
                                    new Document("$set", new Document("name", "An Old Sample Movie")),
                                    new UpdateOptions().upsert(true)),
                            new DeleteOneModel<>(new Document("name", "Yet Another Sample Movie")),
                            new ReplaceOneModel<>(new Document("name", "Yet Another Sample Movie"),
                                    new Document("name", "The Other Sample Movie").append("runtime",  "42"))
                    ));
            System.out.println("Result statistics:" +
                    "\ninserted: " + result4.getInsertedCount() +
                    "\nupdated: " + result4.getModifiedCount() +
                    "\ndeleted: " + result4.getDeletedCount());
        } catch (MongoException me) {
            System.err.println("The bulk write operation failed due to an error: " + me);
        }

        //Count Documents
        CountOptions opts1 = new CountOptions().hintString("_id_");
        long numDocuments = collection.countDocuments(new BsonDocument(), opts1);
        System.out.println("number of documents "+ numDocuments);

        long estimatedCount = collection.estimatedDocumentCount();
        System.out.println("Estimated number of documents in the movies collection: " + estimatedCount);

        Bson query0 = eq("plot", "comedy");
        long matchingCount = collection.countDocuments(query0);
        System.out.println("Number of movies from Spain: " + matchingCount);

        //Distinct values

        try {
            DistinctIterable<String> docs = collection.distinct("title", Filters.eq("plot", "comedy"), String.class);
            MongoCursor<String> results = docs.iterator();
            while(results.hasNext()) {
                System.out.println(results.next());
            }
        } catch (MongoException me) {
            System.err.println("An error occurred: " + me);
        }

        //Get statistics of DB

        try {
            Bson command = new BsonDocument("dbStats", new BsonInt64(1));
            Document commandResult = database.runCommand(command);
            System.out.println("dbStats: " + commandResult.toJson());
        } catch (MongoException me) {
            System.err.println("An error occurred: " + me);
        }
        logger.info("logging end in CRUD service");
    }
}
