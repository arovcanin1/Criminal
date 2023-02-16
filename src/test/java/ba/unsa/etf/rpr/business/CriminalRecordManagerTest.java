package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.domain.CriminalRecord;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;

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
}
