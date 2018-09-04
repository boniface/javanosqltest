package za.ac.cput.javanosqltest.services.cassandra;

import za.ac.cput.javanosqltest.domain.Person;
import za.ac.cput.javanosqltest.domain.Result;
import za.ac.cput.javanosqltest.repository.Repository;
import za.ac.cput.javanosqltest.repository.cassandra.CassandraRepository;
import za.ac.cput.javanosqltest.services.Service;

import java.time.LocalDateTime;
import java.util.List;

import static java.sql.Timestamp.valueOf;

public class CassandraServiceImpl implements Service {
    private Repository repository = new CassandraRepository();
    @Override
    public Result create(Long number) {

        Result result = new Result();
        LocalDateTime timeStamp = LocalDateTime.now();
        result.setStart(timeStamp);
        long start = valueOf(timeStamp).getTime();
        for (int i = 0; i < number; i++) {
            Person person = new Person();
            person.setId(Integer.toString(i));
            person.setName(" Person Number "+i);
            repository.create(person);

        }

        timeStamp = LocalDateTime.now();
        result.setEnd(timeStamp);
        result.setObjects(number);
        long end = valueOf(result.getEnd()).getTime();
        result.setObjects(number);
        result.setDuration(end - start);
        return result;
    }


    @Override
    public Result read() {

        Result result = new Result();
        LocalDateTime timeStamp = LocalDateTime.now();
        result.setStart(timeStamp);
        long start = valueOf(timeStamp).getTime();
        List<Person> persons = repository.readAll();
        timeStamp = LocalDateTime.now();
        result.setObjects(Long.valueOf(persons.size()));
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

        for (Person person : persons) {
            person.setId(person.getId());
            person.setName(person.getName()+" Updated ");
            repository.update(person);
        }


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
