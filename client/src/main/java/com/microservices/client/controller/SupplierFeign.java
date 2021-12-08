package com.microservices.client.controller;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "SUPPLIER-SERVICE")

public interface SupplierFeign {

    @GetMapping("/suppliers")
    int getCounter() ;

}
