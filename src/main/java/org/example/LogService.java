package org.example;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static spark.Spark.port;
import static spark.Spark.post;

public class LogService {

    public static void main(String[] args) {
        port(9001);
        MongoClientURI uri = new MongoClientURI("mongodb://localhost:27017/testdb");

        MongoClient mongoClient = new MongoClient(uri);

        MongoDatabase database = mongoClient.getDatabase("testdb");
        System.out.println("Connected to database successfully!");


        // Obtener una instancia de la colección "logs"
        MongoCollection<Document> collection = database.getCollection("logs");
        Gson gson = new Gson();

        post("/mensajitos", (request, response) -> {

            String message = request.body();
            Document log = new Document();
            log.append("message", message);
            log.append("date", new Date());

            collection.insertOne(log);

//            // Obtener los últimos 10 registros ordenados por fecha descendente
            List<Document> logs = collection.find()
                    .sort(new Document("date", -1))
                    .limit(10)
                    .projection(new Document("message", 1))
                    .projection(new Document("_id", 0))
                    .into(new ArrayList<>());

            return gson.toJson(logs);

        });



    }
}
