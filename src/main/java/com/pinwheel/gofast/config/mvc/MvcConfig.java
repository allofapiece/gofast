package com.pinwheel.gofast.config.mvc;

import com.pinwheel.gofast.service.interceptor.FreeMarkerInterceptor;
import com.pinwheel.gofast.service.interceptor.SecurityInterceptor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/**
 * Spring MVC configuration.
 *
 * @version 1.0.0
 */
@Configuration
@RequiredArgsConstructor
public class MvcConfig implements WebMvcConfigurer {
    @Bean
    public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> webServerCustomizer() {
        return container -> {
            container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/"));
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }

    /**
     * Set up and return {@link ReloadableResourceBundleMessageSource} bean. Set up basename as
     * {@code classpath:i18n/messages}.
     *
     * @return implementation of {@link MessageSource}.
     */
    @Bean
    public MessageSource messageSource() {
        final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:i18n/messages", "classpath:i18n/forms", "classpath:i18n/general");
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setDefaultEncoding("UTF-8");

        return messageSource;
    }

    /**
     * Set up and returns {@link SessionLocaleResolver} bean. Set up default locale as {@code ENGLISH}.
     *
     * @return {@link LocaleResolver} bean.
     */
    @Bean
    public LocaleResolver localeResolver() {
        final SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(Locale.ENGLISH);

        return sessionLocaleResolver;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        final LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        registry.addInterceptor(localeChangeInterceptor);

        final SecurityInterceptor securityInterceptor = new SecurityInterceptor();
        registry.addInterceptor(securityInterceptor);

        final FreeMarkerInterceptor freeMarkerInterceptor = new FreeMarkerInterceptor();
        registry.addInterceptor(freeMarkerInterceptor);
    }

    /**
     * Set up and returns {@link ModelMapper} bean.
     *
     * @return {@link ModelMapper} bean.
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    /**
     * Set up and returns {@link RestTemplate} bean.
     *
     * @return {@link RestTemplate} bean.
     */
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }


}
