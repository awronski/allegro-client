package com.apwglobal.allegro.client.service;

import com.apwglobal.nice.command.SearchPayment;
import com.apwglobal.nice.domain.Payment;
import com.apwglobal.nice.domain.PaymentProcessed;
import com.apwglobal.nice.exception.AllegroException;
import org.junit.Test;
import retrofit.RetrofitError;

import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertNotNull;

public class PaymentServiceTest extends AbstractIntegrationTest {

    @Test
    public void serviceShouldExist() {
        assertNotNull(client.getPaymentService());
    }

    @Test
    public void shouldReturnPayments() {
        List<Payment> forms = client.getPaymentService().getLastPayments(10);
        assertFalse(forms.isEmpty());
    }

    @Test
    public void shouldReturnSearchedPayments() {
        IPaymentService service = client.getPaymentService();
        List<Payment> forms = service.searchPayments(new SearchPayment.Builder()
                .withMsg(false)
                .withInvoice(false)
                .to(new Date())
                .limit(100)
                .build());


        assertNotNull(forms);

        if (!forms.isEmpty()) {
            Payment form = forms.get(0);
            forms = service.searchPayments(new SearchPayment.Builder()
                    .email(form.getEmail())
                    .transactionId(form.getTransactionId())
                    .build());
            assertFalse(forms.isEmpty());
        }
    }

    @Test(expected = AllegroException.class)
    public void shouldThorwException() {
        client.getPaymentService().process(0, 1.11, "123");
    }

    @Test
    public void shouldProcessPayment() {
        IPaymentService paymentService = client.getPaymentService();

        List<Payment> unprocessed = paymentService.searchPayments(new SearchPayment.Builder().processed(false).build());
        if (!unprocessed.isEmpty()) {
            Payment payment = unprocessed.get(0);
            PaymentProcessed processed = paymentService.process(payment.getTransactionId(), payment.getAmount(), "12345");
            assertNotNull(processed);

            List<Payment> processedPaymentWithGivenId = paymentService.searchPayments(new SearchPayment.Builder()
                    .transactionId(payment.getTransactionId())
                    .build());
            Payment updated = processedPaymentWithGivenId.get(0);
            assertTrue(updated.isProcessed() && updated.getTransactionId() == payment.getTransactionId());
        }
    }

    @Test
    public void shouldReturnUnprocessedPayments() {
        List<Payment> unprocessed = client.getPaymentService().getUnprocessed();
        assertNotNull(unprocessed);
        unprocessed
                .stream()
                .forEach(p -> assertFalse(p.isProcessed()));
    }

}