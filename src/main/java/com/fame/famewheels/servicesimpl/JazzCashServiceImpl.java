package com.fame.famewheels.servicesimpl;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import com.fame.famewheels.services.JazzCashService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JazzCashServiceImpl implements JazzCashService {
	
	  private final String JAZZCASH_API_BASE_URL = "https://sandbox.jazzcash.com.pk";
	    private final String MERCHANT_ID = "MC57028";
	    private final String PASSWORD = "wsu1426txw";
	    private final String INTEGRITY_SALT = "tuttyw654h";
	
	
	@Override
	public void initiatePayment(double amount, String orderId) {
		// Create the payment request payload
        Map<String, Object> paymentRequest = new HashMap();
        paymentRequest.put("pp_Version", "1.1");
        paymentRequest.put("pp_TxnType", "MWALLET");
        paymentRequest.put("pp_Language", "EN");
        paymentRequest.put("pp_MerchantID", MERCHANT_ID);
        paymentRequest.put("pp_SubMerchantID", "");
        paymentRequest.put("pp_Password", PASSWORD);
        paymentRequest.put("pp_BankID", "TBANK");
        paymentRequest.put("pp_ProductID", "RETL");
        paymentRequest.put("pp_TxnRefNo", orderId);
        paymentRequest.put("pp_Amount", amount);
        paymentRequest.put("pp_TxnCurrency", "PKR");
        paymentRequest.put("pp_TxnDateTime", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
        paymentRequest.put("pp_BillReference", "billRef");
        paymentRequest.put("pp_Description", "description");
        paymentRequest.put("pp_TxnExpiryDateTime", LocalDateTime.now().plusMinutes(15).format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
        paymentRequest.put("pp_ReturnURL", "https://famewheels.com");
        
        // Generate the integrity hash
        String integritySaltedMessage = MERCHANT_ID + orderId + amount + "PKR" + PASSWORD + INTEGRITY_SALT;
        String integrityHash = DigestUtils.sha256Hex(integritySaltedMessage);
        paymentRequest.put("ppmpf_1", integrityHash);

        // Send the payment request to JazzCash API
//        HttpClient httpClient = HttpClientBuilder.create().build();
//        HttpPost httpPost = new HttpPost(JAZZCASH_API_BASE_URL + "/tbpayments/rest/register.do");
//        httpPost.setHeader("Content-Type", "application/json");
//        httpPost.setEntity(new StringEntity(new ObjectMapper().writeValueAsString(paymentRequest)));
//
//        try {
//            HttpResponse response = httpClient.execute(httpPost);
//            int statusCode = response.getStatusLine().getStatusCode();
//            
//            // Process the response accordingly
//            if (statusCode == HttpStatus.SC_OK) {
//                // Payment request successful
//                // Extract the response body and process it
//            }

		
	}
	
	

}
