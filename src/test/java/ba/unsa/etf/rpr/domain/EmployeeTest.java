package ba.unsa.etf.rpr.domain;

import org.junit.jupiter.api.BeforeEach;

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
}
