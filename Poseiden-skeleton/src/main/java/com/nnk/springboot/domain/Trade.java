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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public Trade(String account, String type) {
        this.account = account;
        this.type = type;
    }

}
