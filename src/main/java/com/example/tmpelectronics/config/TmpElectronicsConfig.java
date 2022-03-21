package com.example.tmpelectronics.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import java.util.Locale;

public class TmpElectronicsConfig {

    @Bean
    public LocaleResolver localeResolver() {
            Locale.setDefault(Locale.forLanguageTag("ru"));
            CookieLocaleResolver slr = new CookieLocaleResolver();
            slr.setDefaultLocale(Locale.forLanguageTag("ru"));
            return slr;
        }

        @Bean
        public LocaleChangeInterceptor localeChangeInterceptor() {
            LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
            lci.setParamName("lang");
            return lci;
        }

        }

