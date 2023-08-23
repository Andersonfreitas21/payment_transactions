package com.picpaysimplificado.config.swagger;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Value("${developer.owner}")
    private String name;

    @Value("${developer.linkedin}")
    private String linkedin;

    @Value("${developer.email}")
    private String email;

    @Value("${title.swagger}")
    private String title;

    @Value("${description.swagger}")
    private String description;

    @Value("${license.swagger}")
    private String license;

    @Value("${license.url.swagger}")
    private String licenseUrl;

    @Value("${version.software}")
    private String version;

    @Bean
    public GroupedOpenApi userOpenApi() {
        return GroupedOpenApi.builder()
                .group("users")
                .pathsToMatch("/api/v1/users/**")
                .build();
    }

    @Bean
    public GroupedOpenApi transactionOpenApi() {
        return GroupedOpenApi.builder()
                .group("transactions")
                .pathsToMatch("/api/v1/transactions/**")
                .build();
    }

    @Bean
    public GroupedOpenApi publicOpenApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title(title)
                        .description(description)
                        .version(version)
                        .license(new License().name(license).url(licenseUrl)))
                .externalDocs(new ExternalDocumentation()
                        .description("SpringShop Wiki Documentation")
                        .url("https://springshop.wiki.github.org/docs"));
    }



}
