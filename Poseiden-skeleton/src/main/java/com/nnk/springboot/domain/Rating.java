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
@Table(name = "rating")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private int id;
    @Basic
    @Column(name = "moodysRating", nullable = true, length = 125)
    private String moodysRating;
    @Basic
    @Column(name = "sandPRating", nullable = true, length = 125)
    private String sandPRating;
    @Basic
    @Column(name = "fitchRating", nullable = true, length = 125)
    private String fitchRating;
    @Basic
    @Column(name = "orderNumber", nullable = true)
    private Integer orderNumber;

    public Rating(String moodysRating, String sandPRating, String fitchRating, Integer orderNumber) {
    }
}
