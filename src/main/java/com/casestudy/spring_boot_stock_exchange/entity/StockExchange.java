package com.casestudy.spring_boot_stock_exchange.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "STOCK_EXCHANGE")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockExchange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private boolean liveInMarket;
    @Version
    private int version;

    @ManyToMany
    @JoinTable(name = "stock_exchange_stock",
        joinColumns = @JoinColumn(name = "stock_exchange_id"),
        inverseJoinColumns = @JoinColumn(name = "stock_id"))
    private Set<Stock> assignedStocks = new HashSet<>();
}
