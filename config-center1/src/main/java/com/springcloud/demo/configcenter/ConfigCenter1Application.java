package com.springcloud.demo.configcenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigServer
public class ConfigCenter1Application {

    public static void main(String[] args) {
        SpringApplication.run(ConfigCenter1Application.class, args);
    }

}
