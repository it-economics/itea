package com.ite.itea.ecommerce;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.annotations.OpenAPI31;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPI31
@OpenAPIDefinition(
        info = @Info(version = "v0.1.0", title = "itea-ecommerce-api")
)
public class IteaApplication {

    public static void main(String[] args) {
        SpringApplication.run(IteaApplication.class, args);
    }

}
