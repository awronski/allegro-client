package com.apwglobal.allegro.client.service;

import com.apwglobal.nice.domain.*;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

import java.util.List;

public interface IFeedbackService {

    @GET("/feedbacks/waiting/onlyPaid")
    List<WaitingFeedback> waitingFeedbackOnlyForPaidOrders();

    @POST("/feedbacks/create")
    List<CreatedFeedback> create(@Body List<CreateFeedback> feedbacks);

}
