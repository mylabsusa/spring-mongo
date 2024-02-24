package com.example.springmongodb.service;


import com.example.springmongodb.dao.MovieRepository;
import com.example.springmongodb.dao.PersonRepository;
import com.example.springmongodb.dto.Movie;

import com.example.springmongodb.dto.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CRUDService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private PersonRepository personRepository;

    public void addPerson(Person person){
        System.out.println("Adding person");
        personRepository.save(person);
    }

    public boolean deletePersonByAcctNumber(String accountnumber){
        System.out.println("account number "+accountnumber);
        Person person = personRepository.findByAccountNumber(accountnumber);
        personRepository.delete(person);
        return true;
    }


    public void addMovie(Movie movie){
        System.out.println("Movie to be added");
        movieRepository.save(movie);
    }

    public void deleteMovie(String title){
       Optional<Movie> movie1 = Optional.ofNullable(movieRepository.findByTitle(title));
        System.out.println("movie plot" + movie1.get().getPlot());
    }
//    public void updateMovie(){
//        Query query  = new Query();
//        query.addCriteria(Criteria.where("title").is("Visali!"));
//        Update update = new Update();
//        update.set("name", "telugu cinema");
//        UpdateResult result= mongoTemplate.upsert(query, update, Movie.class);
//        System.out.println("upsert value "  + result.getUpsertedId());
//   }

}

//    public void insertMovie(){
//        Movie newMovie = new Movie("horror", Arrays.asList("suspense","drama"),"Evil Dead!");
//        mongoTemplate.insert(newMovie);
//        System.out.println("dtabnase: " + mongoTemplate.getDb().getName());
//        System.out.println("Movie Inserted");
//    }
//
//    public void updateOps(MongoCollection collection, MongoDatabase database) {
//        Document query = new Document().append("title", "Cool Runnings 2");
//        Bson updates = Updates.combine(
//                Updates.set("runtime", 99),
//                Updates.addToSet("genres", "Sports"),
//                Updates.currentTimestamp("lastUpdated"));
//        UpdateOptions options = new UpdateOptions().upsert(true);
//        try {
//            UpdateResult result = collection.updateOne(query, updates, options);
//            System.out.println("Modified document count: " + result.getModifiedCount());
//            System.out.println("Upserted id: " + result.getUpsertedId());
//        } catch (MongoException me) {
//            System.err.println("Unable to update due to an error: " + me);
//        }
//
//        //udpateMany
//        Bson query1 = gt("num_mflix_comments", 50);
//        Bson updates1 = Updates.combine(
//                Updates.addToSet("genres", "Frequently Discussed"),
//                Updates.currentTimestamp("lastUpdated"));
//        try {
//            UpdateResult result = collection.updateMany(query1, updates1);
//            System.out.println("Modified document count: " + result.getModifiedCount());
//        } catch (MongoException me) {
//            System.err.println("Unable to update due to an error: " + me);
//        }
//
//        //Replace a document
//
//        Bson query2 = eq("title", "Cool Runnings 2");
//        Document replaceDocument = new Document().
//                append("title", "50 Violins").
//                append("fullplot", " A dramatization of the true story of Roberta Guaspari who co-founded the Opus 118 Harlem School of Music");
//        ReplaceOptions opts = new ReplaceOptions().upsert(true);
//        UpdateResult result = collection.replaceOne(query2, replaceDocument, opts);
//        System.out.println("Modified- replaced document count: " + result.getModifiedCount());
//        System.out.println("Upserted id: " + result.getUpsertedId()); // only contains a value when an upsert is performed
//
//        //Delete a document
//        Bson query3 = eq("title", "April1Vidudala");
//        try {
//            DeleteResult result3 = collection.deleteOne(query3);
//            System.out.println("Deleted document count: " + result3.getDeletedCount());
//        } catch (MongoException me) {
//            System.err.println("Unable to delete due to an error: " + me);
//        }
//
//        //Bulkwrite
//        try {
//            BulkWriteResult result4 = collection.bulkWrite(
//                    Arrays.asList(
//                            new InsertOneModel<>(new Document("name", "A Sample Movie")),
//                            new InsertOneModel<>(new Document("name", "Another Sample Movie")),
//                            new InsertOneModel<>(new Document("name", "Yet Another Sample Movie")),
//                            new UpdateOneModel<>(new Document("name", "A Sample Movie"),
//                                    new Document("$set", new Document("name", "An Old Sample Movie")),
//                                    new UpdateOptions().upsert(true)),
//                            new DeleteOneModel<>(new Document("name", "Yet Another Sample Movie")),
//                            new ReplaceOneModel<>(new Document("name", "Yet Another Sample Movie"),
//                                    new Document("name", "The Other Sample Movie").append("runtime",  "42"))
//                    ));
//            System.out.println("Result statistics:" +
//                    "\ninserted: " + result4.getInsertedCount() +
//                    "\nupdated: " + result4.getModifiedCount() +
//                    "\ndeleted: " + result4.getDeletedCount());
//        } catch (MongoException me) {
//            System.err.println("The bulk write operation failed due to an error: " + me);
//        }
//
//        //Count Documents
//        CountOptions opts1 = new CountOptions().hintString("_id_");
//        long numDocuments = collection.countDocuments(new BsonDocument(), opts1);
//        System.out.println("number of documents "+ numDocuments);
//
//        long estimatedCount = collection.estimatedDocumentCount();
//        System.out.println("Estimated number of documents in the movies collection: " + estimatedCount);
//
//        Bson query0 = eq("plot", "comedy");
//        long matchingCount = collection.countDocuments(query0);
//        System.out.println("Number of movies from Spain: " + matchingCount);
//
//        //Distinct values
//
//        try {
//            DistinctIterable<String> docs = collection.distinct("title", Filters.eq("plot", "comedy"), String.class);
//            MongoCursor<String> results = docs.iterator();
//            while(results.hasNext()) {
//                System.out.println(results.next());
//            }
//        } catch (MongoException me) {
//            System.err.println("An error occurred: " + me);
//        }
//
//        //Get statistics of DB
//
//        try {
//            Bson command = new BsonDocument("dbStats", new BsonInt64(1));
//            Document commandResult = database.runCommand(command);
//            System.out.println("dbStats: " + commandResult.toJson());
//        } catch (MongoException me) {
//            System.err.println("An error occurred: " + me);
//        }
//
//    }