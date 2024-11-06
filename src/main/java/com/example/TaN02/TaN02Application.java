package com.example.TaN02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.example.TaN02.controller", "com.example.TaN02.service"})
@EntityScan("com.example.TaN02.entity")
@EnableJpaRepositories("com.example.TaN02.repository")
public class TaN02Application {

    public static void main(String[] args) {
        SpringApplication.run(TaN02Application.class, args);
    }

}
