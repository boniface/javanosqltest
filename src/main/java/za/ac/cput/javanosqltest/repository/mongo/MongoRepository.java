package za.ac.cput.javanosqltest.repository.mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import za.ac.cput.javanosqltest.domain.Person;
import za.ac.cput.javanosqltest.repository.Repository;

import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class MongoRepository implements Repository {

    public MongoCollection<Document> getConnection() {
        MongoClient client = new MongoClient("localhost", 22005);
        return client.getDatabase("users").getCollection("person");
    }

    @Override
    public Person create(Person person) {
        Document doc = new Document("_id", person.getId()).append("name", person.getName());
        getConnection().insertOne(doc);
        return person;
    }


    @Override
    public Person update(Person person) {
        getConnection().updateOne(eq("_id", person.getId()), new Document("$set", new Document("name", person.getName())));
        return person;
    }

    @Override
    public boolean delete(Person person) {
        getConnection().deleteOne(eq("_id", person.getId()));
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
