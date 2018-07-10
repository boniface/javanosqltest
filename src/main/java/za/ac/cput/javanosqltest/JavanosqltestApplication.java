package za.ac.cput.javanosqltest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class JavanosqltestApplication {

    @RequestMapping("/")
    public String home() {
        return "Docker Issues now Fixed See Readme file ";
    }


    public static void main(String[] args) {
        SpringApplication.run(JavanosqltestApplication.class, args);
    }
}
