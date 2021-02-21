package org.example.config

import org.example.constant.HeaderConstants
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ResourceBundleMessageSource
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver
import java.util.*
import javax.servlet.http.HttpServletRequest

@Configuration
class LocalResolverConfig : AcceptHeaderLocaleResolver(), WebMvcConfigurer {
    private var locales = listOf(
        Locale("en"),
        Locale("vn")
    )

    override fun resolveLocale(request: HttpServletRequest): Locale {
        val headerLang = request.getHeader(HeaderConstants.ACCEPT_LANGUAGE)
        return if (headerLang == null || headerLang.isEmpty()) Locale.getDefault() else Locale.lookup(
            Locale.LanguageRange.parse(
                headerLang
            ), locales
        )
    }

    @Bean
    fun messageSource(): ResourceBundleMessageSource {
        val rs = ResourceBundleMessageSource()
        rs.setBasename("messages")
        rs.setDefaultEncoding("UTF-8")
        rs.setUseCodeAsDefaultMessage(true)
        return rs
    }
}