package za.ac.cput.javanosqltest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import za.ac.cput.javanosqltest.domain.Result;
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

    // HTTP method ReadOperation

    //Cassandra CRUD

    @RequestMapping(value="/java/cassandra/read/{number}", method=RequestMethod.GET)
    public String getCassandra(@PathVariable Long number) { return "You are asking me to read "+number+" objects ";
    }


//Redis CRUD

    @RequestMapping(value="/java/redis/read/{number}", method=RequestMethod.GET)
    public String getRedis(@PathVariable Long number) { return "You are asking me to read "+number+" objects ";
    }

//Mongo CRUD

    @RequestMapping(value="/java/mongo/read/{number}", method=RequestMethod.GET)
    public String getMongo(@PathVariable Long number) { return "You are asking me to read "+number+" objects ";
    }

//Dgraph CRUD

    @RequestMapping(value="/java/dgraph/read/{number}", method=RequestMethod.GET)
    public String getDgraphR(@PathVariable Long number) { return "You are asking me to read "+number+" objects ";
    }


//HTTP method WriteOperation

    //Cassandra CRUD

    @RequestMapping(value="/java/cassandra/write/{number}", method=RequestMethod.GET)
    public String postCassandra(@PathVariable Long number) { return "You are asking me to write "+number+" objects ";
    }


//Redis CRUD

    @RequestMapping(value="/java/redis/write/{number}", method=RequestMethod.GET)
    public String postRedis(@PathVariable Long number) {
        return "You are asking me to write "+number+" objects ";
    }

//Mongo CRUD

    @RequestMapping(value="/java/mongo/write/{number}", method=RequestMethod.GET)
    public String postMongo(@PathVariable Long number) { return "You are asking me to write "+number+" objects ";
    }


    //Dgraph CRUD
    @RequestMapping(value="/java/dgraph/write/{number}", method=RequestMethod.GET)
    public String postDgraph(@PathVariable Long number) {
        return "You are asking me to write "+number+" objects ";
    }

//HTTP method DeleteOperation

    //Cassandra CRUD

    @RequestMapping (value="/java/cassandra/delete/{number}", method=RequestMethod.GET)
    public String deleteCassandra(@PathVariable Long number) { return "You are asking me to delete "+number+" objects ";
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

    @RequestMapping(value="/java/mongo/delete/{number}", method=RequestMethod.GET)
    public String deleteMongo(@PathVariable Long number) { return "You are asking me to delete "+number+" objects ";
    }


    //Dgraph CRUD
    @RequestMapping(value="/java/dgraph/delete/{number}", method=RequestMethod.GET)
    public String deleteDgraph(@PathVariable Long number) {
        return "You are asking me to delete "+number+" objects ";
    }


    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
