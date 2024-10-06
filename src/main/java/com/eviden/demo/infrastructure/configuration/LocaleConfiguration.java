/* EVIDEN (C)2023 */
package com.eviden.demo.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class LocaleConfiguration {

    @Bean(name = "messageSource")
    ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("i18n.messages");
        messageSource.setDefaultEncoding("UTF-8");

        return messageSource;
    }
}
