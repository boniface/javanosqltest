package za.ac.cput.javanosqltest.repository.dgraph;


import io.dgraph.DgraphClient;
import io.dgraph.DgraphGrpc;
import io.dgraph.DgraphProto;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class DgraphConnection {

    private DgraphClient client;

    private static DgraphConnection connection = null;

    private static final String HOSTNAME = "localhost";
    private static final int PORT = 9080;


    public DgraphConnection() {

        ManagedChannel channel = ManagedChannelBuilder.forAddress(HOSTNAME, PORT).usePlaintext(true).build();
        DgraphGrpc.DgraphStub stub = DgraphGrpc.newStub(channel);
        DgraphClient dgraphClient = new DgraphClient(stub);
        String schema = " id: string @index(hash) .\n" +
                " name: string @index(hash) .\n";
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