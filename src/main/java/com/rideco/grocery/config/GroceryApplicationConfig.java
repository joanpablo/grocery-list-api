package com.rideco.grocery.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class GroceryApplicationConfig implements WebMvcConfigurer {

    private Environment env;

    @Autowired
    GroceryApplicationConfig(Environment env) {
        this.env = env;
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.addPathPrefix("/rest", HandlerTypePredicate.forAnnotation(RestController.class));
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/rest/**")
                .allowedOrigins(this.getOrigins())
                .allowedMethods("GET", "POST", "DELETE", "PUT");
    }

    private String[] getOrigins() {
        String[] origins = env.getProperty("cors.origin", String[].class);
        return origins != null ? origins : new String[]{};
    }
}
