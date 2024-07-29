package com.casestudy.spring_boot_stock_exchange.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.casestudy.spring_boot_stock_exchange.entity.Stock;
import com.casestudy.spring_boot_stock_exchange.service.StockService;

@ExtendWith(MockitoExtension.class)
class StockControllerTest {
    @InjectMocks
    private StockController stockController;

    @Mock
    private StockService stockService;

    @Test
    void createStock_validRequest_shouldReturnCreated() {
        doNothing().when(stockService).saveStock(any(Stock.class));
        ResponseEntity<Stock> response = stockController.createStock(new Stock());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void updateStockPrice_validRequest_shouldReturnOk() {
        doNothing().when(stockService).updateStockPrice(anyInt(), anyDouble());
        ResponseEntity<Stock> response = stockController.updateStockPrice(anyInt(), anyDouble());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void deleteStockFromSystem_validRequest_shouldReturnOk() {
        doNothing().when(stockService).deleteStock(anyInt());
        ResponseEntity<Stock> response = stockController.deleteStockFromSystem(anyInt());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
