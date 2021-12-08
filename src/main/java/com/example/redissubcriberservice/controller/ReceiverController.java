package com.example.redissubcriberservice.controller;

import com.example.redissubcriberservice.model.Product;
import com.example.redissubcriberservice.subscriber.Receiver;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReceiverController {

    @GetMapping("/md1")
    public List<Product> getMd(){
        return Receiver.exchangeOneMarketData;
    }

    @GetMapping("/md2")
    public List<Product> getMdTwo(){
        return Receiver.exchangeTwoMarketData;
    }

}
