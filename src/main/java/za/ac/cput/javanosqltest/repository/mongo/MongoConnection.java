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

        MongoClient client = new MongoClient(new MongoClientURI("mongodb://10.233.102.164,10.233.75.38,10.233.74.83,10.233.97.147:27017/?"));

        collection = client.getDatabase("users").getCollection("person");
    }

    public static MongoConnection getInstance() {
        if(connection == null) {
            connection = new MongoConnection();
        }
        return connection;
    }
}
