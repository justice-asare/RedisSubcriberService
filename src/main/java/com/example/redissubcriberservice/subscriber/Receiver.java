package com.example.redissubcriberservice.subscriber;

import com.example.redissubcriberservice.model.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class Receiver {

    public static List<Product> exchangeOneMarketData = new ArrayList<>();
    public static List<Product> exchangeTwoMarketData = new ArrayList<>();
    Logger logger = LoggerFactory.getLogger(Receiver.class);
    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    RestTemplate restTemplate;

    @PostConstruct
    public void sendInitialData() {
        exchangeOneMarketData = restTemplate.getForObject("https://exchange.matraining.com/md", List.class);
        exchangeTwoMarketData = restTemplate.getForObject("https://exchange2.matraining.com/md", List.class);
    }


    public void marketDataFromExchangeOne(String message) throws JsonProcessingException {
        Product[] productList = objectMapper.readValue(message, Product[].class);
        exchangeOneMarketData = Arrays.asList(productList);
        logger.info("Consumed message from exchange one{}", exchangeOneMarketData);
    }


    public void marketDataFromExchangeTwo(String message) throws JsonProcessingException {
        Product[] productList = objectMapper.readValue(message, Product[].class);
        exchangeTwoMarketData = Arrays.asList(productList);
        logger.info("Consumed message from exchange two {}", exchangeTwoMarketData);
    }

}

