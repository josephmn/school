package com.api.school.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * JacksonConfig.
 * This configuration class sets up a custom ObjectMapper for use with WebFlux,
 * enabling strict duplicate detection in JSON parsing.
 *
 * @author Joseph Magallanes
 * @since 2025-06-16
 */
@Configuration
@EnableWebFlux
public class JacksonConfig implements WebFluxConfigurer {

    /**
     * Creates a custom ObjectMapper with strict duplicate detection enabled.
     * This is used for serializing and deserializing JSON in WebFlux applications.
     *
     * @return a configured ObjectMapper instance
     */
    @Bean
    @Primary
    public ObjectMapper reactiveObjectMapper() {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.configure(JsonParser.Feature.STRICT_DUPLICATE_DETECTION, true);
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper;
    }

    /**
     * Configures the HTTP message codecs for WebFlux.
     * This method sets up the Jackson JSON decoder and encoder with the custom ObjectMapper.
     *
     * @param configurer the ServerCodecConfigurer to configure
     */
    @Override
    public void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {
        final Jackson2JsonDecoder jsonDecoder = new Jackson2JsonDecoder(reactiveObjectMapper());
        final Jackson2JsonEncoder jsonEncoder = new Jackson2JsonEncoder(reactiveObjectMapper());

        configurer.defaultCodecs().jackson2JsonDecoder(jsonDecoder);
        configurer.defaultCodecs().jackson2JsonEncoder(jsonEncoder);

        // Configurar límites de memoria para requests grandes
        // configurer.defaultCodecs().maxInMemorySize(1024 * 1024); // 1MB
    }
}
