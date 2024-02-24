//package com.example.springmongodb;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import com.mongodb.client.*;
//import com.mongodb.client.MongoDatabase;
//import org.bson.Document;
//
//@SpringBootApplication
//public class TestMongoDriver implements CommandLineRunner {
//
//    public static void main(String[] args) {
//        SpringApplication.run(TestMongoDriver.class, args);
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//         System.out.println("test my aplication");
//                String uri = "mongodb://127.0.0.1:27017";
//
//                // Create a MongoClient instance
//
//        try (MongoClient mongoClient = MongoClients.create(uri)) {
//                    // Get a reference to the "admin" database
//                    MongoDatabase adminDatabase = mongoClient.getDatabase("admin");
//
//                    // Enable the profiler
//                    //Document command = new Document("profile", 1).append("slowms", 50); // Set threshold to 50 milliseconds
//                    Document command = new Document("profile",2);
//                    adminDatabase.runCommand(command);
//
//                    System.out.println("Profiler enabled.");
//
//            // Insert a slow operation manually
//            adminDatabase.runCommand(new Document("profile", 2));
//            //adminDatabase.getCollection("students").find(new Document("test","tfinda")).first();
//           // adminDatabase.runCommand(new Document("profile", 0));
//            MongoCollection<Document> profileCollection = adminDatabase.getCollection("system.profile");
//
//                    MongoIterable<Document> iprof = profileCollection.find();
//
//                    for(Document profile: iprof){
//                        System.out.println(profile.toJson());
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//
//            }
//        }
