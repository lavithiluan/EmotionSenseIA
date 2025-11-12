package com.fiap.gs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.function.context.config.ContextFunctionCatalogAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication(exclude = { ContextFunctionCatalogAutoConfiguration.class })
public class HumorApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(HumorApiApplication.class, args);
    }
}