package com.example.rentalapi.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.rentalapi.constants.SwaggerConstant;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;

@Configuration
public class SwaggerConfig {

	 @Bean
	    public OpenAPI movieRentalApiOpenAPI() {
	        return new OpenAPI()
	                .info(new Info()
	                        .title(SwaggerConstant.API_TITLE)
	                        .description(SwaggerConstant.API_DESCRIPTION)
	                        .version(SwaggerConstant.API_VERSION)
	                        .contact(new Contact()
	                                .email(SwaggerConstant.API_CONTACT_EMAIL)
	                                .name(SwaggerConstant.API_CONTACT_NAME)));
	    }
}