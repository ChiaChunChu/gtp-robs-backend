package ie.cct.gtp.robs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@ComponentScan("ie.cct*")
public class RobsApplication {

	public static void main(String[] args) {
		SpringApplication.run(RobsApplication.class, args);
	}

}
