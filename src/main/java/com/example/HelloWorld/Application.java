package com.example.HelloWorld;

import common.EventPoller;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        EventPoller.scheduleFixedDelayTask();
        SpringApplication.run(Application.class, args);
    }
}