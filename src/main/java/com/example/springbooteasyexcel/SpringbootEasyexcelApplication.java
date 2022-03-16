package com.example.springbooteasyexcel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan("com.example.springbooteasyexcel")
public class SpringbootEasyexcelApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootEasyexcelApplication.class, args);
    }

}
