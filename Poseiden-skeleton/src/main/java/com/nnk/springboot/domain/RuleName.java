package com.nnk.springboot.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
@Table(name = "rulename")
public class RuleName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;
    @NotBlank(message = "name is mandatory")
    @Column(name = "name", nullable = true, length = 125)
    private String name;
    @NotBlank(message = "description is mandatory")
    @Column(name = "description", nullable = true, length = 125)
    private String description;
    @NotBlank(message = "json is mandatory")
    @Column(name = "json", nullable = true, length = 125)
    private String json;
    @NotBlank(message = "template is mandatory")
    @Column(name = "template", nullable = true, length = 512)
    private String template;
    @NotBlank(message = "sqlStr is mandatory")
    @Column(name = "sqlStr", nullable = true, length = 125)
    private String sqlStr;
    @NotBlank(message = "sqlPart is mandatory")
    @Column(name = "sqlPart", nullable = true, length = 125)
    private String sqlPart;

    public RuleName(String name, String description, String json, String template, String sqlStr, String sql_part) {
        this.name = name;
        this.description = description;
        this.json = json;
        this.template = template;
        this.sqlStr = sqlStr;
        this.sqlPart = sql_part;

    }
    public RuleName() {
    }
}
