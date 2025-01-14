package com.nnk.springboot.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;


@Getter
@Setter
@Entity
@Table(name = "trade")
public class Trade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "account", nullable = false, length = 30)
    private String account;

    @Column(name = "type", nullable = false, length = 30)
    private String type;

    @Column(name = "buy_quantity", nullable = true, precision = 0)
    private Double buyQuantity;

    @Column(name = "sell_quantity", nullable = true, precision = 0)
    private Double sellQuantity;

    @Column(name = "buy_price", nullable = true, precision = 0)
    private Double buyPrice;

    @Column(name = "sell_price", nullable = true, precision = 0)
    private Double sellPrice;

    @Column(name = "trade_date")
    private Timestamp tradeDate;

    @Column(name = "security", nullable = true, length = 125)
    private String security;

    @Column(name = "status", nullable = true, length = 10)
    private String status;

    @Column(name = "trader", nullable = true, length = 125)
    private String trader;

    @Column(name = "benchmark", nullable = true, length = 125)
    private String benchmark;

    @Column(name = "book", nullable = true, length = 125)
    private String book;

    @Column(name = "creation_name", nullable = true, length = 125)
    private String creationName;

    @Column(name = "creation_date", nullable = true)
    private Timestamp creationDate;

    @Column(name = "revision_name", nullable = true, length = 125)
    private String revisionName;

    @Column(name = "revision_date", nullable = true)
    private Timestamp revisionDate;

    @Column(name = "deal_name", nullable = true, length = 125)
    private String dealName;

    @Column(name = "deal_type", nullable = true, length = 125)
    private String dealType;

    @Column(name = "source_list_id", nullable = true, length = 125)
    private String sourceListId;

    @Column(name = "side", nullable = true, length = 125)
    private String side;


    public Trade() {
    }

    public Trade(String account, String type) {
        this.account = account;
        this.type = type;

    }

    public Trade(Integer id, String account, String type, Double buyQuantity, Double sellQuantity, Double buyPrice, Double sellPrice, Timestamp tradeDate, String security, String status, String trader, String benchmark, String book, String creationName, Timestamp creationDate, String revisionName, Timestamp revisionDate, String dealName, String dealType, String sourceListId, String side) {
        this.id = id;
        this.account = account;
        this.type = type;
        this.buyQuantity = buyQuantity;
        this.sellQuantity = sellQuantity;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.tradeDate = tradeDate;
        this.security = security;
        this.status = status;
        this.trader = trader;
        this.benchmark = benchmark;
        this.book = book;
        this.creationName = creationName;
        this.creationDate = creationDate;
        this.revisionName = revisionName;
        this.revisionDate = revisionDate;
        this.dealName = dealName;
        this.dealType = dealType;
        this.sourceListId = sourceListId;
        this.side = side;
    }
}
