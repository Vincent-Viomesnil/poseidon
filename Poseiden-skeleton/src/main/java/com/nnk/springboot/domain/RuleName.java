package com.nnk.springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rulename")
public class RuleName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private int id;
    @Basic
    @Column(name = "name", nullable = true, length = 125)
    private String name;
    @Basic
    @Column(name = "description", nullable = true, length = 125)
    private String description;
    @Basic
    @Column(name = "json", nullable = true, length = 125)
    private String json;
    @Basic
    @Column(name = "template", nullable = true, length = 512)
    private String template;
    @Basic
    @Column(name = "sqlStr", nullable = true, length = 125)
    private String sqlStr;
    @Basic
    @Column(name = "sqlPart", nullable = true, length = 125)
    private String sqlPart;

    public RuleName(String name, String description, String json, String template, String sqlStr, String sql_part) {
        this.name =name;
        this.description=description;
        this.json = json;
        this.template = template;
        this.sqlStr = sqlStr;
        this.sqlPart = sql_part;

    }

}
