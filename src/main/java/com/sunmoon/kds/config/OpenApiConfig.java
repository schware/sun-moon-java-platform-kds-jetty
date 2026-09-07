package com.sunmoon.kds.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI kdsOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("sun-moon-java-platform-kds API")
                        .description("Kitchen Display System — ticket intake for the sun-moon-java-platform family.")
                        .version("0.1.0"))
                .servers(List.of(new Server().url("/kds")));
    }
}
