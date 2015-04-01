package com.apwglobal.allegro.client.service;

import com.apwglobal.allegro.client.auctionbuilder.AuctionBuilder;
import com.apwglobal.allegro.client.auctionbuilder.Color;
import com.apwglobal.allegro.client.auctionbuilder.State;
import com.apwglobal.nice.domain.*;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.singletonList;
import static org.junit.Assert.*;

public class AuctionServiceTest extends AbstractIntegrationTest {

    @Test
    public void serviceShouldExist() {
        assertNotNull(client.getAuctionService());
    }

    @Test
    public void shouldReturnOnlyOpenAuctions() {
        List<Auction> auctions = client.getAuctionService().getOpenAuctions(true);
        auctions
                .stream()
                .forEach(a -> assertTrue(a.isOpen()));
    }

    @Test
    public void shouldNotReturnAuction() {
        Optional<Auction> auction = client.getAuctionService().getAuctionById(0l);
        assertFalse(auction.isPresent());
    }

    @Test
    public void shouldReturnAuction() {
        List<Auction> auctions = client.getAuctionService().getAllAuctions();
        if (!auctions.isEmpty()) {
            Optional<Auction> auction = client.getAuctionService().getAuctionById(auctions.get(0).getId());
            assertTrue(auction.isPresent());
        }
    }

    @Test
    public void shouldChangeQty() {
        List<Auction> auctions = client.getAuctionService().getOpenAuctions(true);
        if (!auctions.isEmpty()) {
            ChangedQty changedQty = client.getAuctionService().changeQty(auctions.get(0).getId(), 3);
            assertEquals(3, changedQty.getLeft());
        }
    }

    @Test
    public void shouldFinishAuction() {
        List<Auction> auctions = client.getAuctionService().getOpenAuctions(true);
        if (!auctions.isEmpty()) {
            List<FinishAuctionFailure> finish = client.getAuctionService().finish(singletonList(auctions.get(0).getId()));
            assertTrue(finish.isEmpty());
        }
    }

    @Test
    public void shoudlCreateAuction() throws URISyntaxException {
        URI img = getClass().getResource("/resources/test.png").toURI();

        List<NewAuctionField> fields = new AuctionBuilder()
                .category(76661)
                .title(String.format("Testing %tFT%<tRZ", new Date()))
                .city("Warszawa")
                .zip("01-111")
                .state(State.MAZOWIECKIE)
                .color(Color.BLACK)
                .desc("This is test description")
                .price(1.99)

                .priceForLetter(7.99)
                .priceForNextItemInLetter(0.0)
                .qtyInLetter(50)

                .priceForCourier(14.99)
                .priceForNextItemInCourier(0.0)
                .qtyInCourier(250)

                .image(img)

                .build();
        CreatedAuction createdAuction = client.getAuctionService().create(fields);
        assertNotNull(createdAuction);
    }

}