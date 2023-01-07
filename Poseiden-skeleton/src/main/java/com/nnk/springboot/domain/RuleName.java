package com.nnk.springboot.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "rulename")
public class RuleName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "name", nullable = true, length = 125)
    private String name;

    @Column(name = "description", nullable = true, length = 125)
    private String description;

    @Column(name = "json", nullable = true, length = 125)
    private String json;

    @Column(name = "template", nullable = true, length = 512)
    private String template;

    @Column(name = "sql_str", nullable = true, length = 125)
    private String sqlStr;

    @Column(name = "sql_part", nullable = true, length = 125)
    private String sqlPart;

    public RuleName(String name, String description, String json, String template, String sqlStr, String sqlPart) {
        this.name = name;
        this.description = description;
        this.json = json;
        this.template = template;
        this.sqlStr = sqlStr;
        this.sqlPart = sqlPart;

    }
    public RuleName() {
    }
}
