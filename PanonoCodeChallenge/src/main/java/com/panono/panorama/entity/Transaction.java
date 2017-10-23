/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.panono.panorama.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.panono.panorama.Default;
import com.panono.panorama.validation.TillLast;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static java.lang.System.currentTimeMillis;

/**
 *
 * @author uday
 */
@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Transaction implements Serializable {

    public Transaction() {
    }

    public Transaction(Double count, Long timestamp) {
        this.count = count;
        this.timestamp = timestamp;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private Double count;

    @NotNull
    @TillLast
    private Long timestamp;

    @JsonIgnore
    public boolean isFutureTimeStamp() {
        return currentTimeMillis() - getTimestamp() <= Default.TIME_LIMIT;
    }

}
