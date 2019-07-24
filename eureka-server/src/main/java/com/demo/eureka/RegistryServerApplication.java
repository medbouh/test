package com.demo.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * serveur d'annuaire permettant d'enregistrer les adresses des différents services
 *
 * @author mohamed bouhamyd
 */
@EnableEurekaServer
@SpringBootApplication
public class RegistryServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegistryServerApplication.class, args);
    }
}
