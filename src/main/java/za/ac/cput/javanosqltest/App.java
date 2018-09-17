package za.ac.cput.javanosqltest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import za.ac.cput.javanosqltest.domain.Result;
import za.ac.cput.javanosqltest.services.cassandra.CassandraServiceImpl;
import za.ac.cput.javanosqltest.services.mongo.Impl.MongoServiceImpl;
import za.ac.cput.javanosqltest.services.redis.Impl.RedisServiceImpl;
import za.ac.cput.javanosqltest.services.Service;

@SpringBootApplication
@RestController
public class App {
    private Service service;

    @RequestMapping("/")
    public String home() {
        return "Docker Issues now Fixed See Readme file ";
    }


//Redis CRUD

    @RequestMapping(value="/redis/create/{number}", method=RequestMethod.GET)
    public Result redisCreate(@PathVariable Long number) {
       service = new RedisServiceImpl();
       return service.create(number);
    }

    @RequestMapping(value="/redis/read", method=RequestMethod.GET)
    public Result redisRead() {
        service = new RedisServiceImpl();
        return service.read();
    }

    @RequestMapping(value="/redis/update", method=RequestMethod.GET)
    public Result redisUpdate() {
        service = new RedisServiceImpl();
        return service.update();
    }

    @RequestMapping(value="/redis/delete", method=RequestMethod.GET)
    public Result redisDelete() {
        service = new RedisServiceImpl();
        return service.delete();
    }

//Mongo CRUD

    @RequestMapping(value="/mongo/create/{number}", method=RequestMethod.GET)
    public Result mongoCreate(@PathVariable Long number) {
        service = new MongoServiceImpl();
        return service.create(number);
    }

    @RequestMapping(value="/mongo/read", method=RequestMethod.GET)
    public Result mongoRead() {
        service = new MongoServiceImpl();
        return service.read();
    }

    @RequestMapping(value="/mongo/update", method=RequestMethod.GET)
    public Result mongoUpdate() {
        service = new MongoServiceImpl();
        return service.update();
    }

    @RequestMapping(value="/mongo/delete", method=RequestMethod.GET)
    public Result mongoDelete() {
        service = new MongoServiceImpl();
        return service.delete();
    }

    // Cassandra CRUD

    @RequestMapping(value="/cassandra/create/{number}", method=RequestMethod.GET)
    public Result cassandraCreate(@PathVariable Long number) {
        service = new CassandraServiceImpl();
        return service.create(number);
    }

    @RequestMapping(value="/cassandra/read", method=RequestMethod.GET)
    public Result cassandraRead() {
        service = new CassandraServiceImpl();
        return service.read();
    }

    @RequestMapping(value="/cassandra/update", method=RequestMethod.GET)
    public Result cassandraUpdate() {
        service = new CassandraServiceImpl();
        return service.update();
    }

    @RequestMapping(value="/cassandra/delete", method=RequestMethod.GET)
    public Result cassandraDelete() {
        service = new CassandraServiceImpl();
        return service.delete();
    }


    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
