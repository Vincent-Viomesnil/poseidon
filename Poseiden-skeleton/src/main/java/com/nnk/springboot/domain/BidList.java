package com.nnk.springboot.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @Column(name = "account", nullable = false, length = 30)
    @NotNull(message = "account is mandatory")
    private String account;

    @Column(name = "type", nullable = false, length = 30)
    @NotNull(message = "type is mandatory")
    private String type;

    @Column(name = "bidQuantity", nullable = true, precision = 0)
    @NotNull(message = "bidQuantity is mandatory")
    private Double bidQuantity;

    @Column(name = "askQuantity", nullable = true, precision = 0)
    private Double askQuantity;

    @Column(name = "bid", nullable = true, precision = 0)
    private Double bid;

    @Column(name = "ask", nullable = true, precision = 0)
    private Double ask;

    @Column(name = "benchmark", nullable = true, length = 125)
    private String benchmark;

    @Column(name = "bidListDate", nullable = true)
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

    @Column(name = "creationName", nullable = true, length = 125)
    private String creationName;

    @Column(name = "creationDate", nullable = true)
    private Timestamp creationDate;

    @Column(name = "revisionName", nullable = true, length = 125)
    private String revisionName;

    @Column(name = "revisionDate", nullable = true)
    private Timestamp revisionDate;

    @Column(name = "dealName", nullable = true, length = 125)
    private String dealName;

    @Column(name = "dealType", nullable = true, length = 125)
    private String dealType;

    @Column(name = "sourceListId", nullable = true, length = 125)
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
