package com.casestudy.spring_boot_stock_exchange.service;

import java.sql.Timestamp;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.casestudy.spring_boot_stock_exchange.entity.Stock;
import com.casestudy.spring_boot_stock_exchange.repository.StockRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class StockService {
    private final StockRepository stockRepository;

    public void saveStock(Stock stock) {
        boolean exists = stockRepository.existsByName(stock.getName());
        if (exists) {
            log.info("Stock already exists");
            return;
        }
        stockRepository.save(stock);

    }

    public void updateStockPrice(Integer id, double price) {
        Stock stock = stockRepository.findById(id).orElseThrow(() -> new RuntimeException("Stock not found"));
        stock.setCurrentPrice(price);
        stock.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        stockRepository.save(stock);
    }

    public void deleteStock(int id) {
        stockRepository.deleteById(id);
    }
}
