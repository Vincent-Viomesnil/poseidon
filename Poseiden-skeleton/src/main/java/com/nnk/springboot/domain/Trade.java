package com.nnk.springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "trade")
public class Trade {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "TradeId", nullable = false)
    private int tradeId;
    @Basic
    @Column(name = "account", nullable = false, length = 30)
    private String account;
    @Basic
    @Column(name = "type", nullable = false, length = 30)
    private String type;
    @Basic
    @Column(name = "buyQuantity", nullable = true, precision = 0)
    private Double buyQuantity;
    @Basic
    @Column(name = "sellQuantity", nullable = true, precision = 0)
    private Double sellQuantity;
    @Basic
    @Column(name = "buyPrice", nullable = true, precision = 0)
    private Double buyPrice;
    @Basic
    @Column(name = "sellPrice", nullable = true, precision = 0)
    private Double sellPrice;
    @Basic
    @Column(name = "tradeDate")
    private Timestamp tradeDate;
    @Basic
    @Column(name = "security", nullable = true, length = 125)
    private String security;
    @Basic
    @Column(name = "status", nullable = true, length = 10)
    private String status;
    @Basic
    @Column(name = "trader", nullable = true, length = 125)
    private String trader;
    @Basic
    @Column(name = "benchmark", nullable = true, length = 125)
    private String benchmark;
    @Basic
    @Column(name = "book", nullable = true, length = 125)
    private String book;
    @Basic
    @Column(name = "creationName", nullable = true, length = 125)
    private String creationName;
    @Basic
    @Column(name = "creationDate", nullable = true)
    private Timestamp creationDate;
    @Basic
    @Column(name = "revisionName", nullable = true, length = 125)
    private String revisionName;
    @Basic
    @Column(name = "revisionDate", nullable = true)
    private Timestamp revisionDate;
    @Basic
    @Column(name = "dealName", nullable = true, length = 125)
    private String dealName;
    @Basic
    @Column(name = "dealType", nullable = true, length = 125)
    private String dealType;
    @Basic
    @Column(name = "sourceListId", nullable = true, length = 125)
    private String sourceListId;
    @Basic
    @Column(name = "side", nullable = true, length = 125)
    private String side;

    public Trade(String trade_account, String type) {
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Trade trade = (Trade) o;
//        return tradeId == trade.tradeId && Objects.equals(account, trade.account) && Objects.equals(type, trade.type) && Objects.equals(buyQuantity, trade.buyQuantity) && Objects.equals(sellQuantity, trade.sellQuantity) && Objects.equals(buyPrice, trade.buyPrice) && Objects.equals(sellPrice, trade.sellPrice) && Objects.equals(tradeDate, trade.tradeDate) && Objects.equals(security, trade.security) && Objects.equals(status, trade.status) && Objects.equals(trader, trade.trader) && Objects.equals(benchmark, trade.benchmark) && Objects.equals(book, trade.book) && Objects.equals(creationName, trade.creationName) && Objects.equals(creationDate, trade.creationDate) && Objects.equals(revisionName, trade.revisionName) && Objects.equals(revisionDate, trade.revisionDate) && Objects.equals(dealName, trade.dealName) && Objects.equals(dealType, trade.dealType) && Objects.equals(sourceListId, trade.sourceListId) && Objects.equals(side, trade.side);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(tradeId, account, type, buyQuantity, sellQuantity, buyPrice, sellPrice, tradeDate, security, status, trader, benchmark, book, creationName, creationDate, revisionName, revisionDate, dealName, dealType, sourceListId, side);
//    }
}
