package com.apwglobal.allegro.client.service;

import com.apwglobal.nice.domain.Deal;
import org.junit.Test;

import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
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
    public void shouldReturnDealsAfterTransactionId() {
        List<Deal> lastDeals = client.getDealService().getLastDeals(100)
                .stream()
                .filter(d -> d.getTransactionId().isPresent())
                .collect(toList());

        if (!lastDeals.isEmpty()) {
            Long minTransactionId = lastDeals
                    .stream()
                    .min(comparing(deal -> deal.getTransactionId().get()))
                    .get()
                    .getTransactionId()
                    .get();

            List<Deal> after = client.getDealService().getDealsAfterTransactionId(minTransactionId);
            assertTrue(lastDeals.size() > after.size());
        }
    }

    @Test
    public void shouldReturnDealsAfterEventId() {
        List<Deal> lastDeals = client.getDealService().getLastDeals(100)
                .stream()
                .sorted(comparing(Deal::getEventId))
                .collect(toList());

        if (lastDeals.size() > 1) {
            List<Deal> after = client.getDealService().getDealsAfterEventId(lastDeals.get(0).getEventId());
            assertTrue(lastDeals.size() - 1 == after.size());
        }
    }

}