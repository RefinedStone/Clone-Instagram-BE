package com.example.cloneinstargram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication
@EnableJpaAuditing
public class CloneInstagramBE {
    public static void main(String[] args) {
        SpringApplication.run(CloneInstagramBE.class, args);
    }
}
