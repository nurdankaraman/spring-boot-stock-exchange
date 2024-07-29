package com.casestudy.spring_boot_stock_exchange.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.casestudy.spring_boot_stock_exchange.entity.Stock;
import com.casestudy.spring_boot_stock_exchange.repository.StockRepository;

@ExtendWith(MockitoExtension.class)
class StockServiceTest {
    @InjectMocks
    private StockService stockService;

    @Mock
    private StockRepository stockRepository;

    private static Stock stock;

    private final static double PRICE = 100.0;

    @BeforeAll
    static void setUp() {
        stock = prepareStock();
    }

    @Test
    void saveStock_validRequest_shouldReturnOk() {
        when(stockRepository.existsByName(stock.getName())).thenReturn(false);
        when(stockRepository.save(stock)).thenReturn(stock);

        stockService.saveStock(stock);

        verify(stockRepository, times(1)).existsByName(stock.getName());
        verify(stockRepository, times(1)).save(stock);
    }

    private static Stock prepareStock() {
        return Stock.builder()
            .id(1)
            .name("Test Stock")
            .currentPrice(PRICE)
            .lastUpdate(new Timestamp(System.currentTimeMillis()))
            .build();
    }

    @Test
    void updateStockPrice_validRequest_shouldReturnOk() {
        when(stockRepository.findById(stock.getId())).thenReturn(java.util.Optional.of(stock));
        when(stockRepository.save(stock)).thenReturn(stock);

        stockService.updateStockPrice(stock.getId(), PRICE);

        verify(stockRepository, times(1)).findById(stock.getId());
        verify(stockRepository, times(1)).save(stock);
    }

    @Test
    void deleteStock_validRequest_shouldReturnOk() {
        stockService.deleteStock(stock.getId());

        verify(stockRepository, times(1)).deleteById(stock.getId());
    }

}
