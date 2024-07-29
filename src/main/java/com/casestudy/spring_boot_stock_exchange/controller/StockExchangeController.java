package com.casestudy.spring_boot_stock_exchange.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.spring_boot_stock_exchange.entity.Stock;
import com.casestudy.spring_boot_stock_exchange.entity.StockExchange;
import com.casestudy.spring_boot_stock_exchange.service.StockExchangeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/stock-exchange")
@RequiredArgsConstructor
public class StockExchangeController {

    private final StockExchangeService stockExchangeService;

    @GetMapping("/{name}")
    public Optional<StockExchange> getStockExchangeWithAllStock(@PathVariable String name) {
        return stockExchangeService.getStockExchangeWithAllStock(name);
    }

    @PutMapping("/{name}")
    public StockExchange addStockToStockExchange(@PathVariable String name, @RequestBody Stock stock) {
        return stockExchangeService.addStockToStockExchange(name, stock);
    }

    @DeleteMapping("/{name}")
    public StockExchange deleteStockFromStockExchange(@PathVariable String name, @RequestBody Stock stock) {
        return stockExchangeService.deleteStockFromStockExchange(name, stock);
    }
}
