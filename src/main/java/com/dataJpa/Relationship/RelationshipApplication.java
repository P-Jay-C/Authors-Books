package com.dataJpa.Relationship;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.dajaJpa.Relationship.Service")
@OpenAPIDefinition()
public class RelationshipApplication {


	public static void main(String[] args) {
		SpringApplication.run(RelationshipApplication.class, args);
	}

}
