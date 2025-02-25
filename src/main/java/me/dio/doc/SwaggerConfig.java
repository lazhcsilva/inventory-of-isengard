package me.dio.doc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Isengard Supermarket")
                        .description("Project created for the Santander Bootcamp in partnership with Digital Innovation One")
                        .version("1.0")
                        .termsOfService("Term of use: Open Source")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://www.test.com.br")));
    }

}
