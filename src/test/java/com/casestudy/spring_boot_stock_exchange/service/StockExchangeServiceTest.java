package com.casestudy.spring_boot_stock_exchange.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.HashSet;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.casestudy.spring_boot_stock_exchange.entity.Stock;
import com.casestudy.spring_boot_stock_exchange.entity.StockExchange;
import com.casestudy.spring_boot_stock_exchange.repository.StockExchangeRepository;

@ExtendWith(MockitoExtension.class)
class StockExchangeServiceTest {
    @InjectMocks
    private StockExchangeService stockExchangeService;

    @Mock
    private StockExchangeRepository stockExchangeRepository;

    private static Stock stock;

    private static StockExchange stockExchange;

    private final static double PRICE = 100.0;

    @BeforeAll
    static void setUp() {
        stock = prepareStock();
        stockExchange = prepareStockExchange();
    }

    @Test
    void getStockExchangeWithAllStock_validRequest_shouldReturnOk() {
        when(stockExchangeRepository.findByName(stockExchange.getName())).thenReturn(
            java.util.Optional.of(stockExchange));

        stockExchangeService.getStockExchangeWithAllStock(stockExchange.getName());

        verify(stockExchangeRepository, times(1)).findByName(stockExchange.getName());
    }

    @Test
    void addStockToStockExchange_validRequest_shouldReturnOk() {
        when(stockExchangeRepository.findByName(stockExchange.getName())).thenReturn(
            java.util.Optional.of(stockExchange));
        when(stockExchangeRepository.save(stockExchange)).thenReturn(stockExchange);

        stockExchangeService.addStockToStockExchange(stockExchange.getName(), stock);

        verify(stockExchangeRepository, times(1)).findByName(stockExchange.getName());
        verify(stockExchangeRepository, times(1)).save(stockExchange);
        assertEquals(1, stockExchange.getAssignedStocks().size());
        assertFalse(stockExchange.isLiveInMarket());
        assertTrue(stockExchange.getAssignedStocks().contains(stock));
    }

    @Test
    void deleteStockFromStockExchange_validRequest_shouldReturnOk() {
        when(stockExchangeRepository.findByName(stockExchange.getName())).thenReturn(
            java.util.Optional.of(stockExchange));
        when(stockExchangeRepository.save(stockExchange)).thenReturn(stockExchange);

        stockExchangeService.deleteStockFromStockExchange(stockExchange.getName(), stock);

        verify(stockExchangeRepository, times(1)).findByName(stockExchange.getName());
        verify(stockExchangeRepository, times(1)).save(stockExchange);
        assertEquals(0, stockExchange.getAssignedStocks().size());
        assertFalse(stockExchange.isLiveInMarket());
        assertFalse(stockExchange.getAssignedStocks().contains(stock));
    }

    private static Stock prepareStock() {
        return Stock.builder()
            .id(1)
            .name("Test Stock")
            .currentPrice(PRICE)
            .lastUpdate(new Timestamp(System.currentTimeMillis()))
            .build();
    }

    private static StockExchange prepareStockExchange() {
        return StockExchange.builder().id(1).name("name").assignedStocks(new HashSet<>()).build();
    }
}
