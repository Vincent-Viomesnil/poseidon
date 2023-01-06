package com.nnk.springboot.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "rating")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "moodysRating", nullable = true, length = 125)
    private String moodysRating;

    @Column(name = "sandPRating", nullable = true, length = 125)
    private String sandPRating;

    @Column(name = "fitchRating", nullable = true, length = 125)
    private String fitchRating;

    @Column(name = "orderNumber", nullable = true)
    private Integer orderNumber;

    public Rating(String moodysRating, String sandPRating, String fitchRating, Integer orderNumber) {
        this.moodysRating = moodysRating;
        this.sandPRating= sandPRating;
        this.fitchRating =fitchRating;
        this.orderNumber= orderNumber;
    }

    public Rating() {

    }
}
