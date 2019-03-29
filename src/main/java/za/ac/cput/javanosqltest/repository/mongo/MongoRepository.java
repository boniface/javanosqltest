package za.ac.cput.javanosqltest.repository.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import za.ac.cput.javanosqltest.domain.Person;
import za.ac.cput.javanosqltest.repository.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;


public class MongoRepository implements Repository {


    private MongoCollection<Document> getConnection() {
        MongoClient client = new MongoClient(new MongoClientURI("mongodb://mongodb://10.233.102.164,10.233.75.38,10.233.74.83,10.233.97.147:27017/?"));
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
        Document document =  getConnection().find(eq("_id", id)).first();
        Person person = new Person();
        person.setId(document.getString("_id"));
        person.setName(document.getString("name"));
        return person;
    }

    @Override
    public List<Person> readAll() {
        List<Person> personList = new ArrayList<>();
        for (Document document : getConnection().find()) {
            Person person = new Person();
            person.setId(document.getString("_id"));
            person.setName(document.getString("name"));
            personList.add(person);
        }
        return personList;
    }


}
