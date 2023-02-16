package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.domain.Criminal;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;

public class CriminalManager {

    Criminal criminal = new Criminal();

    @BeforeEach
    public void setCriminalTest() {
        criminal.setId(1);
        criminal.setFirstName("");
        criminal.setLastName("");
        criminal.setJmbg("2705996175015");
        criminal.setBirthDate(LocalDate.of(1996,05,27));
    }
}
