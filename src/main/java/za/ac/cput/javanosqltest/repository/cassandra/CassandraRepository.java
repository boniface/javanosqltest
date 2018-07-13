package za.ac.cput.javanosqltest.repository.cassandra;


import za.ac.cput.javanosqltest.domain.Person;
import za.ac.cput.javanosqltest.repository.Repository;

import java.util.List;

public class CassandraRepository implements Repository {
    @Override
    public Person create(Person person) {
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
