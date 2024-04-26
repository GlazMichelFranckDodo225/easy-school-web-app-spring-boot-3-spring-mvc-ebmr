package com.dgmf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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
        http.authorizeHttpRequests((requests) -> requests.anyRequest().permitAll());
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());

        // Return an Object of SecurityFilterChain Type
        return http.build();
    }
}
