

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
@EnableCircuitBreaker
@ComponentScan(basePackages = {"com.microservicio.client*"})
public class MicroservicioClientApplication {
	
	 public static void main(String[] args)
	 {
		 System.setProperty("spring.jackson.serialization.INDENT_OUTPUT", "true");
		 SpringApplication.run(MicroservicioClientApplication.class, args);
	 }


}
