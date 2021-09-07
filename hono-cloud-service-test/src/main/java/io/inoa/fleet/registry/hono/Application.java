package io.inoa.fleet.registry.hono;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@EnableConfigurationProperties(InoaConfig.class)
public class Application {

	public static void main(final String[] args) {
		SpringApplication.run(Application.class, args);
		/*
		 * String tenantId = System.getenv("HONO_TENANT");
		 * System.out.println("Starting consumer..."); final Application
		 * honoExampleApplication = new Application(tenantId);
		 * honoExampleApplication.consumeData(); System.in.read();
		 * System.out.println("Finishing consumer.");
		 */
	}
}
