package com.example.Ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;
import java.util.Base64;

@SpringBootApplication
public class EcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
       /* byte[] key= new byte[32];
        new SecureRandom().nextBytes(key);
        System.out.println(Base64.getEncoder().encodeToString(key));*/
	}
//7bPHNxm2dC9iU4YvVflMp2u1hS8



    /*
    version: "3.9"

services:
    mysql:
        image: mysql:8.0
        container_name: ecommerce-mysql
        environment:
            MYSQL_DATABASE: ecommercedb
            MYSQL_ROOT_PASSWORD: olusola
        ports:
            - "3306:3306"
        volumes:
            - mysql_data:/var/lib/mysql

    backend:
        build: .
        container_name: ecommerce-backend
        ports:
            - "8080:8080"
        environment:
            SPRING_DATASOURCE_URL: jdbc:mysql://mysql:3306/ecommercedb
            SPRING_DATASOURCE_USERNAME: root
            SPRING_DATASOURCE_PASSWORD: olusola
            SPRING_DATASOURCE_DRIVER_CLASS_NAME: com.mysql.cj.jdbc.Driver
            SPRING_JPA_HIBERNATE_DDL_AUTO: update
        depends_on:
            - mysql

volumes:
    mysql_data:
    */
}
