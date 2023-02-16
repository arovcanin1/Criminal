package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.CriminalRecord;
import ba.unsa.etf.rpr.exceptions.CriminalRecordsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CriminalRecordManagerTest {
    CriminalRecord criminalRecord = new CriminalRecord();

    @BeforeEach
    public void setCriminalRecordTest() {
        criminalRecord.setId(12);
        criminalRecord.setPlace("Ljubljana");
        criminalRecord.setCode("codecode");
        criminalRecord.setDescription("Kill");
        criminalRecord.setDate(LocalDate.of(2025, 06,03));
    }

    @Test
    public void exceptionDateTest() {
        try {
            DaoFactory.criminalRecordsDao().add(criminalRecord);
        } catch (CriminalRecordsException e) {
            assertEquals("Date is not correct", e.getMessage());
        }
    }
}
