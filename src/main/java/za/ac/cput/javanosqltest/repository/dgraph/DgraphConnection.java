package za.ac.cput.javanosqltest.repository.dgraph;

import io.dgraph.DgraphClient;
import io.dgraph.DgraphGrpc;
import io.dgraph.DgraphProto;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.ArrayList;
import java.util.List;

public class DgraphConnection {

    private DgraphClient client;

    private static DgraphConnection connection = null;

    private DgraphConnection() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9080).usePlaintext(true).build();
        DgraphGrpc.DgraphBlockingStub stub = DgraphGrpc.newBlockingStub(channel);
        List<DgraphGrpc.DgraphBlockingStub> stubs = new ArrayList<>();
        stubs.add(stub);
        DgraphClient dgraphClient = new DgraphClient(stubs);
        String schema = " id: string @index(exact) .\n" +
                " name: string   @index(int)  .\n";
        DgraphProto.Operation op = DgraphProto.Operation.newBuilder().setSchema(schema).build();
        dgraphClient.alter(op);
        client = dgraphClient;
    }

    public static DgraphConnection getInstance() {
        if (connection == null) {
            connection = new DgraphConnection();
        }
        return connection;
    }

    public DgraphClient getClient() {
        return client;
    }
}
