package com.robert.spring.jpa.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * @author Roberto Noriega
 * @version 1.0.0
 * @since 20/10/17
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.robert.spring.jpa.rest"))
                .paths(regex("/.*"))
                .build()
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {
        return new ApiInfo(
                "Spring Boot JPA and REST Example API",
                "Spring Boot JPA and REST Example API Documentation",
                "1.0.0",
                "Terms of Service",
                new Contact("Roberto Noriega", "http://robertnrg.github.io",
                        "roberto.noriega@openmailbox.org"),
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0"
        );
    }

}
