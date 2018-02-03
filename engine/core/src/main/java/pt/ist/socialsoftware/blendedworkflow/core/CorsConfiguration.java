package pt.ist.socialsoftware.blendedworkflow.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import static pt.ist.socialsoftware.blendedworkflow.core.Application.frontendUrl;

@Configuration
public class CorsConfiguration {

    @Bean
    public WebMvcConfigurer addCorsMappings() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(frontendUrl)
                        .allowCredentials(false).maxAge(3600);
            }
        };
    }
}
