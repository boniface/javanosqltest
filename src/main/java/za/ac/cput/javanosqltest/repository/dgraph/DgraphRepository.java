package za.ac.cput.javanosqltest.repository.dgraph;



import io.dgraph.DgraphClient;
import io.dgraph.DgraphGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import za.ac.cput.javanosqltest.domain.Person;
import za.ac.cput.javanosqltest.repository.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DgraphRepository implements Repository {

    public DgraphClient getConnection (){
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9080).usePlaintext(true).build();
        DgraphGrpc.DgraphBlockingStub stub  = DgraphGrpc.newBlockingStub(channel);
        List<DgraphGrpc.DgraphBlockingStub> stubs = new ArrayList<>();
        stubs.add(stub);
        return new DgraphClient(stubs);
    }



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
