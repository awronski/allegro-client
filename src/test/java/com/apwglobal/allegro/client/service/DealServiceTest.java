package com.apwglobal.allegro.client.service;

import com.apwglobal.nice.domain.Deal;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class DealServiceTest extends AbstractIntegrationTest {

    @Test
    public void serviceShouldExist() {
        assertNotNull(client.getDealService());
    }

    @Test
    public void shouldReturnDeals() {
        List<Deal> lastDeals = client.getDealService().getLastDeals(10);
        assertFalse(lastDeals.isEmpty());
    }

    @Test
    public void shouldReturnDealsAfter() {
        List<Deal> lastDeals = client.getDealService().getLastDeals(100);
        Optional<Deal> dealWithTransactionId = lastDeals
                .stream()
                .filter(d -> d.getTransactionId().isPresent())
                .reduce((a, b) -> b);

        if (dealWithTransactionId.isPresent()) {
            long transactionId = dealWithTransactionId.get().getTransactionId().get();
            List<Deal> after = client.getDealService().getDealsAfter(transactionId);
            assertTrue(lastDeals.size() > after.size());
        }
    }

}