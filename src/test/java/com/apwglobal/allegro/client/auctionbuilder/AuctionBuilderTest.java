package com.apwglobal.allegro.client.auctionbuilder;

import com.apwglobal.nice.domain.AuctionField;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertFalse;

public class AuctionBuilderTest {

    @Test
    public void buildWithDefaultValuesTest() {
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
                .build();

        assertFalse(fields.isEmpty());
    }

}