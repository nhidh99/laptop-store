package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

@Configuration
public class FreemarkerConfig {
    @Bean(name = "emailConfigBean")
    public FreeMarkerConfigurationFactoryBean freemarkerConfiguration(ResourceLoader resourceLoader) {
        return new FreeMarkerConfigurationFactoryBean() {{
            setTemplateLoaderPath("classpath:/templates/");
        }};
    }
}
