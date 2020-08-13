package com.web.blog.model.temp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.Entity;

import lombok.*;

@NoArgsConstructor
@Entity
@ToString
@Table(name = "apply_date")
public class Temp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adid")
    private int adid;

    @Column(name = "date", nullable = false)
    private String date;

    @Builder
    public Temp(final int adid, final String date) {
        this.adid = adid;
        this.date = date;
    }

}