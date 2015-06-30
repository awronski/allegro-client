package com.apwglobal.allegro.client.service;

import com.apwglobal.nice.domain.*;
import retrofit.http.*;

import java.util.List;
import java.util.Optional;

public interface IAuctionService {

    @GET("/auctions")
    List<Auction> getAllAuctions();

    @GET("/auctions")
    List<Auction> getOpenAuctions(@Query("open") boolean open);

    @GET("/auctions/{itemId}")
    Optional<Auction> getAuctionById(@Path("itemId") long itemId);

    @GET("/auctions/{itemId}/fields")
    List<AuctionField> getAuctionFieldsById(@Path("itemId") long itemId);

    @PUT("/auctions/{itemId}/changeQty")
    ChangedQty changeQty(@Path("itemId") long itemId, @Query("newQty") int newQty);

    @PUT("/auctions/finish")
    List<FinishAuctionFailure> finish(@Query("itemsIds") List<Long> itemsIds);

    @POST("/auctions/create")
    CreatedAuction create(@Body List<AuctionField> fields);

    @PUT("/auctions/{itemId}/change")
    ChangedAuctionInfo change(@Path("itemId") long itemId, @Body List<AuctionField> fields);

}
