package pt.ist.socialsoftware.blendedworkflow.core;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import pt.ist.socialsoftware.blendedworkflow.core.utils.ModulesFactory;

@PropertySource({ "classpath:application.properties" })
@SpringBootApplication
public class CoreApplication extends SpringBootServletInitializer {

	@Value("${frontend.url}")
	public static final String frontendUrl = "http://localhost:8080";

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(CoreApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
	}

	@Bean
	public ModulesFactory modulesFactory() {
		return new ModulesFactory();
	}

}
