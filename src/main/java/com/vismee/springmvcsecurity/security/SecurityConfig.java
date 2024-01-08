package com.vismee.springmvcsecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig
{
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource)
    {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
        manager.setUsersByUsernameQuery("select user_id, pw, active from members where user_id=?");
        manager.setAuthoritiesByUsernameQuery("select user_id, role from roles where user_id=?");
        return manager;
    }

    /* Using Spring Security Db Default Schema
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource)
    {
        return new JdbcUserDetailsManager(dataSource);
    }
     */

    /* In Memory authentication
    @Bean
    public InMemoryUserDetailsManager userDetailsManager()
    {
        UserDetails john = User.builder().
                           username("john").
                           password("{noop}john123").
                           roles("EMPLOYEE").
                           build();

        UserDetails mary = User.builder().
                           username("mary").
                           password("{noop}mary123").
                           roles("MANAGER").
                           build();

        UserDetails susan = User.builder().
                            username("susan").
                            password("{noop}susan123").
                            roles("ADMIN").
                            build();

        return new InMemoryUserDetailsManager(john,mary,susan);

    }
    */

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        http.
                // To setup role based access
                authorizeHttpRequests(configurer -> configurer.
                                requestMatchers("/").hasAnyRole("EMPLOYEE","MANAGER","ADMIN").
                                requestMatchers("/courseMaterials/**").hasAnyRole("MANAGER", "ADMIN").
                                requestMatchers("/courseFees/**").hasRole("ADMIN").
                                anyRequest().authenticated()).
                // Configuring custom access denied page for forbidden access
                exceptionHandling(configurer -> configurer.accessDeniedPage("/accessDenied")).
                /* Referencing custom login form instead of Spring Boot default login form
                   /showLogin - request mapping that we need to create to show the login form
                   /authenticateUser - the login form should post/submit the data to this url for processing. No need to create this code.
                 */
                formLogin(form -> form.loginPage("/showLogin").loginProcessingUrl("/authenticateUser").permitAll()).
                /*
                   Configuring application logout support. This will by default expose the url /logout
                   for logging out of the application. We need to create logout button wherever needed
                   as a form button so that it can post the data to Url /logout. And we dont want to create
                   code for /logout.
                   Spring Boot handles logging out of the application by
                   1. Invalidating user's HTTP session and session cookies
                   2. Sending user back to login page
                   3. Appending ?logout parameter in url
                 */
                logout(logout -> logout.permitAll());

        return http.build();
    }
}
