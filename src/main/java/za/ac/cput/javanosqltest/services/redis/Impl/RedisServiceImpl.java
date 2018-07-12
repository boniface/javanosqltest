package za.ac.cput.javanosqltest.services.redis.Impl;

import redis.clients.jedis.Jedis;
import za.ac.cput.javanosqltest.domain.Person;
import za.ac.cput.javanosqltest.domain.Result;
import za.ac.cput.javanosqltest.repository.Repository;
import za.ac.cput.javanosqltest.repository.redis.RedisRepository;
import za.ac.cput.javanosqltest.services.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class RedisServiceImpl implements Service {
    private Repository repository= new RedisRepository();

    @Override
    public Result create(Long number) {
        long count =0;

        Result result = new Result();
        LocalDateTime timeStamp = LocalDateTime.now();
        result.setStart(timeStamp);
        long start = Timestamp.valueOf(timeStamp).getTime();


        for (int i = 0; i < number; i++) {
            Person person = new Person();
            person.setName("Person Number "+i);
            Person created = repository.create(person);
            if(created.getId()!=null)
                count++;
            result.setObjects(count);
        }


        timeStamp = LocalDateTime.now();
        result.setEnd(timeStamp);
        result.setObjects(number);
        long end = Timestamp.valueOf(result.getEnd()).getTime();
        result.setObjects(number);
        result.setDuration(end - start);
        return result;
    }

    @Override
    public Result read() {


        Result result = new Result();
        LocalDateTime timeStamp = LocalDateTime.now();
        result.setStart(timeStamp);
        long start = Timestamp.valueOf(timeStamp).getTime();


        List<Person> persons = repository.readAll();


        timeStamp = LocalDateTime.now();
        result.setEnd(timeStamp);
        result.setObjects((long) persons.size());
        long end = Timestamp.valueOf(result.getEnd()).getTime();
        result.setDuration(end - start);
        return result;
    }

    @Override
    public Result update() {
        long count =0;

        Result result = new Result();
        LocalDateTime timeStamp = LocalDateTime.now();
        result.setStart(timeStamp);
        long start = Timestamp.valueOf(timeStamp).getTime();
        List<Person> persons = repository.readAll();


        for (int i = 0; i < persons.size(); i++) {
            Person person = new Person();
            person.setName("Person Number updated "+i);
            Person created = repository.create(person);
            if(created.getId()!=null)
                count++;
            result.setObjects(count);
        }


        timeStamp = LocalDateTime.now();
        result.setEnd(timeStamp);
        result.setObjects((long) persons.size());
        long end = Timestamp.valueOf(result.getEnd()).getTime();
        result.setDuration(end - start);
        return result;
    }

    @Override
    public Result delete() {
        long count =0;

        Result result = new Result();
        LocalDateTime timeStamp = LocalDateTime.now();
        result.setStart(timeStamp);
        long start = Timestamp.valueOf(timeStamp).getTime();
        List<Person> persons = repository.readAll();


        for (Person person : persons) {
            boolean isDeleted = repository.delete(person);
            if(isDeleted)
                count++;
        }


        timeStamp = LocalDateTime.now();
        result.setEnd(timeStamp);
        result.setObjects(count);
        long end = Timestamp.valueOf(result.getEnd()).getTime();
        result.setDuration(end - start);
        return result;
    }
}
