package health;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("health")
public class HealthManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthManagementSystemApplication.class, args);
	}

}
