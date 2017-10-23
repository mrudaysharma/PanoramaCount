/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.panono.panorama.service;

import com.panono.panorama.Default;
import com.panono.panorama.TimestampComparator;
import com.panono.panorama.entity.Statistic;
import com.panono.panorama.entity.Transaction;
import org.springframework.stereotype.Service;

import java.util.concurrent.PriorityBlockingQueue;

import lombok.Getter;

/**
 * 
 * @author uday
 */
@Service
public class StatisticService {

    private final PriorityBlockingQueue<Transaction> lastSixtySecondsTransactions =
            new PriorityBlockingQueue<>(Default.IINITIAL_CAPACITY, new TimestampComparator());

    @Getter
    private Statistic statistics = new Statistic(lastSixtySecondsTransactions);


    public void insertTransaction(Transaction transaction) {
        lastSixtySecondsTransactions.add(transaction);
        updateStatistics();
    }

    private void updateStatistics() {
        statistics = new Statistic(lastSixtySecondsTransactions);
    }

}