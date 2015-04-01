package com.apwglobal.allegro.client.service;

import com.apwglobal.nice.domain.FormField;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertNotNull;

public class FormFieldServiceTest extends AbstractIntegrationTest {

    @Test
    public void serviceShouldExist() {
        assertNotNull(client.getFormFieldService());
    }

    @Test
    public void shouldReturnDeals() {
        List<FormField> fields = client.getFormFieldService().getFormFields(76661, true);
        assertFalse(fields.isEmpty());
    }
}