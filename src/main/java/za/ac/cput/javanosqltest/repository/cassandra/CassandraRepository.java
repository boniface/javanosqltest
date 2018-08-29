package za.ac.cput.javanosqltest.repository.cassandra;


import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import za.ac.cput.javanosqltest.domain.Person;
import za.ac.cput.javanosqltest.repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class CassandraRepository implements Repository {

    final Cluster cluster = Cluster
            .builder()
            .addContactPoint("127.0.0.1")
            .build();
    final Session session = cluster.connect("users");
    @Override
    public Person create(Person person) {
        session.execute("INSERT INTO persons (id, name) VALUES ("+ person.getId()+ ","+person.getName()+")");
        return person;
    }

    @Override
    public Person update(Person person) {
        session.execute("UPDATE persons SET name = "+ person.getName()+ " WHERE id ="+person.getId()+")");
        return person;
    }

    @Override
    public boolean delete(Person person) {
        session.execute("DELETE FROM persons where id = "+person.getId());
        return false;
    }

    @Override
    public Person read(String id) {
        ResultSet results = session.execute("SELECT * FROM persons WHERE id = "+id);
        final Row row = results.one();
        Person person = new Person();
        person.setName(row.getString("name"));
        person.setId(row.getString("id"));
        return person;
    }

    @Override
    public List<Person> readAll() {
        List<Person> persons = new ArrayList<>();
        ResultSet results = session.execute("SELECT * FROM persons");
        for (Row row : results) {
            Person person = new Person();
            person.setName(row.getString("name"));
            person.setId(row.getString("id"));
            persons.add(person);
        }
        return persons;
    }
}
