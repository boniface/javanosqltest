package za.ac.cput.javanosqltest.repository.redis;

import redis.clients.jedis.Jedis;
import za.ac.cput.javanosqltest.domain.Person;
import za.ac.cput.javanosqltest.repository.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class RedisRepository implements Repository {


    public  Jedis getConnection(){

        Jedis jedis = new Jedis("localhost");

        return  jedis;
    }

    @Override
    public Person create(Person person) {
        person.setId(UUID.randomUUID().toString());
        getConnection().set(person.getId(),person.getName());
        return person;

    }

    @Override
    public Person update(Person person) {
        person.setName(person.getName());
        getConnection().set(person.getId(),person.getName());
        return person;

    }

    @Override
    public boolean delete(Person person) {
        getConnection().del(person.getId());
        String id = getConnection().get(person.getId());
        return id == null;
    }

    @Override
    public Person read(String  id) {
        String name = getConnection().get(id);
        Person person = new Person();
        person.setId(id);
        person.setName(name);
        return person;
    }

    @Override
    public List<Person> readAll() {
        List<Person> persons = new ArrayList<>();
        Set<String> keys = getConnection().keys("*");
        for (String key : keys) {
            Person person = new Person();
            person.setId(key);
            person.setName(getConnection().get(key));
            persons.add(person);
        }
        return persons;
    }
}