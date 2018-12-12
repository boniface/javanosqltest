package za.ac.cput.javanosqltest.repository.dgraph;


import com.google.gson.Gson;
import com.google.protobuf.ByteString;
import io.dgraph.DgraphClient;
import io.dgraph.DgraphProto;
import za.ac.cput.javanosqltest.domain.Person;
import za.ac.cput.javanosqltest.repository.Repository;

import java.util.List;

public class DgraphRepository implements Repository {

    DgraphClient client = DgraphConnection.getInstance().getClient();


    @Override
    public Person create(Person person) {
        Gson gson = new Gson(); // For JSON encode/decode

        DgraphClient.Transaction txn = client.newTransaction();
        try {

            // Serialize it
            String json = gson.toJson(person);

            // Run mutation
            DgraphProto.Mutation mu =
                    DgraphProto.Mutation.newBuilder().setSetJson(ByteString.copyFromUtf8(json.toString())).build();
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

        DgraphClient.Transaction txn = client.newTransaction();
        try {
            // Serialize it
            String json = gson.toJson(person);
            person.setName(person+ " Updated ");

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
        return null;
    }

    @Override
    public List<Person> readAll() {


        return null;
    }
}
