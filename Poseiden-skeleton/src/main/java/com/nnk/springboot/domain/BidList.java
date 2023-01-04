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
@Table(name = "bidlist")
public class BidList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BidListId")
    private int bidListId;
    @Basic
    @Column(name = "account", nullable = false, length = 30)
    private String account;
    @Basic
    @Column(name = "type", nullable = false, length = 30)
    private String type;
    @Basic
    @Column(name = "bidQuantity", nullable = true, precision = 0)
    private Double bidQuantity;
    @Basic
    @Column(name = "askQuantity", nullable = true, precision = 0)
    private Double askQuantity;
    @Basic
    @Column(name = "bid", nullable = true, precision = 0)
    private Double bid;
    @Basic
    @Column(name = "ask", nullable = true, precision = 0)
    private Double ask;
    @Basic
    @Column(name = "benchmark", nullable = true, length = 125)
    private String benchmark;
    @Basic
    @Column(name = "bidListDate", nullable = true)
    private Timestamp bidListDate;
    @Basic
    @Column(name = "commentary", nullable = true, length = 125)
    private String commentary;
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

    public BidList(String account_test, String type_test, double v) {
    }
}
