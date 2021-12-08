package com.microservices.supplier.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.protocol.Message;
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

    @GetMapping("")
    public int countRequest( ) {
        log.info("inside countRequest method of SupplierController");

        return counter++;

    }
    @KafkaListener(topics = "supplier", groupId = "supplier")
    public void kafkaListener(@Payload String message){
        log.info("kafkaListener"+message);
    }

}
