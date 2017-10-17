package com.apwglobal.allegro.client.service;

import com.apwglobal.allegro.client.auctionbuilder.AuctionBuilder;
import com.apwglobal.allegro.client.auctionbuilder.Color;
import com.apwglobal.allegro.client.auctionbuilder.State;
import com.apwglobal.nice.domain.*;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    public void shouldReturnAuctionFields() {
        List<Auction> auctions = client.getAuctionService().getOpenAuctions(true);
        if (!auctions.isEmpty()) {
            List<AuctionField> fields = client.getAuctionService().getAuctionFieldsById(auctions.get(0).getId());
            assertFalse(fields.isEmpty());
        }
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

            ChangedPrice changedPrice = client.getAuctionService().changePrice(auctions.get(0).getId(), 9.49);
            assertNotNull(changedPrice);
        }
    }

    @Test
    public void shouldFinishAuction() {
        boolean value = client.getAuctionService().updateExtraOptions(5335857537L);
//        List<Auction> auctions = client.getAuctionService().getOpenAuctions(true);
//        if (!auctions.isEmpty()) {
//            List<FinishAuctionFailure> finish = client.getAuctionService().finish(singletonList(auctions.get(0).getId()));
//            assertTrue(finish.isEmpty());
//        }
    }

    @Test
    public void shouldCreateAuction() throws URISyntaxException {
        URI img = getClass().getResource("/resources/test.png").toURI();

        List<AuctionField> fields = new AuctionBuilder()
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
        NewAuction newAuction = new NewAuction(fields, new SalesConditions("1", "2", "3"));
        CreatedAuction createdAuction = client.getAuctionService().create(newAuction);
        assertNotNull(createdAuction);
    }

    @Test
    public void shouldChangeAuction() {
        List<Auction> auctions = client.getAuctionService().getAllAuctions();
        if (!auctions.isEmpty()) {
            List<AuctionField> fields = Collections.singletonList(
                    new AuctionField<>(FieldId.TITLE, FieldType.Type.STRING, "zawieszki masa perłowa kwadraty zieleń -8 sztuk q9"));
            ChangedAuctionInfo change = client.getAuctionService().change(auctions.get(0).getId(), fields);
            assertNotNull(change);
        }
    }

}