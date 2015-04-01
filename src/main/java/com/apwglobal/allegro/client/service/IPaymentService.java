package com.apwglobal.allegro.client.service;

import com.apwglobal.nice.command.SearchPayment;
import com.apwglobal.nice.domain.Payment;
import com.apwglobal.nice.domain.PaymentProcessed;
import retrofit.http.*;

import java.util.List;

public interface IPaymentService {

    @GET("/payments")
    List<Payment> getLastPayments(@Query("limit") int limit);

    @POST("/payments/search")
    List<Payment> searchPayments(@Body() SearchPayment s);

    @PUT("/payments/process")
    PaymentProcessed process(@Query("transactionId") long transactionId,
                             @Query("amount") double amount,
                             @Query("ref") String ref);

    @GET("/payments/unprocessed")
    List<Payment> getUnprocessed();

}
