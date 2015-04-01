package com.apwglobal.allegro.client.service;

import com.apwglobal.nice.domain.Deal;
import retrofit.http.GET;
import retrofit.http.Query;

import java.util.List;

public interface IDealService {

    @GET("/deals")
    List<Deal> getLastDeals(@Query("limit") int limit);

    @GET("/deals/after")
    List<Deal> getDealsAfter(@Query("transactionId") long transactionId);

}
