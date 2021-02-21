package org.example.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.ConverterRegistry

@Configuration
class EnumConfig {
    @Bean
    fun initConverter(registry: ConverterRegistry): ConverterRegistry {
        registry.addConverterFactory(EnumConverter())
        return registry
    }
}