package com.apwglobal.allegro.client.service;

import com.apwglobal.nice.domain.FormField;
import retrofit.http.GET;
import retrofit.http.Query;

import java.util.List;

public interface IFormFieldService {

    @GET("/form-fields")
    List<FormField> getFormFields(@Query("categoryId") int categoryId, @Query("onlyRequired") boolean onlyRequired);

}
