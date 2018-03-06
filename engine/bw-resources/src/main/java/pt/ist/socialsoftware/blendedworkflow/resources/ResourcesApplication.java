package pt.ist.socialsoftware.blendedworkflow.resources;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import pt.ist.socialsoftware.blendedworkflow.core.CoreApplication;

@PropertySource({ "classpath:resources.properties" })
@Import(CoreApplication.class)
@SpringBootApplication
public class ResourcesApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ResourcesApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(ResourcesApplication.class, args);
	}

}
