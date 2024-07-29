package com.casestudy.spring_boot_stock_exchange.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casestudy.spring_boot_stock_exchange.entity.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {
    boolean existsByName(String name);
}
