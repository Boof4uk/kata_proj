package com.bank.profile.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    public OpenAPI customizeOpenApi(){
        return new OpenAPI().info(apiInfo());
    }
    private Info apiInfo() {
        return new Info()
                .title("Blog API")
                .description("Api for blog information")
                .version("0.1.0")
                .contact(apiContact());
    }

    private Contact apiContact() {
        return new Contact()
                .name("Blog api developers")
                .email("buhgin1@gmail.com")
                .url("https://github.com");
    }
}
