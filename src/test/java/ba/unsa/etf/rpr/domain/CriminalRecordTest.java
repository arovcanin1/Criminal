package ba.unsa.etf.rpr.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CriminalRecordTest {
    Criminal criminal = new Criminal(1, "Criminal", "Criminal", "2705996175015", LocalDate.of(1996,05,27));
    CriminalRecord criminalRecord = new CriminalRecord(9, "someDescription", "Stockholm", LocalDate.of(2022,05,23), "someCode", criminal);

    @Test
    public void getCriminalTest() {
        assertEquals(criminal, criminalRecord.getCriminal());
    }
}
