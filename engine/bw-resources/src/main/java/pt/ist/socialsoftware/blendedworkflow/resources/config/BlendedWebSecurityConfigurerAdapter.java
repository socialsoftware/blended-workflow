package pt.ist.socialsoftware.blendedworkflow.resources.config;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import pt.ist.socialsoftware.blendedworkflow.resources.security.BlendedAuthenticationEntryPoint;
import pt.ist.socialsoftware.blendedworkflow.resources.security.BlendedAuthenticationSuccessHandler;
import pt.ist.socialsoftware.blendedworkflow.resources.security.BlendedUserDetailsService;


@Configuration
@EnableWebSecurity
public class BlendedWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter{
    private static Logger logger = LoggerFactory.getLogger(BlendedWebSecurityConfigurerAdapter.class);

    @Autowired
    private BlendedAuthenticationEntryPoint blendedAuthenticationEntryPoint;

    @Autowired
    private BlendedAuthenticationSuccessHandler authenticationSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        logger.debug("configure123");

        http.csrf().disable()
            .exceptionHandling()
                .authenticationEntryPoint(blendedAuthenticationEntryPoint)
                .and()
            .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/").authenticated()
//                .anyRequest().authenticated()
                .and()
            .formLogin().
                loginPage("/login")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(new SimpleUrlAuthenticationFailureHandler())
                .permitAll()
                .and()
            .logout()
                .permitAll();

        http.sessionManagement().maximumSessions(2).sessionRegistry(sessionRegistry());
    }

    @Inject
    public void registerAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        logger.debug("registerAuthentication");

        auth.userDetailsService(blendedUserDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }

    @Bean
    public BlendedUserDetailsService blendedUserDetailsService() {
        return new BlendedUserDetailsService();
    }

    @Bean
    public BlendedAuthenticationSuccessHandler mySuccessHandler(){
        return new BlendedAuthenticationSuccessHandler();
    }
    @Bean
    public SimpleUrlAuthenticationFailureHandler myFailureHandler(){
        return new SimpleUrlAuthenticationFailureHandler();
    }
}
