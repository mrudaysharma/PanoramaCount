/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.panono.panorama;

import com.panono.panorama.entity.Transaction;
import java.util.Comparator;
/**
 *
 * @author uday
 */
public class TimestampComparator implements Comparator<Transaction> {

    @Override
    public int compare(Transaction transactionOne, Transaction transactionTwo) {
        return transactionOne.getTimestamp().compareTo(transactionTwo.getTimestamp());
    }
}
