package com.example.springmongodb;

import com.example.springmongodb.dao.MovieRepository;
import com.example.springmongodb.dto.Movie;
import com.example.springmongodb.dto.Person;
import com.example.springmongodb.service.CRUDService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Arrays;

import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@SpringBootApplication
public class SpringMongodbApplication implements CommandLineRunner {
   @Autowired
    private CRUDService crudService;

   // private static final Logger logger = LoggerFactory.getLogger();
   public static void main(String[] args) {
        SpringApplication.run(SpringMongodbApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //Initialize logger
        //logger.info("Current Working Directory: " + System.getProperty("user.dir"));

        System.out.println("caling template from crud service ...");
        Movie movie = new Movie();
        movie.setTitle("2024 movie");
        movie.setPlot("NEW PLOT");
        movie.setGenres(Arrays.asList("Horror","Thriller"));
//        crudService.insertMovie();
//
//        crudService.updateMovie();
//
        //crudService.addMovie(movie);
        crudService.deleteMovie("2024 movie");

        Person person = new Person("1","peter","parker", "12345" );

        //crudService.addPerson(person);
        //movieRepository.save(movie);

        crudService.deletePersonByAcctNumber("12345");

    }
}

// Replace the placeholder with your MongoDB deployment's connection string
//        String uri = "mongodb://localhost:27017/";
//        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
//        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
//        System.out.println("Insert movies into database");
//        try (MongoClient mongoClient = MongoClients.create(uri)) {
//            MongoDatabase database = mongoClient.getDatabase("samplemoviedb");
//            MongoCollection<Document> collection = database.getCollection("movies");
//            List<Document> movieList = Arrays.asList(
//                    new Document().append("title", "Short Circuit 3"),
//                    new Document().append("title", "The Lego Frozen Movie"),
//                          new Document().append("title", "Back to the Future"));
//            //InsertManyResult
//            try {
//                InsertManyResult result = collection.insertMany(movieList);
//                System.out.println("movies insertion complete");
//
//                System.out.println("Inserted document ids: " + result.getInsertedIds());
//            } catch (MongoException me) {
//                System.err.println("Unable to insert due to an error: " + me);
//            }
//
//            //InsertOneResult
//            InsertOneResult movie1=  collection.insertOne(new Document()
//                .append("_id", new ObjectId())
//                .append("title", "MIB")
//                .append("genres", Arrays.asList("Original", "Action")));
//
//            Document doc = collection.find().first();
//            if (doc != null) {
//                System.out.println(doc.toJson());
//            } else {
//                System.out.println("No matching documents found.");
//            }
//
//             doc = collection.find(eq("title", "The Lego Frozen Movie")).first();
//            if (doc != null) {
//                System.out.println(doc.toJson());
//            } else {
//                System.out.println("No matching documents found.");
//            }
//            //Inserting POJO's into the Mongo collection
//            System.out.println("read movies collection from database");
//            MongoCollection<Movie> mCollection  = database.getCollection("movies", Movie.class).withCodecRegistry(pojoCodecRegistry);;
//            System.out.println("movie collection retrieved");
//
//            System.out.println(" Insert 2 new movies into the collection");
//            Movie newMovie = new Movie("horror", Arrays.asList("suspense","drama"),"Visali");
//            mCollection.insertOne(newMovie);
//            newMovie = new Movie("comedy", Arrays.asList("comedy","drama"),"April1Vidudala");
//            mCollection.insertOne(newMovie);
//            System.out.println("insertion complete");
//
//            //return all documents , movie pojo;s
//            List<Movie> movies = new ArrayList<Movie>();
//            mCollection.find().into(movies);
//            System.out.println(movies);
//
//            //Java Records
//            MongoCollection<DataRecord> drCollection = database.getCollection("data_storage_devices", DataRecord.class);
//            // insert the record
//            drCollection.insertOne(new DataRecord("2TB SSD", 1.71, "samsung"));
//            drCollection.insertOne(new DataRecord("1TB SSD", 0.9, "kingston"));
//
//            List<DataRecord> records = new ArrayList<DataRecord>();
//            drCollection.find().into(records);
//            records.forEach(System.out::println);
//
//            //Java Records using annotations.
//            MongoCollection<NetworkDeviceRecord> ndrCol = database.getCollection("network_devices", NetworkDeviceRecord.class);
//            // insert the record
//            String deviceId = new ObjectId().toHexString();
//            ndrCol.insertOne(new NetworkDeviceRecord(deviceId, "Enterprise Wi-fi", "router"));
//
//            // return all documents in the collection as records
//            List<NetworkDeviceRecord> recds = new ArrayList<NetworkDeviceRecord>();
//            ndrCol.find().into(recds);
//            records.forEach(System.out::println);
//
//
//            //
//
//
//            Bson projectionFields = Projections.fields(
//                    Projections.include("title", "plot"),
//                    Projections.excludeId());
//             Document docMovie = collection.find(eq("title", "Visali"))
//                    .projection(projectionFields)
//                    //.sort(Sorts.descending("imdb.rating"))
//                    .first();
//            if (docMovie == null) {
//                System.out.println("No results found.");
//            } else {
//                System.out.println(docMovie.toJson());
//            }
//
//
//            MongoCursor<Document> cursor = collection.find(eq("plot","horror"))
//                    .projection(projectionFields)
//                    .sort(Sorts.descending("title")).iterator();
//
//
//            try {
//                while(cursor.hasNext()) {
//                    System.out.println(cursor.next().toJson());
//                }
//            } finally {
//                cursor.close();
//            }
//
//            crudService.updateOps(collection, database);
//        }