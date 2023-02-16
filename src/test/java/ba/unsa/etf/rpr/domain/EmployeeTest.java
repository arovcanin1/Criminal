package ba.unsa.etf.rpr.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeTest {

    private Employee employee = new Employee();

    @BeforeEach
    public void setEmployee() {
        employee.setId(1);
        employee.setFirstName("Amila");
        employee.setLastName("Rovcanin");
        employee.setUsername("arovcanin1");
        employee.setEmail("arovcanin1@etf.unsa.ba");
        employee.setPassword("amilaamila1-");
    }

    @Test
    void getFirstName() {
        assertEquals("Amila", employee.getFirstName());
    }

    @Test
    void getLastName() {
        assertEquals("Rovcanin", employee.getLastName());
    }

    @Test
    void getUsername() {
        assertEquals("arovcanin1", employee.getUsername());
    }

    @Test
    void getEmail() {
        assertEquals("arovcanin1@etf.unsa.ba", employee.getEmail());
    }

    @Test
    void getPassword() {
        assertEquals("amilaamila1-", employee.getPassword());
    }
}
