/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.panono.panorama.entity;

import com.panono.panorama.Default;
import java.util.Collection;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static java.util.stream.Collectors.toList;

/**
 *
 * @author uday
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Statistic {

    private Double sum;

    private Double avg;

    private Double max;

    private Double min;

    private Long count;

    public Statistic() {
    }

    public Statistic(Double sum, Double avg, Double max, Double min, Long count) {
        this.sum = sum;
        this.avg = avg;
        this.max = max;
        this.min = min;
        this.count = count;
    }

    public Statistic(Collection<Transaction> transactions) {
        createStatistic(transactions);
    }

    private void createStatistic(Collection<Transaction> transactions) {
        List<Double> lastMinuteAmount = transactions.stream().map((transaction) -> transaction.getCount()).collect(toList());
        Long amount = lastMinuteAmount.stream().count();
        this.setCount(amount);
        if (amount > 0) {
            double maxValue = lastMinuteAmount.stream().max(Double::compareTo).get();
            this.setMin(Default.MIN_VAULE);
            this.setSum(maxValue + min);
            this.setMax(maxValue);
            this.setAvg((Double) Math.floor((maxValue + min) / max));
        } else {
            this.setMin(Double.NaN);
            this.setSum(Double.NaN);
            this.setMax(Double.NaN);
            this.setAvg(Double.NaN);
        }
    }

}
