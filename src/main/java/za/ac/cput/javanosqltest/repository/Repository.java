package za.ac.cput.javanosqltest.repository;

import za.ac.cput.javanosqltest.domain.Person;

import java.util.List;

public interface Repository {
    Person create(Person person);
    Person update(Person person);
    boolean delete(Person person);
    Person read(String  id);
    List<Person> readAll();
}
