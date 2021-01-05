package com.art.aspiringminds.ui.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger specification creates the RESTful contract for API, detailing all of its resources and operations in a human 
 * and machine readable format.
 *
 */
@Configuration
@EnableSwagger2
@ComponentScan(value={"com.art.aspiringminds.rest.controller"})
public class SwaggerConfig {

	/**
	 * docket provides a way to control the end points exposed by Swagger
	 * @return Docket
	 */
    @Bean
    public Docket api(){
    	
    	return new Docket(DocumentationType.SWAGGER_2)
    	          .select()
    	          .apis(RequestHandlerSelectors.basePackage("com.art.aspiringminds.rest.controller"))
    	          .paths(PathSelectors.any())//.paths(PathSelectors.regex("/api/payment/.*"))
    	          .build()
    	          .apiInfo(apiInfo());
    }

    /**
     * @return ApiInfo about project like title, description, version, etc. 
     */
    private ApiInfo apiInfo() {
    	return new ApiInfo(
            "Consumer Portal",
            "Online Payment Portal",
            "1.0",
            "testone",
            "TEAM",
            "Api-Documentation",
            "v2/api-docs"
        );
       
    }
}
