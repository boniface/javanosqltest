package za.ac.cput.javanosqltest.repository.cassandra;


import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import za.ac.cput.javanosqltest.domain.Person;
import za.ac.cput.javanosqltest.repository.Repository;

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
        return null;
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
