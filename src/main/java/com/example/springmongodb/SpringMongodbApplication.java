package com.example.springmongodb;

import com.mongodb.MongoException;
import com.mongodb.client.result.InsertManyResult;
import org.bson.types.ObjectId;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.bson.Document;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

@SpringBootApplication
public class SpringMongodbApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringMongodbApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Replace the placeholder with your MongoDB deployment's connection string
        String uri = "mongodb://localhost:27017/";
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        System.out.println("Insert movies into database");
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("sample_mflix");
            MongoCollection<Document> collection = database.getCollection("movies");
            List<Document> movieList = Arrays.asList(
                    new Document().append("title", "Short Circuit 3"),
                    new Document().append("title", "The Lego Frozen Movie"),
                          new Document().append("title", "Back to the Future"));
            try {
                InsertManyResult result = collection.insertMany(movieList);
                System.out.println("movies insertion complete");

                System.out.println("Inserted document ids: " + result.getInsertedIds());
            } catch (MongoException me) {
                System.err.println("Unable to insert due to an error: " + me);
            }

            Document doc = collection.find().first();
            if (doc != null) {
                System.out.println(doc.toJson());
            } else {
                System.out.println("No matching documents found.");
            }

             doc = collection.find(eq("title", "The Lego Frozen Movie")).first();
            if (doc != null) {
                System.out.println(doc.toJson());
            } else {
                System.out.println("No matching documents found.");
            }
            //Inserting POJO's into the Mongo collection
            System.out.println("read movies collection from database");
            MongoCollection<Movie> mCollection  = database.getCollection("movies", Movie.class).withCodecRegistry(pojoCodecRegistry);;
            System.out.println("movie collection retrieved");

            System.out.println(" Insert 2 new movies into the collection");
            Movie newMovie = new Movie("horror", Arrays.asList("suspense","drama"),"Visali");
            mCollection.insertOne(newMovie);
            newMovie = new Movie("comedy", Arrays.asList("comedy","drama"),"April1Vidudala");
            mCollection.insertOne(newMovie);
            System.out.println("insertion complete");

            //return all documents , movie pojo;s
            List<Movie> movies = new ArrayList<Movie>();
            mCollection.find().into(movies);
            System.out.println(movies);

            //Java Records
            MongoCollection<DataRecord> drCollection = database.getCollection("data_storage_devices", DataRecord.class);
            // insert the record
            drCollection.insertOne(new DataRecord("2TB SSD", 1.71, "samsung"));
            drCollection.insertOne(new DataRecord("1TB SSD", 0.9, "kingston"));

            List<DataRecord> records = new ArrayList<DataRecord>();
            drCollection.find().into(records);
            records.forEach(System.out::println);

            //Java Records using annotations.
            MongoCollection<NetworkDeviceRecord> ndrCol = database.getCollection("network_devices", NetworkDeviceRecord.class);
            // insert the record
            String deviceId = new ObjectId().toHexString();
            ndrCol.insertOne(new NetworkDeviceRecord(deviceId, "Enterprise Wi-fi", "router"));

            // return all documents in the collection as records
            List<NetworkDeviceRecord> recds = new ArrayList<NetworkDeviceRecord>();
            ndrCol.find().into(recds);
            records.forEach(System.out::println);


        }
    }
}
