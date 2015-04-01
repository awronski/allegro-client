package com.apwglobal.allegro.client.service;

import com.apwglobal.nice.command.SearchJournal;
import com.apwglobal.nice.domain.Journal;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

import java.util.List;

public interface IJournalService {

    @GET("/journals")
    List<Journal> getLastJournals(@Query("limit") int limit);

    @POST("/journals/search")
    List<Journal> search(@Body() SearchJournal s);

}
