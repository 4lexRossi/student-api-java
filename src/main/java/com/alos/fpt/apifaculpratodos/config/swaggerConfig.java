package com.alos.fpt.apifaculpratodos.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static springfox.documentation.builders.PathSelectors.regex;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class swaggerConfig {

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.alos.fpt.apifaculpratodos"))
            .paths(regex("/api/v1/facul-pra-todos.*"))
            .build()
            .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {
        ApiInfo apiInfo = new ApiInfo(
            "Student Api Rest",
            "Api para pré-cadastro de usuários",
            "1.0",
            "Terms of Service",
            new Contact(
                "Alex Rossi", "https://4lexrossi.github.io/",
                "devalexrossi@gmail.com"),
                "MIT",
                "https://github.com/4lexRossi/student-api-java",
                new ArrayList<VendorExtension>()
        );

        return apiInfo;
    }
}
