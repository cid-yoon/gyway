import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.Entity;

@SpringBootApplication
@EntityScan("com.cid.gyway.*")
@EnableJpaRepositories("com.cid.gyway.*")
public class Application {

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
