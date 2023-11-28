package org.develdeco.hexagonal.micro_simple.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"org.develdeco.hexagonal"})
@EnableJpaRepositories(basePackages = {"org.develdeco.hexagonal"})
@EntityScan("org.develdeco.hexagonal")
@EnableCaching
public class MsMicroSimpleApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsMicroSimpleApplication.class, args);
    }
}
