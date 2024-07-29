package com.casestudy.spring_boot_stock_exchange.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.casestudy.spring_boot_stock_exchange.entity.Stock;
import com.casestudy.spring_boot_stock_exchange.entity.StockExchange;
import com.casestudy.spring_boot_stock_exchange.repository.StockExchangeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Transactional
@Service
@RequiredArgsConstructor
@Slf4j
public class StockExchangeService {

    private final StockExchangeRepository stockExchangeRepository;

    public Optional<StockExchange> getStockExchangeWithAllStock(String name) {
        return stockExchangeRepository.findByName(name);
    }

    public StockExchange addStockToStockExchange(String name, Stock stock) {
        try {
            Optional<StockExchange> stockExchangeEntity = getStockExchangeWithAllStock(name);
            if (stockExchangeEntity.isEmpty()) {
                log.info("Stock exchange not found");
                return new StockExchange();
            }
            Set<Stock> stockSet = stockExchangeEntity.get().getAssignedStocks();
            stockSet.add(stock);
            stockExchangeEntity.get().setAssignedStocks(stockSet);
            return saveStockExchange(stockExchangeEntity.get());
        } catch (Exception e) {
            log.info(
                "There is a problem with adding stock to the stock exchange. Exception: " + e.getMessage());
            return null;
        }
    }

    private StockExchange saveStockExchange(StockExchange stockExchange) {
        stockExchange.setLiveInMarket(stockExchange.getAssignedStocks().size() >= 5);
        return stockExchangeRepository.save(stockExchange);
    }

    public StockExchange deleteStockFromStockExchange(String name, Stock stock) {
        try {
            Optional<StockExchange> stockExchangeEntity = getStockExchangeWithAllStock(name);
            if (stockExchangeEntity.isEmpty()) {
                log.info("Stock exchange not found");
                return new StockExchange();
            }
            Set<Stock> stockSet = stockExchangeEntity.get().getAssignedStocks();
            stockSet.remove(stock);
            stockExchangeEntity.get().setAssignedStocks(stockSet);
            return saveStockExchange(stockExchangeEntity.get());
        } catch (Exception e) {
            log.error(
                "There is a problem with deleting stock from the stock exchange. Exception: " + e.getMessage());
            return null;
        }
    }
}
