package com.example.PFT.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

//Swagger Configuration
@Configuration
public class OpenAPIConfiguration {

    @Bean
    public OpenAPI defineOpenApi() {
        Server server = new Server();
        server.setUrl("http://localhost:8080");
        //for live one
        //server.setUrl("https://pft-imqb.onrender.com/");
        server.setDescription("Development");

//        Contact myContact = new Contact();
//        myContact.setName("Jane Doe");
//        myContact.setEmail("your.email@gmail.com");

        Info information = new Info()
                .title("Personal Finances Tracker API")
                .version("1.0")
                .description("This API exposes endpoints to manage your personal finances.");
//                .contact(myContact);

        SecurityScheme securityScheme = new SecurityScheme()
                .name("bearerAuth")
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");

        return new OpenAPI().info(information).servers(List.of(server)).addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components().addSecuritySchemes("bearerAuth", securityScheme));
    }
}
