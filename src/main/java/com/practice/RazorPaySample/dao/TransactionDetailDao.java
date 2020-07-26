package com.practice.RazorPaySample.dao;
/* 
Created by amit.chaurasia 
on 7/26/20 
*/

import com.practice.RazorPaySample.entity.TransactionDetail;
import com.practice.RazorPaySample.repository.TransactionDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class TransactionDetailDao {

    @Autowired
    private TransactionDetailRepository transactionDetailRepository;

    @Async
    @Transactional
    public void saveOrderDetailsForTransaction(TransactionDetail transactionDetail){
        transactionDetailRepository.save(transactionDetail);
    }

    @Transactional
    public TransactionDetail findTransactionByOrderId(String orderId) {
        return transactionDetailRepository.findByOrderId(orderId);
    }
}
