package com.casestudy.spring_boot_stock_exchange.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.spring_boot_stock_exchange.entity.Stock;
import com.casestudy.spring_boot_stock_exchange.service.StockService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/stock")
@RequiredArgsConstructor
public class StockController {
    private final StockService stockService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Stock> createStock(@RequestBody Stock stock) {
        stockService.saveStock(stock);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Stock> updateStockPrice(@RequestParam(value = "id") Integer id, @RequestParam double price) {
        stockService.updateStockPrice(id, price);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Stock> deleteStockFromSystem(@RequestParam(value = "id") Integer id) {
        stockService.deleteStock(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
