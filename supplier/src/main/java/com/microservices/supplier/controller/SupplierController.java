package com.microservices.supplier.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/suppliers")
@Slf4j
public class SupplierController {

    int counter;
    boolean willCommit = false;

    @GetMapping("")
    public int countRequest( ) {
        log.info("inside countRequest method of SupplierController");

        return counter++;

    }
    @KafkaListener(topics = "supplier", groupId = "supplier")
    public void kafkaListener(@Payload String message){
        log.info("kafkaListener" + message);

        if (willCommit)
        throw new IllegalStateException();

    }
    @GetMapping("/changeCommit")
    public void changeCommit() {
        //gonna change the result true --> false  or false --> true
        willCommit= !willCommit;


        log.info("inside changeCommit SupplierController");
    }

}
