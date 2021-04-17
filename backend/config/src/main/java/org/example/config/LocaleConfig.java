package org.example.config;

import org.example.constant.HeaderConstants;
import org.example.constant.LocaleConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Configuration
public class LocaleConfig extends AcceptHeaderLocaleResolver implements WebMvcConfigurer {
    private final List<Locale> LOCALES = Arrays.asList(
            new Locale(LocaleConstants.ENGLISH),
            new Locale(LocaleConstants.VIETNAM));

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String headerLang = request.getHeader(HeaderConstants.ACCEPT_LANGUAGE);
        return headerLang == null || headerLang.isEmpty()
                ? Locale.getDefault()
                : Locale.lookup(Locale.LanguageRange.parse(headerLang), LOCALES);
    }

    @Bean
    public ResourceBundleMessageSource messageSource() {
        return new ResourceBundleMessageSource() {{
            setBasename("messages");
            setDefaultEncoding("UTF-8");
            setUseCodeAsDefaultMessage(true);
        }};
    }
}