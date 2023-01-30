package com.nnk.springboot.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "bidlist")
public class BidList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "account",nullable = true, length = 30)
    private String account;

    @Column(name = "type",nullable = true, length = 30)
    private String type;

    @Column(name = "bid_quantity",nullable = true, precision = 0)
    private Double bidQuantity;

    @Column(name = "ask_quantity", nullable = true, precision = 0)
    private Double askQuantity;

    @Column(name = "bid", nullable = true, precision = 0)
    private Double bid;

    @Column(name = "ask", nullable = true, precision = 0)
    private Double ask;

    @Column(name = "benchmark", nullable = true, length = 125)
    private String benchmark;

    @Column(name = "bid_list_date", nullable = true)
    private Timestamp bidListDate;

    @Column(name = "commentary", nullable = true, length = 125)
    private String commentary;

    @Column(name = "security", nullable = true, length = 125)
    private String security;

    @Column(name = "status", nullable = true, length = 10)
    private String status;

    @Column(name = "trader", nullable = true, length = 125)
    private String trader;

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

    public BidList(String account, String type, Double bidQuantity) {
        this.account = account;
        this.type= type;
        this.bidQuantity = bidQuantity;
    }

    public BidList() {
    }
}
