package za.ac.cput.javanosqltest.services.cassandra.Impl;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import org.junit.Before;
import za.ac.cput.javanosqltest.domain.Person;
import za.ac.cput.javanosqltest.domain.Result;
import za.ac.cput.javanosqltest.services.Service;
import za.ac.cput.javanosqltest.repository.Repository;
import za.ac.cput.javanosqltest.repository.cassandra.CassandraRepository;


import java.time.LocalDateTime;
import java.util.HashMap;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import static java.sql.Timestamp.valueOf;


public class CassandraServiceImpl implements Service {

    private Repository repository = new CassandraRepository();

    final Cluster cluster = Cluster
            .builder()
            .addContactPoint("127.0.0.1")
            .build();

    public static final String KEYSPACE_CREATION_QUERY = "CREATE KEYSPACE IF NOT EXISTS users WITH replication = { 'class': 'SimpleStrategy', 'replication_factor': '1' };";

    public static final String KEYSPACE_ACTIVATE_QUERY = "USE users;";

    public static final String DATA_TABLE_NAME = "person";

    final Session session = cluster.connect("users");

    @Before
    public void setUp() {
        final Cluster cluster = Cluster.builder().addContactPoints("127.0.0.1").withPort(9042).build();
        LOGGER.info("Server Started at 127.0.0.1:9042... ");
        final Session session = cluster.connect();
        session.execute(KEYSPACE_CREATION_QUERY);
        session.execute(KEYSPACE_ACTIVATE_QUERY);
        LOGGER.info("KeySpace created and activated.");

    }

    @Override
    public Result create(Long number) {

        Result result = new Result();
        LocalDateTime timeStamp = LocalDateTime.now();
        result.setStart(timeStamp);
        long start = valueOf(timeStamp).getTime();
        for (int i = 0; i < number; i++) {

        }
        timeStamp = LocalDateTime.now();
        result.setEnd(timeStamp);
        result.setObjects(number);
        long end = valueOf(result.getEnd()).getTime();
        result.setObjects(number);
        result.setDuration(end - start);
        return result;

    }

    @Override
    public Result read() {
        return null;
    }

    @Override
    public Result update() {
        return null;
    }

    @Override
    public Result delete() {
        return null;
    }
}
