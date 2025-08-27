package com.api.school.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.core.DatabaseClient;
import io.asyncer.r2dbc.mysql.MySqlConnectionConfiguration;
import io.asyncer.r2dbc.mysql.MySqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import lombok.RequiredArgsConstructor;

/**
 * R2dbcConfig.
 * This class configures the R2DBC connection factory and database client for PostgreSQL.
 *
 * @author Joseph Magallanes
 * @since 2025-08-22
 */
@Configuration
@EnableR2dbcRepositories
@RequiredArgsConstructor
public class R2dbcConfig {

    private final PropertiesR2dbc propertiesR2dbc;

    /**
     * Creates a MySqlConnectionFactory bean.
     * This factory is used to create connections to the MySQL database.
     *
     * @return ConnectionFactory
     */
    @Bean
    public ConnectionFactory connectionFactory() {
        return MySqlConnectionFactory.from(
            MySqlConnectionConfiguration.builder()
                .host(propertiesR2dbc.getHost())
                .port(propertiesR2dbc.getPort())
                .username(propertiesR2dbc.getUsername())
                .password(propertiesR2dbc.getPassword())
                .database(propertiesR2dbc.getDatabase())
                .build()
        );
    }

    /**
     * Creates a DatabaseClient bean.
     * This client is used to interact with the database in a reactive manner.
     *
     * @param connectionFactory the connection factory
     * @return DatabaseClient
     */
    @Bean
    public DatabaseClient databaseClient(ConnectionFactory connectionFactory) {
        return DatabaseClient.builder()
            .connectionFactory(connectionFactory)
            .build();
    }
}
