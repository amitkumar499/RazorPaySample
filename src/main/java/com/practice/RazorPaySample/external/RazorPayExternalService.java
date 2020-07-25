package com.practice.RazorPaySample.external;
/* 
Created by amit.chaurasia 
on 7/25/20 
*/

import com.practice.RazorPaySample.exception.HttpTransportException;
import com.practice.RazorPaySample.request.RazorPayCreateOrderRequest;
import com.practice.RazorPaySample.response.RazorPayCreateOrderResponse;
import com.practice.RazorPaySample.transport.HttpSender;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

@Service
public class RazorPayExternalService {

    public static final String UNIQUE_REQUEST_ID = "uniqueRequestId";
    public static final String TRANSACTION_CODE = "transactionCode";
    public static final String PENDING = "pending";
    public static final String FAILED = "failed";
    public static final String PROCESSED = "processed";
    public static final String ID = "id";

    @Autowired
    private Environment environment;

    private final static String CONNECTOR = ":";
    private final static String NEWLINE = "\n";
    private final static String AUTHORIZATION = "Authorization";

    /*public static PaymentGatewayResponse.PGResponseStatus convertPayemtStatus(Map<String, Object> paymentResponseAttributes) {
        String pgStatus = getTxnStatus(paymentResponseAttributes);
        PaymentGatewayResponse.PGResponseStatus pgResponseStatus = PaymentGatewayResponse.PGResponseStatus.CREATED;
        switch (pgStatus) {
            case Constants.CAPTURED:
                pgResponseStatus = PaymentGatewayResponse.PGResponseStatus.SUCCESS;
                break;
            case Constants.FAILED:
                pgResponseStatus = PaymentGatewayResponse.PGResponseStatus.FAIL;
                break;
        }
        return pgResponseStatus;
    }

    public static PaymentGatewayResponse getPaymentGatewayResponse(String txnCode, Map<String, Object> responseAttrMap, PaymentGatewayResponse.PGResponseStatus pgResponseStatus) {
        Map<String, Object> items = new HashMap<>();
        if (MapUtils.isNotEmpty(responseAttrMap)) {
            items.putAll(responseAttrMap);
            items.put(AbstractPaymentGatewayProvider.ORDER_ID, txnCode);
            items.put(AbstractPaymentGatewayProvider.RESPONSE_PG_ERROR_CODE, getErrorCode(responseAttrMap));
            //setting razorpay payment id from response.
            items.put(RazorpayResponseKeys.RAZORPAY_PAYMENT_ID.getCode(), responseAttrMap.get(ID));
        }

        return new PaymentGatewayResponse(pgResponseStatus, items);
    }

    private static String getErrorCode(Map<String, Object> attrs) {
        Object obj = attrs.get("error_code");
        return  obj != null ? (String) obj : "";
    }

    public static String getTxnCodeFromPgResponse(Map<String, ? extends Object> parameterMap) {
        return (String) parameterMap.get(AbstractPaymentGatewayProvider.ORDER_ID);
    }

    public static boolean isTransactionFailed(Map<String, Object> responseAttrMap, String txnCode) {
        if (!(StringUtils.equalsIgnoreCase(Constants.CAPTURED, getTxnStatus(responseAttrMap)))) {
            return true;
        }
        return false;
    }

    public static String getTxnStatus(Map<String, Object> responseAttrMap) {
        String status = MapUtils.getString(responseAttrMap, Constants.STATUS);
        return StringUtils.isNotEmpty(status) ? status.toUpperCase() : "";
    }

    public static boolean isAmountPaidNotEqual(int amountFromDB, Map<String, Object> paymentResponseAttributes, String transactionCode) {
        Double transactionPaidAmount = new Double(amountFromDB);
        Double amountFromPg = getAmount(paymentResponseAttributes);
        // If paymentAmount (amount that was supposed to be paid) is not equal to amountPaid (amount actually paid), return false.
        transactionPaidAmount = transactionPaidAmount != null ? transactionPaidAmount * 100 : new Double(0);
        if (!transactionPaidAmount.equals(amountFromPg)) {
            LOGGER.info("[isAmountPaidNotEqual] Amount to be paid " + transactionPaidAmount + " is not equal to amount actually paid " + getAmount(paymentResponseAttributes) + " for [txnCode] {}", transactionCode);
            return true;
        }
        return false;
    }


    public static Double getAmount(Map<String, Object> responseAttrMap) {
        return MapUtils.getDouble(responseAttrMap, Constants.AMOUNT);
    }

    public static Map<String, Object> getPaymentResponseAttributes(String response) {
        HashMap<String, Object> responseAttrMap = new HashMap<String, Object>();
        DocumentContext documentContext = JsonPath.parse(response);

        // All these required attrs get be read from a configuration.
        Iterable<String> serializableOrderStatusFields = Splitter.on(Constants.DELIM_COMMA).omitEmptyStrings().trimResults().split(
                CacheManager.getInstance().getCache(OpmsPropertiesCache.class).getSerializableRazorpayPaymentStatusFields());

        String responseJSONPath = Constants.DELIM_DOLLAR;

        for (String serializableOrderStatusField : serializableOrderStatusFields) {
            if (org.apache.commons.lang3.StringUtils.isEmpty(serializableOrderStatusField))
                continue;

            try {
                Object obj = documentContext.read(responseJSONPath + Constants.DELIM_PERIOD + serializableOrderStatusField, Object.class);
                responseAttrMap.put(serializableOrderStatusField, obj);
            } catch (Exception e) {
            }
        }
        return responseAttrMap;
    }

    public static RefundResponseDTO convertPgRefundResponse(String response, String suborderCode) {
        RefundResponseDTO refundResponseDTO = new RefundResponseDTO();
        try {
            if (StringUtils.isNotEmpty(response)) {
                refundResponseDTO.setPgIncomingParams(response);

                JSONObject jsonObject = OpmsUtils.convertStringToJsonObject(response);

                setRefundStatus(jsonObject.getString(Constants.STATUS), refundResponseDTO);

            } else {
                LOGGER.info("Empty response received from razorpay for suborder code :: {}", suborderCode);
                refundResponseDTO.setMessage("Invalid response received from razorpay");
            }
        } catch (JSONException e) {
            LOGGER.error("[Json parse error]Error in executing refund at razorpay for suborder code :: {}", suborderCode, e);
            refundResponseDTO.setMessage("Invalid response received from razorpay : " + e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error in executing refund at razorpay for suborder code :: {}", suborderCode, e);
            refundResponseDTO.setMessage("Invalid response received from razorpay : " + e.getMessage());
        }
        return refundResponseDTO;
    }

    */

