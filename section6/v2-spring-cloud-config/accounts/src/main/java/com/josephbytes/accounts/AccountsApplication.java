package com.josephbytes.accounts;

import com.josephbytes.accounts.dto.AccountsContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableConfigurationProperties(
		value = {AccountsContactInfoDto.class}
)
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Accounts Microservices REST API Documentation",
				description = "EasyBank Accounts Microservices REST API Documentation",
				version = "v1",
				contact = @Contact(
							name = "Joseph Saleh",
							email = "Josephsaleh@joseph.com",
							url = "https://josephsaleh.com"),
				license = @License(name = "Apache 2.0",url = "https://licence.josephsaleh.com")
		),
		externalDocs = @ExternalDocumentation(
				description = "EasyBank Accounts Microservices REST API Documentation",
				url = "https://josephsaleh.com"
		)
)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
