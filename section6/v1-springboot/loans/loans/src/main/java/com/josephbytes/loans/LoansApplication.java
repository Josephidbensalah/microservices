package com.josephbytes.loans;

import com.josephbytes.loans.dto.LoansContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {LoansContactInfoDto.class})
@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Loans Microservices REST API Documentation",
				description = "EasyBank Loans Microservices REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Joseph Saleh",
						email = "joseph@saleh.com",
						url = "https://josephsaleh.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://licence.josephsaleh.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "EasyBank Loans Microservices REST API Documentation",
				url = "https://josephsaleh.com"
		))
public class LoansApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoansApplication.class, args);
	}

}
