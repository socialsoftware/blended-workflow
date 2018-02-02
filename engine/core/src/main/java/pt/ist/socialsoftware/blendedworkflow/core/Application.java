package pt.ist.socialsoftware.blendedworkflow.core;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;

@PropertySource({ "classpath:application.properties" })
@SpringBootApplication
public class Application extends SpringBootServletInitializer {
	@Value("${frontend.url}")
	public static final String baseUrl = "http://localhost:8080";

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
