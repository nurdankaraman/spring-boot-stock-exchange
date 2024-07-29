package com.casestudy.spring_boot_stock_exchange.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.casestudy.spring_boot_stock_exchange.entity.StockExchange;
import com.casestudy.spring_boot_stock_exchange.service.StockExchangeService;

@ExtendWith(MockitoExtension.class)
class StockExchangeControllerTest {
    @InjectMocks
    private StockExchangeController stockExchangeController;

    @Mock
    private StockExchangeService stockExchangeService;
    private static StockExchange stockExchange;

    @BeforeAll
    static void setUp() {
        stockExchange = prepareStockExchange();
    }

    @Test
    void getStockExchangeWithAllStock_validRequest_shouldReturnOk() {
        when(stockExchangeService.getStockExchangeWithAllStock(anyString())).thenReturn(
            Optional.ofNullable(stockExchange));
        Optional<StockExchange> response = stockExchangeController.getStockExchangeWithAllStock(anyString());
        assertTrue(response.isPresent());
        assert stockExchange != null;
        assertEquals(stockExchange.getName(), response.get().getName());
        assertEquals(stockExchange.getDescription(), response.get().getDescription());
        assertEquals(stockExchange.isLiveInMarket(), response.get().isLiveInMarket());

    }

    @Test
    void addStockToStockExchange_validRequest_shouldReturnOk() {
        when(stockExchangeService.addStockToStockExchange(anyString(), any())).thenReturn(stockExchange);
        StockExchange response = stockExchangeController.addStockToStockExchange(anyString(), any());
        assertEquals(stockExchange.getName(), response.getName());
        assertEquals(stockExchange.getDescription(), response.getDescription());
        assertEquals(stockExchange.isLiveInMarket(), response.isLiveInMarket());
    }

    private static StockExchange prepareStockExchange() {
        return StockExchange.builder().name("name").description("desc").liveInMarket(false).build();
    }

    @Test
    void deleteStockFromStockExchange_validRequest_shouldReturnOk() {
        when(stockExchangeService.deleteStockFromStockExchange(anyString(), any())).thenReturn(stockExchange);
        StockExchange response = stockExchangeController.deleteStockFromStockExchange(anyString(), any());
        assertEquals(stockExchange.getName(), response.getName());
        assertEquals(stockExchange.getDescription(), response.getDescription());
        assertEquals(stockExchange.isLiveInMarket(), response.isLiveInMarket());
    }
}
