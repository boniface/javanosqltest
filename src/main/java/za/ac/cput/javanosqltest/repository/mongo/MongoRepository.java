package za.ac.cput.javanosqltest.repository.mongo;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import za.ac.cput.javanosqltest.domain.Person;
import za.ac.cput.javanosqltest.repository.Repository;
import com.mongodb.*;
import com.mongodb.client.MongoDatabase;

import java.util.List;

public class MongoRepository implements Repository {


    private MongoCollection<Person> collection;

    public MongoRepository(MongoCollection<Person> collection) {
        this.collection = collection;
    }

    public MongoRepository(Repository repository) {

    }

    public static void main(String[] args) {

        MongoClient mongoClient = new MongoClient("10.47.3.5", 22005);

        //    MongoClient client = new MongoClient("localhost",27017);

            MongoDatabase db = mongoClient.getDatabase("users");

            MongoCollection<Document> collection = db.getCollection("person");

        long startTime = System.currentTimeMillis();

        long endTime = System.currentTimeMillis();
        long diff = endTime - startTime;
        System.out.println("Total time (in mill seconds) : " + (diff));

    }

    @Override
    public Person create(Person person) {
        Document user = new Document("Id",11)
                .append("name","Anderson");

        collection.insertOne(person);

        return person;
    }

    @Override
    public Person update(Person person) {
        return null;
    }

    @Override
    public boolean delete(Person person) {
        return false;
    }

    @Override
    public Person read(String id) {
        return null;
    }

    @Override
    public List<Person> readAll() {
        return null;
    }


}
