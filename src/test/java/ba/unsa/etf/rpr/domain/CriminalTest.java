package ba.unsa.etf.rpr.domain;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CriminalTest {

    private Criminal criminal = new Criminal();

    @BeforeEach
    public void setCriminal() {
        LocalDate localDate = LocalDate.of(1996,05,27);
        criminal.setId(1);
        criminal.setFirstName("Criminal");
        criminal.setLastName("Criminal");
        criminal.setJmbg("2705996175015");
        criminal.setBirthDate(localDate);
    }

    @Test
    void getFirstName() {
        assertEquals("Criminal", criminal.getFirstName());
    }

    @Test
    void getLastName() {
        assertEquals("Criminal", criminal.getLastName());
    }

    @Test
    void getJmbg() {
        assertEquals("2705996175015", criminal.getJmbg());
    }

    @Test
    void toStringTest() {
        String s = "First name: " + criminal.getFirstName() + "\nLast name: " + criminal.getLastName() + "\nJMBG: " + criminal.getJmbg() + "\nBirth date: " + criminal.getBirthDate();
        assertEquals(s, criminal.toString());
    }
}
