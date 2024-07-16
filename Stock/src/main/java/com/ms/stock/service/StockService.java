package com.ms.stock.service;

import com.ms.stock.model.Stock;

import java.util.List;
import java.util.Optional;

public interface StockService {
    List<Stock> findAll();
    Optional<Stock> findById(String id);
    Stock save(Stock stock);
    void deleteById(String id);
}
