package za.ac.cput.javanosqltest.repository.redis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import za.ac.cput.javanosqltest.domain.Person;
import za.ac.cput.javanosqltest.repository.Repository;

import java.security.SecureRandom;
import java.util.*;

public class RedisRepository implements Repository {


   public  JedisCluster getCluaster(){
       Set<HostAndPort> jedisClusterNode = new HashSet<HostAndPort>();
       jedisClusterNode.add(new HostAndPort("redis.r8s.svc.cluster.local", 7379));
       return  new JedisCluster(jedisClusterNode);
   }

    @Override
    public Person  create(Person person) {
        person.setId(UUID.fromString(new SecureRandom().toString()).toString());
        getCluaster().set(person.getId(),person.getName());
        return person;

    }

    @Override
    public Person update(Person person) {
        person.setName(person.getName());
        getCluaster().set(person.getId(),person.getName());
        getCluaster().set(person.getId(),person.getName());
        return person;

    }

    @Override
    public boolean delete(Person person) {
        getCluaster().del(person.getId());
        String id = getCluaster().get(person.getId());
        return id == null;
    }

    @Override
    public Person read(String  id) {
       String name = getCluaster().get(id);
       Person person = new Person();
       person.setId(id);
       person.setName(name);
       return person;
    }

    @Override
    public List<Person> readAll() {
       List<Person> persons = new ArrayList<>();
       Set<String> results = getCluaster().hkeys("*");
        for (String result : results) {
            Person person = new Person();
            person.setId(result);
            person.setName(getCluaster().get(result));
            persons.add(person);
        }
        return persons;
    }
}
