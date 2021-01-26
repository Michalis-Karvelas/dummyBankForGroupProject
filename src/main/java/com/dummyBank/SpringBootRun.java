package com.dummyBank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SpringBootRun extends SpringBootServletInitializer {

    public static void main(String[] args) {

        SpringApplication.run(SpringBootRun.class, args);
    }
}
