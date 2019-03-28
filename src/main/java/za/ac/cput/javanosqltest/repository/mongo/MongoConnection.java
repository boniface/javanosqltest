package za.ac.cput.javanosqltest.repository.mongo;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class MongoConnection {
    private static MongoConnection connection = null;

    public MongoCollection<Document> getCollection() {
        return collection;
    }

    private MongoCollection<Document> collection;

    public MongoConnection() {

        MongoClient client = new MongoClient(new MongoClientURI("mongodb://mongo-0.mongo,mongo-1.mongo,mongo-2.mongo,mongo-3.mongo:27017/users\\_?"));

        collection = client.getDatabase("users").getCollection("person");
    }

    public static MongoConnection getInstance() {
        if(connection == null) {
            connection = new MongoConnection();
        }
        return connection;
    }
}
