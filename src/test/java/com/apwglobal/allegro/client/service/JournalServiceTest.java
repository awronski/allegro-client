package com.apwglobal.allegro.client.service;

import com.apwglobal.nice.command.SearchJournal;
import com.apwglobal.nice.domain.Journal;
import com.apwglobal.nice.domain.JournalType;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertNotNull;

public class JournalServiceTest extends AbstractIntegrationTest {

    @Test
    public void serviceShouldExist() {
        assertNotNull(client.getJournalService());
    }

    @Test
    public void shouldReturnJournals() {
        List<Journal> lastJournals = client.getJournalService().getLastJournals(10);
        assertFalse(lastJournals.isEmpty());
    }

    @Test
    public void shouldSearchAndReturnJournals() {
        SearchJournal searchJournal = new SearchJournal.Builder()
                .changeType(JournalType.CHANGE)
                .to(new Date())
                .build();

        List<Journal> lastJournals = client.getJournalService().search(searchJournal);
        assertFalse(lastJournals.isEmpty());
    }

}