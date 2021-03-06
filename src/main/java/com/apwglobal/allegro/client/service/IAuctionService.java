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

    @GET("/auctions")
    List<Auction> getOpenAuctionsWithSale(@Query("open") boolean open, @Query("withSale") boolean withSale);

    @GET("/auctions/{itemId}")
    Optional<Auction> getAuctionById(@Path("itemId") long itemId);

    @GET("/auctions/{itemId}/fields")
    List<AuctionField> getAuctionFieldsById(@Path("itemId") long itemId);

    @PUT("/auctions/{itemId}/changeQty")
    ChangedQty changeQty(@Path("itemId") long itemId, @Query("newQty") int newQty);

    @PUT("/auctions/{itemId}/extraOptions")
    boolean updateExtraOptions(@Path("itemId") long itemId);

    @PUT("/auctions/{itemId}/changePrice")
    ChangedPrice changePrice(@Path("itemId") long itemId, @Query("newPrice") double newPrice);

    @PUT("/auctions/finish")
    List<FinishAuctionFailure> finish(@Query("itemsIds") List<Long> itemsIds);

    @POST("/auctions/create")
    CreatedAuction create(@Body NewAuction newAuction);

    @PUT("/auctions/{itemId}/change")
    ChangedAuctionInfo change(@Path("itemId") long itemId, @Body List<AuctionField> fields);

}
