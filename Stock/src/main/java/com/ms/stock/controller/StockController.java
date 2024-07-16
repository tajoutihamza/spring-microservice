package com.ms.stock.controller;

import com.ms.stock.model.Stock;
import com.ms.stock.service.StockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stocks")
public class StockController {


    private final StockService stockService;
    public StockController(StockService stockService){
        this.stockService = stockService;
    }

    @GetMapping
    public List<Stock> getAllStocks() {
        return stockService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stock> getStockById(@PathVariable String id) {
        return stockService.findById(id)
                .map(stock -> ResponseEntity.ok().body(stock))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Stock createStock(@RequestBody Stock stock) {
        return stockService.save(stock);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Stock> updateStock(@PathVariable String id, @RequestBody Stock stockDetails) {
        return stockService.findById(id)
                .map(stock -> {
                    stock.setZone(stockDetails.getZone());
                    Stock updatedStock = stockService.save(stock);
                    return ResponseEntity.ok().body(updatedStock);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteStock(@PathVariable String id) {
        return stockService.findById(id)
                .map(stock -> {
                    stockService.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
