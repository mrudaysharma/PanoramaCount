/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.panono.panorama.service;

import com.panono.panorama.entity.Statistic;
import com.panono.panorama.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author uday
 */
@Service
public class TransactionService {

    @Autowired
    private StatisticService statisticsService;

    public void create(Transaction transaction) {
        statisticsService.insertTransaction(transaction);
    }
    
    public Statistic getStatistic()
    {
        return statisticsService.getStatistics();
    }

}
