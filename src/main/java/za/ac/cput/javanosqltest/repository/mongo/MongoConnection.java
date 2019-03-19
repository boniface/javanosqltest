package za.ac.cput.javanosqltest.repository.mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class MongoConnection {
    private static MongoConnection connection = null;

    public MongoCollection<Document> getCollection() {
        return collection;
    }

    private MongoCollection<Document> collection;

    private  MongoConnection() {
        MongoClient client = new MongoClient("mongo.mongo", 27017);
        collection = client.getDatabase("users").getCollection("person");
    }

    public static MongoConnection getInstance() {
        if(connection == null) {
            connection = new MongoConnection();
        }
        return connection;
    }
}
