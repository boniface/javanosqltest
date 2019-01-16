package za.ac.cput.javanosqltest.repository.dgraph;


import com.google.gson.Gson;
import com.google.protobuf.ByteString;
import io.dgraph.DgraphClient;
import io.dgraph.DgraphProto;
import io.dgraph.Transaction;
import za.ac.cput.javanosqltest.domain.Person;
import za.ac.cput.javanosqltest.repository.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class DgraphRepository implements Repository {

    DgraphClient client = DgraphConnection.getInstance().getClient();


    @Override
    public Person create(Person person) {
        Gson gson = new Gson(); // For JSON encode/decode
        Transaction txn = client.newTransaction();
        try {

            // Serialize it
            String json = gson.toJson(person);

            // Run mutation
            DgraphProto.Mutation mu = DgraphProto.Mutation
                    .newBuilder()
                    .setSetJson(ByteString.copyFromUtf8(json.toString()))
                    .build();
            txn.mutate(mu);
            txn.commit();

        } finally {
            txn.discard();
        }

        return person;
    }

    @Override
    public Person update(Person person) {
        Gson gson = new Gson(); // For JSON encode/decode

        Transaction txn = client.newTransaction();
        try {
            person.setName(person+ " Updated ");
            // Serialize it
            String json = gson.toJson(person);
            // Run mutation
            DgraphProto.Mutation mu = DgraphProto
                    .Mutation
                    .newBuilder()
                    .setSetJson(ByteString.copyFromUtf8(json))
                    .build();
            txn.mutate(mu);
            txn.commit();

        } finally {
            txn.discard();
        }
        return person;
    }

    @Override
    public boolean delete(Person person) {
        return false;
    }

    @Override
    public Person read(String id) {
        Gson gson = new Gson(); // For JSON encode/decode

        // Query
        String query =
                "query all($a: string){\n" +
                        "  all(func: eq(id, $a)) {\n" +
                        "    name\n" +
                        "  }\n" +
                        "}\n";

        Map<String, String> vars = Collections.singletonMap("$a", id);
        DgraphProto.Response res = client.newReadOnlyTransaction().queryWithVars(query, vars);

        Person persons = gson.fromJson(res.getJson().toStringUtf8(), Person.class);


        return persons.all.get(0); // Bad Programming potential Ugly Null Pointer Exception ..needs to be wrapped in if (!=null)
    }

    @Override
    public List<Person> readAll() {
        Gson gson = new Gson(); // For JSON encode/decode

        // Query
        String query =
                "query all($a: string){\n" +
                        "  all(func: eq(id, $a)) {\n" +
                        "    name\n" +
                        "  }\n" +
                        "}\n";

        Map<String, String> vars = Collections.singletonMap("$a", "");
        DgraphProto.Response res = client.newReadOnlyTransaction().queryWithVars(query, vars);

        Person persons = gson.fromJson(res.getJson().toStringUtf8(), Person.class);

        return persons.all;
    }
}