    /**
     * This method converts razorpay response into snapdeal specific response.
     * Pending -> HOLD
     * Not SUCCESS or NOT FAILED -> HOLD
     * SUCCESS -> SUCCESS
     * FAILED -> FAILED
     *
     * @param pgStatus
     * @param refundResponseDTO
     *//*
    private static void setRefundStatus(String pgStatus, RefundResponseDTO refundResponseDTO) {
        refundResponseDTO.setSuccessful(false);
        switch (pgStatus) {
            case FAILED:
                refundResponseDTO.setDebitStatus(DebitStatus.FAILED);
                refundResponseDTO.setMessage("Refund failed at razorpay");
                break;
            case PROCESSED:
                refundResponseDTO.setDebitStatus(DebitStatus.SUCCESS);
                refundResponseDTO.setMessage("Refund success at razorpay");
                refundResponseDTO.setSuccessful(true);
                break;
            default:
                refundResponseDTO.setDebitStatus(DebitStatus.HOLD);
                refundResponseDTO.setMessage("Refund pending at razorpay");
                break;
        }
    }

    public static boolean validateRazorpaySignature(Map<String, String> params, String transactionCode, String razorpaySecret) throws OPMSServiceException {

        String razorpayOrderId = params.get(RazorpayResponseKeys.RAZORPAY_ORDER_ID.getCode());
        String razorpayPaymentId = params.get(RazorpayResponseKeys.RAZORPAY_PAYMENT_ID.getCode());
        String razorpaySignature = params.get(RazorpayResponseKeys.RAZORPAY_SIGNATURE.getCode());

        LOGGER.info("Validating razorpay signature for txn code :: {}", transactionCode);

        OpmsPropertiesCache opmsPropertiesCache = CacheManager.getInstance().getCache(OpmsPropertiesCache.class);
        String razorpayAPISault = opmsPropertiesCache.getRazorpayAPISault();

        StringBuilder signatureBuilder = new StringBuilder();
        signatureBuilder.append(razorpayOrderId).append("|").append(razorpayPaymentId).append(razorpayAPISault);
        try {
            String generatedSignature = HashUtil.calculateRFC2104HMAC(signatureBuilder.toString(), razorpaySecret);

            if(generatedSignature.equals(razorpaySignature)) {
                return true;
            }
        } catch (SignatureException e) {
            LOGGER.error("Error in signature validation for txn code :: {}", transactionCode, e);
            throw new OPMSServiceException(OPMSServiceErrorCode.RAZORPAY_BAD_REQUEST.getCode(), OPMSServiceErrorCode.RAZORPAY_BAD_REQUEST);
        }
        return false;
    }

    public static String getAmountInPaise(int paymentAmount) {
        //converting amount into paise for razorpay
        Long amount = new Long(paymentAmount * 100);
        return String.valueOf(amount);
    }

    public static RazorpayCreateOrderRequest getCreateOrderRequest(OrderTransactionSRO orderTransaction) {
        OpmsPropertiesCache opmsPropertiesCache = CacheManager.getInstance().getCache(OpmsPropertiesCache.class);

        RazorpayCreateOrderRequest request = new RazorpayCreateOrderRequest();
        request.setAmount(RazorpayUtil.getAmountInPaise(orderTransaction.getPaymentAmount()));
        request.setCurrency(opmsPropertiesCache.getRazorpayCurrency());
        request.setReceipt(orderTransaction.getCode());
        request.setPayment_capture(opmsPropertiesCache.isPaymentAutoCaptureEnabledAtRazorpay());

        return request;
    }

    public static RazorpayRefundRequest getRazorpayRefundRequest(String code, String uniqueRequestId, int amount) {
        RazorpayRefundRequest refundRequest = new RazorpayRefundRequest();
        refundRequest.setAmount(RazorpayUtil.getAmountInPaise(amount));
        Map<String, String> notes = new HashMap<>(2);
        notes.put(UNIQUE_REQUEST_ID, uniqueRequestId);
        notes.put(TRANSACTION_CODE, code);
        refundRequest.setNotes(notes);
        return refundRequest;
    }*/
    public RazorPayCreateOrderResponse postRequestToPg(String apiName, String url, String request, Integer timeout) throws HttpTransportException {
        RazorPayCreateOrderRequest request1 = new RazorPayCreateOrderRequest();
        request1.setAmount(500);
        request1.setCurrency("INR");
        request1.setReceipt("id-1");
        request1.setPayment_capture("1");
        RazorPayCreateOrderResponse response = HttpSender.getInstance().executePostRequest(url, request1, RazorPayCreateOrderResponse.class, timeout, getHeaders());
        return response;
    }

    public Map<String, String> getHeaders() {
        String apiKey = environment.getProperty("razorpay.api.key");
        String apiSecret = environment.getProperty("razorpay.api.secret.key");
        StringBuilder apiKeySecret = new StringBuilder(apiKey).append(CONNECTOR).append(apiSecret);
        String encodedKey = new String(Base64.encodeBase64(apiKeySecret.toString().getBytes())).replaceAll(NEWLINE, "");
        Map<String, String> headers = new HashMap<>();
        headers.put(AUTHORIZATION, String.format("Basic %s", encodedKey));
        return headers;
    }


    /*public static String getResponseFromPg(String apiName, String url, Integer timeout, Map<String, String> headers) throws HttpTransportException, ExternalServiceException {
        return HttpSender.getInstance().executeGet(apiName, url, null,headers, timeout);
    }*/

}
