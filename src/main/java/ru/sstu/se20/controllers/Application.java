package ru.sstu.se20.controllers;

import ru.sstu.se20.common.QueuePuller;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        QueuePuller.scheduleFixedDelayTask();
        SpringApplication.run(Application.class, args);
    }
}