package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.convert.converter.ConverterRegistry;

@Configuration
public class EnumConfig {
    @Bean
    @Primary
    public ConverterRegistry initConverter(ConverterRegistry registry) {
        registry.addConverterFactory(new EnumConverter());
        return registry;
    }
}
