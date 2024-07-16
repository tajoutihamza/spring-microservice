package com.ms.article.service;

import com.ms.article.dto.StockDto;
import com.ms.article.feign.IStockClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StockService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private IStockClient stockClient;



    private static final String STOCK_SERVICE_URL = "http://localhost:8080/stocks/";

    @CircuitBreaker(name = "stockServiceCircuitBreaker", fallbackMethod = "getDefaultStock")
    public StockDto getStockByIdRest(String id) {
        return restTemplate.getForObject(STOCK_SERVICE_URL + id, StockDto.class);
    }
    @CircuitBreaker(name = "stockCircuitBreaker", fallbackMethod = "getDefaultStock")
    public StockDto getStockById(String id) {
        return stockClient.getStockById(id);
    }
    public StockDto getDefaultStock(String id, Throwable throwable) {
        return new StockDto(id,"Zone is not Available");

    }
}
