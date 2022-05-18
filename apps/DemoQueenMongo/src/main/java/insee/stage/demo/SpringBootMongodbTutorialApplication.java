package insee.stage.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.context.annotation.Import;


@SpringBootApplication
public class SpringBootMongodbTutorialApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMongodbTutorialApplication.class, args);
    }
}
