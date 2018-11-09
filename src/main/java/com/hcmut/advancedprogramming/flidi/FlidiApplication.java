package com.hcmut.advancedprogramming.flidi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.hcmut.advancedprogramming.flidi.persistence.model")
@SpringBootApplication
public class FlidiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlidiApplication.class, args);
    }
}
