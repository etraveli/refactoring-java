package com.etraveli.config;

import com.etraveli.config.constant.ConfigurationConstant;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
public class DocumentationConfiguration {
    @Bean
    OpenAPI config() {
        return new OpenAPI().info(
                new Info()
                        .title(ConfigurationConstant.SWAGGER_TITLE)
                        .version(ConfigurationConstant.SWAGGER_VERSION)
                        .description(ConfigurationConstant.SWAGGER_DESCRIPTION)
        );
    }
}
