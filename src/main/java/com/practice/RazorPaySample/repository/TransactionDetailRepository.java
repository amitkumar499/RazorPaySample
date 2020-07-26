package com.practice.RazorPaySample.repository;
/* 
Created by amit.chaurasia 
on 7/26/20 
*/

import com.practice.RazorPaySample.entity.TransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface TransactionDetailRepository extends JpaRepository<TransactionDetail, Integer>, JpaSpecificationExecutor<TransactionDetail> {

    TransactionDetail findByOrderId(String orderId);

}