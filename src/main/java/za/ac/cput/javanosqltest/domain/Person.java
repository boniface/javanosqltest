package za.ac.cput.javanosqltest.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Person implements Serializable {
    private String id;
    private String name;

    public List<Person> all;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(getId(), person.getId());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId());
    }
}
