package ba.unsa.etf.rpr.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for domain class Criminal record
 */
public class CriminalRecordTest {
    /**
     * Attributes
     */
    Criminal criminal = new Criminal(1, "Criminal", "Criminal", "2705996175015", LocalDate.of(1996,05,27));
    CriminalRecord criminalRecord = new CriminalRecord(9, "someDescription", "Stockholm", LocalDate.of(2022,05,23), "someCode", criminal);

    /**
     * Test that tests getter for attribute place
     */
    @Test
    public void getPlaceTest() {
        assertEquals("Stockholm", criminalRecord.getPlace());
    }

    /**
     * Test that tests getter for attribute description
     */
    @Test
    public void getDescriptionTest() {
        assertEquals("someDescription", criminalRecord.getDescription());
    }

    /**
     * Test that tests getter for attribute code
     */
    @Test
    public void getCodeTest() {
        assertEquals("someCode", criminalRecord.getCode());
    }

    /**
     * Test that tests getter for attribute date
     */
    @Test
    public void getDateTest() {
        assertEquals(LocalDate.of(2022, 05,23), criminalRecord.getDate());
    }

    /**
     * Test that tests getter for attribute criminal
     */
    @Test
    public void getCriminalTest() {
        assertEquals(criminal, criminalRecord.getCriminal());
    }


}
