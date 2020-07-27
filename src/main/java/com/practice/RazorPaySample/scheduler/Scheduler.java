package com.practice.RazorPaySample.scheduler;
/* 
Created by amit.chaurasia 
on 7/27/20 
*/


import com.practice.RazorPaySample.constants.PaymentStatus;
import com.practice.RazorPaySample.controller.PaymentController;
import com.practice.RazorPaySample.dao.TransactionDetailDao;
import com.practice.RazorPaySample.entity.TransactionDetail;
import com.practice.RazorPaySample.external.RazorPayExternalService;
import com.practice.RazorPaySample.repository.TransactionDetailRepository;
import com.practice.RazorPaySample.response.RazorPayOrderStatusResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Component
public class Scheduler {
    @Autowired
    private Environment environment;

    @Autowired
    private TransactionDetailDao transactionDetailDao;

    @Autowired
    private TransactionDetailRepository transactionDetailRepository;

    @Autowired
    private RazorPayExternalService razorPayExternalService;

    @Scheduled(cron = "${txn.status.cron.expression}")
    @Transactional
    public void checkTransactionStatus() {
        System.out.println("Executing  job for Transaction status at razorpay...");
        ZoneId zone = ZoneId.of("Asia/Kolkata");
        List<TransactionDetail> transactionDetails=null;
        long daysBack = LocalDateTime.now().minusMinutes(Long.parseLong(environment.getProperty("txn.check.job.time.lag"))).atZone(zone).toInstant().toEpochMilli();
        try {
            transactionDetails=transactionDetailRepository.findTxnForStatusCheck(new Date(daysBack));
            System.out.println("successfully executed Transaction status.");
        } catch (Exception e) {
            System.out.println("Error while executing Transaction status "+e.toString());
        }
        try {
            if(!CollectionUtils.isEmpty(transactionDetails)){
                for(TransactionDetail detail:transactionDetails){
                    RazorPayOrderStatusResponse orderStatusResponse=razorPayExternalService.getPaymentStatusByOrderId(detail.getOrderId());
                    if(orderStatusResponse!=null && !CollectionUtils.isEmpty(orderStatusResponse.getItems())
                            && orderStatusResponse.getItems().get(0).getOrder_id().equals(detail.getOrderId())
                            && orderStatusResponse.getItems().get(0).getAmount()==Integer.parseInt(detail.getAmount())
                    ){
                        detail.setTransactionId(orderStatusResponse.getItems().get(0).getId());
                        if("authorized".equalsIgnoreCase(orderStatusResponse.getItems().get(0).getStatus())){
                            detail.setStatus(PaymentStatus.AUTHORIZED.toString());
                        }else if("captured".equalsIgnoreCase(orderStatusResponse.getItems().get(0).getStatus())){
                            detail.setStatus(PaymentStatus.CAPTURED.toString());
                        }else if("failed".equalsIgnoreCase(orderStatusResponse.getItems().get(0).getStatus())){
                            detail.setStatus(PaymentStatus.FAILED.toString());
                        }else{
                            detail.setStatus(orderStatusResponse.getItems().get(0).getStatus());
                        }
                        transactionDetailRepository.save(detail);
                    }
                }
            }
            System.out.println("successfully executed  job for txn status.");
        } catch (Exception e) {
            System.out.println("Error while executing  job for txn status:"+e);
        }
    }
}
