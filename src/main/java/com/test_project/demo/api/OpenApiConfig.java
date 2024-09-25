package com.test_project.demo.api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "File service",
                description = "Getting number", version = "1.0.0",
                contact = @Contact(
                        name = "Lipakov Vadim",
                        email = "vadimlipakov@gmail.com"
                )
        )
)
public class OpenApiConfig {
}
