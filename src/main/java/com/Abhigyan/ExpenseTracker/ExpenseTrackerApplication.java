package com.Abhigyan.Expensetracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication is a shortcut for three annotations combined:
//   @Configuration    -> this class can define Spring beans
//   @EnableAutoConfiguration -> Spring Boot auto-configures beans based on
//                                dependencies on the classpath (e.g. seeing
//                                spring-boot-starter-web pulls in an embedded
//                                Tomcat server automatically)
//   @ComponentScan    -> Spring scans this package and sub-packages for
//                        @Component/@Service/@Repository/@RestController
//                        classes and registers them as beans automatically
@SpringBootApplication
public class ExpenseTrackerApplication {

    public static void main(String[] args) {
        // This single call boots the embedded server, wires all beans
        // together (dependency injection), and starts listening on
        // http://localhost:8080
        SpringApplication.run(ExpenseTrackerApplication.class, args);
    }
}
