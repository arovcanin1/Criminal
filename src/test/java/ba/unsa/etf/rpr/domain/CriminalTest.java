package ba.unsa.etf.rpr.domain;

import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;

public class CriminalTest {

    private Criminal criminal = new Criminal();

    @BeforeEach
    public void setCriminal() {
        criminal.setId(1);
        criminal.setFirstName("Criminal");
        criminal.setLastName("Criminal");
        criminal.setJmbg("2705996175015");
    }
}
