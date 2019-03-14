package za.ac.cput;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@RestController
public class App {

    @RequestMapping("/")
    public String home() {
        return "Docker Issues now Fixed See Readme file ";
    }

    public static void main( String[] args ) {
        SpringApplication.run(App.class, args);
        System.out.println( "Hello World!" );
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

    @RequestMapping(value="/java/redis/delete/{number}", method=RequestMethod.GET)
    public String deleteRedis(@PathVariable Long number) {
        return "You are asking me to delete "+number+" objects ";
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



}
