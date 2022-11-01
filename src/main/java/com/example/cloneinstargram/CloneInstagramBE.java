package com.example.cloneinstargram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CloneInstagramBE {
    public static void main(String[] args) {
        SpringApplication.run(CloneInstagramBE.class, args);
    }
}