package za.ac.cput.javanosqltest.services.mongo.Impl;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import za.ac.cput.javanosqltest.domain.Person;
import za.ac.cput.javanosqltest.domain.Result;
import za.ac.cput.javanosqltest.repository.Repository;

import za.ac.cput.javanosqltest.repository.mongo.MongoRepository;
import za.ac.cput.javanosqltest.services.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Timestamp.valueOf;


public class MongoServiceImpl implements Service {


    MongoClient mongoClient = new MongoClient("localhost", 27017);
    MongoDatabase database = mongoClient.getDatabase("users");

    public Repository repository= new MongoRepository();

    public MongoCollection<Document> collection;

    @Override
    public Result create(Long number) {

        MongoClient client = new MongoClient("localhost", 27017);
        client.getDatabase("users");

        List<MongoDatabase> writes = new ArrayList<>();
        Result result = new Result();

        LocalDateTime timeStamp = LocalDateTime.now();
        result.setStart(timeStamp);
        long start = valueOf(timeStamp).getTime();

        for (int i = 0; i < number; i++) {

            Person person = new Person();
            long startTime = System.currentTimeMillis();
            collection.insertOne(new Document().append("name", person.getName()));
            writes.add(database);
            System.out.println("Inserted Document ");
            long endTime = System.currentTimeMillis();
            long diff = endTime - startTime;
        }

        timeStamp = LocalDateTime.now();
        result.setEnd(timeStamp);
        result.setObjects(number);
        long end = valueOf(result.getEnd()).getTime();
        result.setObjects(number);
        result.setDuration(end - start);
        return result;
    }

//Read works
    @Override
    public Result read() {

        Result result = new Result();
        LocalDateTime timeStamp = LocalDateTime.now();
        result.setStart(timeStamp);
        long start = valueOf(timeStamp).getTime();
        MongoClient client = new MongoClient("localhost", 27017);
        client.getDatabase("users");
        List<Person> persons = repository.readAll();

        timeStamp = LocalDateTime.now();
        result.setEnd(timeStamp);
        long end = valueOf(result.getEnd()).getTime();
        result.setDuration(end - start);
        return result;

    }

    @Override
    public Result update() {

        long count = 0;

        Result result = new Result();
        LocalDateTime timeStamp = LocalDateTime.now();
        result.setStart(timeStamp);
        long start = valueOf(timeStamp).getTime();
        List<Person> persons = repository.readAll();

        MongoClient client = new MongoClient("localhost", 27017);

            Person person = new Person();
            long startTime = System.currentTimeMillis();
            collection.insertOne(new Document().append("name", person.getName()));
            System.out.println("Updated Document ");
            long endTime = System.currentTimeMillis();
            long diff = endTime - startTime;

            timeStamp = LocalDateTime.now();
            result.setEnd(timeStamp);
            result.setObjects((long) persons.size());
            long end = valueOf(result.getEnd()).getTime();
            result.setDuration(end - start);
            return result;
    }


    @Override
    public Result delete() {

        long count =0;

        Result result = new Result();
        LocalDateTime timeStamp = LocalDateTime.now();
        result.setStart(timeStamp);
        long start = valueOf(timeStamp).getTime();

        MongoClient client = new MongoClient("localhost", 27017);

        DBCollection table = (DBCollection) client.getDatabase("users");
        List<Person> persons = repository.readAll();

        for (Person person : persons) {
            boolean isDeleted = repository.delete(person);
            if(isDeleted)
                count++;
        }



        timeStamp = LocalDateTime.now();
        result.setEnd(timeStamp);
        result.setObjects(count);
        long end = valueOf(result.getEnd()).getTime();
        result.setDuration(end - start);
        return result;
    }
}
