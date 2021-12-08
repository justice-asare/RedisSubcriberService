package com.example.redissubcriberservice.controller;

import com.example.redissubcriberservice.model.Product;
import com.example.redissubcriberservice.subscriber.Receiver;
import lombok.var;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
public class ReceiverController {
    

    @GetMapping("/md{exchange}")
    public List<Product> getMd(@PathVariable("exchange") Integer exchange){
        List<Product> products = new ArrayList<>();
        if(exchange == 1){
         products  = Receiver.exchangeOneMarketData;
        } else if (exchange == 2) {
            products = Receiver.exchangeTwoMarketData;
        }
        return products;
    }


    @GetMapping("/md{exchange}/{tickerType}")
    public Product getMSFTMarketDataOne(@PathVariable("tickerType") String ticker, @PathVariable("exchange") Integer exchange) {
        Product productType = null;
        
        if(exchange == 1){
             productType = Receiver.exchangeOneMarketData.stream()
                    .filter(p -> ticker.toUpperCase(Locale.ROOT).equals(p.getTicker()))
                    .findAny().orElse(null);
        } else if (exchange == 2) {
            productType = Receiver.exchangeTwoMarketData.stream()
                    .filter(p -> ticker.toUpperCase(Locale.ROOT).equals(p.getTicker()))
                    .findAny().orElse(null);
        }
        return productType;
    }

}
