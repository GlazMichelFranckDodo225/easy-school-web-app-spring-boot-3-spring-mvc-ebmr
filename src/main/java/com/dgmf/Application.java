package com.dgmf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.dgmf.repository")
@EntityScan("com.dgmf.entity")
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
