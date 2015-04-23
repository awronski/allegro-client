package com.apwglobal.allegro.client.service;

import com.apwglobal.nice.domain.WaitingFeedback;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;

public class FeedbackServiceTest extends AbstractIntegrationTest {

    @Test
    public void serviceShouldExist() {
        assertNotNull(client.getFeedbackService());
    }

    @Test
    public void shouldReturnWaitingFeedbacks() {
        List<WaitingFeedback> waitingFeedbacks = client.getFeedbackService().waitingFeedbackOnlyForPaidOrders();
        assertNotNull(waitingFeedbacks);
    }

}