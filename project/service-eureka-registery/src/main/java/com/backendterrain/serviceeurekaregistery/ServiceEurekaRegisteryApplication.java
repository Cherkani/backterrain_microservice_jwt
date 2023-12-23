package com.backendterrain.serviceeurekaregistery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@SpringBootApplication
@EnableEurekaServer
public class ServiceEurekaRegisteryApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceEurekaRegisteryApplication.class, args);
    }

}
