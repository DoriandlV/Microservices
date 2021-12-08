package com.microservices.client.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@RestController
@RequestMapping("/clients")
//@EnableFeignClients
@Slf4j
public class ClientController {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    SupplierFeign supplierFeign;

    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;

    @GetMapping("/feign")
    public int getCounter(){

        log.info("inside countRequest method of SupplierController");
        return supplierFeign.getCounter();

    }

    @GetMapping("/restTemplate")
    public int restTemplate(){

        ResponseEntity<Integer> forEntity = restTemplate.getForEntity("http://SUPPLIER-SERVICE/suppliers", Integer.class);
        log.info("inside restTemplate");
        return forEntity.getBody();
    }

    @GetMapping("/kafka")
    public void kafka() {
        kafkaTemplate.send("supplier",UUID.randomUUID().toString(),"Hello World!");
    }
}

