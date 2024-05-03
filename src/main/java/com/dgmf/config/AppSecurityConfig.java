package com.dgmf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class AppSecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        // To Authenticate all the Requests
        // http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated());
        // To Unauthorized all Incoming Requests for all logged Users
        // http.authorizeHttpRequests((requests) -> requests.anyRequest().denyAll());
        // To Give Free Accesses to all the Requests
        // http.authorizeHttpRequests((requests) -> requests.anyRequest().permitAll());
        // Custom Security Configurations
        http
                // .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                    auth -> auth
                        // .requestMatchers("dashboard").authenticated()
                        .requestMatchers("/", "/home").permitAll()
                        // .requestMatchers("/", "/home").authenticated()
                        .requestMatchers("/holidays/**").permitAll()
                        .requestMatchers("/contact").permitAll()
                        .requestMatchers("saveMsg").permitAll()
                        // .requestMatchers("/courses").authenticated()
                        .requestMatchers("/courses").permitAll()
                        .requestMatchers("/about").permitAll()
                        .requestMatchers("/assets/**").permitAll()
                )
                .formLogin(withDefaults())
                .httpBasic(withDefaults());

        // Return an Object of SecurityFilterChain Type
        return http.build();
    }

    // PasswordEncoder Bean
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Spring Security In-Memory Authentication : https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/in-memory.html
    // Configure Multiple inMemory Users (Not Recommended for Production)
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("1234"))
                .roles("USER", "ADMIN")
                .build();

        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder().encode("123"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }

}
