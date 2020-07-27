package com.practice.RazorPaySample.service.impl;
/* 
Created by amit.chaurasia 
on 7/25/20 
*/

import com.practice.RazorPaySample.constants.PaymentStatus;
import com.practice.RazorPaySample.dao.TransactionDetailDao;
import com.practice.RazorPaySample.entity.TransactionDetail;
import com.practice.RazorPaySample.external.RazorPayExternalService;
import com.practice.RazorPaySample.request.RazorPayCapturePaymentRequest;
import com.practice.RazorPaySample.request.RazorPayCreateOrderRequest;
import com.practice.RazorPaySample.request.sro.CapturePaymentRequest;
import com.practice.RazorPaySample.request.sro.CreatePaymentOrderRequest;
import com.practice.RazorPaySample.response.RazorPayCapturePaymentResponse;
import com.practice.RazorPaySample.response.RazorPayPaymentsResponse;
import com.practice.RazorPaySample.response.sro.CapturePaymentResponse;
import com.practice.RazorPaySample.response.sro.CreatePaymentOrderResponse;
import com.practice.RazorPaySample.response.RazorPayCreateOrderResponse;
import com.practice.RazorPaySample.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.util.Date;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private RazorPayExternalService razorPayExternalService;

    @Autowired
    private TransactionDetailDao transactionDetailDao;

    @Autowired
    private Environment environment;

    private final String ORDER_RECEIPT_PREFIX = "order-receipt-";
    private final static String HMAC_SHA256_ALGORITHM = "HmacSHA256";

    @Override
    public CreatePaymentOrderResponse createPaymentOrder(CreatePaymentOrderRequest request) {
        RazorPayCreateOrderRequest razorPayCreateOrderRequest = prepareRazorPayRequest(request);
        try {
            RazorPayCreateOrderResponse response = razorPayExternalService.createRazorpayOrder(razorPayCreateOrderRequest, 7000);
            System.out.println(response.toString());
            if (response != null) {
                transactionDetailDao.saveOrderDetailsForTransaction(prepareTransationEntity(response));
                return prepareRazorPayCreateOrderResponse(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private TransactionDetail prepareTransationEntity(RazorPayCreateOrderResponse response) {
        TransactionDetail transactionDetail = new TransactionDetail();
        transactionDetail.setAmount(String.valueOf(response.getAmount()));
        transactionDetail.setOrderId(response.getId());
        transactionDetail.setCurrency(response.getCurrency());
        transactionDetail.setStatus(PaymentStatus.CREATED.name());
        transactionDetail.setCreated(new Date());
        transactionDetail.setUpdated(new Date());
        return transactionDetail;
    }

    @Override
    public CapturePaymentResponse capturePayment(CapturePaymentRequest request) {
        CapturePaymentResponse capturePaymentResponse = new CapturePaymentResponse();
        String data = request.getOrderId() + "|" + request.getTransactionId();
        try {
            if (validateRazorpaySignature(data, request.getSignature())) {
                TransactionDetail transactionDetail = transactionDetailDao.findTransactionByOrderId(request.getOrderId());
                if (validatePaymentResponse(razorPayExternalService.getPaymentDetails(request.getTransactionId()), transactionDetail)) {
                    RazorPayCapturePaymentResponse razorPayCapturePaymentResponse = razorPayExternalService.captureRazorPayPayment(prepareRazorPayCapturePaymentRequest(transactionDetail),
                            7000, request.getTransactionId());
                    transactionDetail.setTransactionId(request.getTransactionId());
                    if (razorPayCapturePaymentResponse.isCaptured()) {
                        transactionDetail.setStatus(PaymentStatus.CAPTURED.name());
                        transactionDetailDao.updateTransaction(transactionDetail);
                        capturePaymentResponse.setCaptured(true);
                    } else {
                        transactionDetail.setStatus(PaymentStatus.AUTHORIZED.name());
                        transactionDetailDao.updateTransaction(transactionDetail);
                    }
                }
            } else {
                capturePaymentResponse.setSuccessful(false);
                capturePaymentResponse.setMessage("Signature doesn't match, invalid payment !!!");
            }
        } catch (Exception e) {
            capturePaymentResponse.setSuccessful(false);
            e.printStackTrace();
        }

        return capturePaymentResponse;
    }

    private RazorPayCapturePaymentRequest prepareRazorPayCapturePaymentRequest(TransactionDetail transactionDetail) {
        RazorPayCapturePaymentRequest razorPayCapturePaymentRequest = new RazorPayCapturePaymentRequest();
        razorPayCapturePaymentRequest.setAmount(Integer.valueOf(transactionDetail.getAmount()));
        razorPayCapturePaymentRequest.setCurrency(transactionDetail.getCurrency());
        return razorPayCapturePaymentRequest;
    }


    private boolean validateRazorpaySignature(String transactionCode, String razorpaySignature) {
        try {
            SecretKeySpec signingKey = new SecretKeySpec(environment.getProperty("razorpay.api.secret.key").getBytes(), HMAC_SHA256_ALGORITHM);
            Mac mac = Mac.getInstance(HMAC_SHA256_ALGORITHM);
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(transactionCode.getBytes());
            String generatedSignature = DatatypeConverter.printHexBinary(rawHmac).toLowerCase();
            if (generatedSignature.equals(razorpaySignature)) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error while evaluating signature");
            e.printStackTrace();
        }
        return false;
    }

    private boolean validatePaymentResponse(RazorPayPaymentsResponse paymentDetails, TransactionDetail byOrderId) {
        return paymentDetails.getAmount().equals(Integer.valueOf(byOrderId.getAmount())) && paymentDetails.getCurrency().equals(paymentDetails.getCurrency()) &&
                PaymentStatus.AUTHORIZED.status().equalsIgnoreCase(paymentDetails.getStatus());
    }

    private CreatePaymentOrderResponse prepareRazorPayCreateOrderResponse(RazorPayCreateOrderResponse response) {
        CreatePaymentOrderResponse createPaymentOrderResponse = new CreatePaymentOrderResponse();
        createPaymentOrderResponse.setOrderId(response.getId());
        return createPaymentOrderResponse;
    }

    private RazorPayCreateOrderRequest prepareRazorPayRequest(CreatePaymentOrderRequest request) {
        RazorPayCreateOrderRequest razorPayCreateOrderRequest = new RazorPayCreateOrderRequest();
        razorPayCreateOrderRequest.setAmount(request.getAmount());
        razorPayCreateOrderRequest.setCurrency(request.getCurrency());
        razorPayCreateOrderRequest.setPayment_capture("0");
        razorPayCreateOrderRequest.setReceipt(ORDER_RECEIPT_PREFIX + System.currentTimeMillis());
        return razorPayCreateOrderRequest;
    }

}
