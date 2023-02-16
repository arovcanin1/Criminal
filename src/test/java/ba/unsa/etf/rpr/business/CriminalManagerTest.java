package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Criminal;
import ba.unsa.etf.rpr.exceptions.CriminalRecordsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for CriminalManager class
 */
public class CriminalManagerTest {
    /**
     * Attributes
     */
    Criminal criminal = new Criminal();
    Criminal newCriminal = new Criminal();

    /**
     * Method that sets criminals to testing
     */
    @BeforeEach
    public void setCriminalTest() {
        criminal.setId(1);
        criminal.setFirstName("");
        criminal.setLastName("");
        criminal.setJmbg("2705996175015");
        criminal.setBirthDate(LocalDate.of(1996,05,27));

        criminal.setId(1);
        criminal.setFirstName("Criminal");
        criminal.setLastName("Criminal");
        criminal.setJmbg("2705996175015");
        criminal.setBirthDate(LocalDate.of(2025,05,27));
    }

    /**
     * Method that tests if there is exception when some fields are null
     */
    @Test
    public void exceptionTest() {
        try {
            DaoFactory.criminalsDao().add(criminal);
        } catch (CriminalRecordsException e) {
            assertEquals("All fields have to be filled!", e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void exceptionTest1() {
        try {
            DaoFactory.criminalsDao().add(newCriminal);
        } catch (CriminalRecordsException e) {
            assertEquals("Birth date is not correct", e.getMessage());
            e.printStackTrace();
        }
    }
}
