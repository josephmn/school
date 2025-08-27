package com.api.school.config;

import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;

/**
 * OpenApiConfig.
 *
 * @author Joseph Magallanes
 * @since 2025-05-23
 */
@Configuration
@RequiredArgsConstructor
public class OpenApiConfig {

    private final OpenApiProperties openApiProperties;

    /**
     * Configures the OpenAPI documentation for the application.
     *
     * @return OpenAPI object with the configuration.
     */
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title(openApiProperties.getTitle())
                        .description("API gestion school, this API is a simple example of how "
                                + "to use OpenAPI 3.0 with Spring Boot.\n"
                                + "\nSome useful links:\n"
                                + "\n- [The repository in GitHub]("
                                + openApiProperties.getGithub().getUrl() + ")"
                                + "\n- [The source API definition for Banking Customer]("
                                + openApiProperties.getGithub().getDefinition() + ")"
                        )
                        .termsOfService(openApiProperties.getTerms())
                        .contact(new Contact()
                                .name(openApiProperties.getContact().getName())
                                .email(openApiProperties.getContact().getEmail())
                                .url(openApiProperties.getContact().getUrl())
                        )
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://www.apache.org/licenses/LICENSE-2.0.html"))
                        .version("1.0.0"))
                .externalDocs(new ExternalDocumentation()
                        .description("Find out more about Swagger")
                        .url("http://swagger.io"))
                .servers(
                        Collections.singletonList(
                                new Server().url(openApiProperties.getServers().getUrl()).
                                        description(openApiProperties.getServers().getDescription())
                        )
                );
    }
}
