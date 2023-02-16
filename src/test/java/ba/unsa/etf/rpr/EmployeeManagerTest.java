package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.domain.Employee;
import org.junit.jupiter.api.BeforeAll;

public class EmployeeManagerTest {
    Employee employee = new Employee();

    @BeforeAll
    public void setEmployee() {
        employee.setId(1);
        employee.setFirstName("Amila");
        employee.setLastName("Rovcanin");
        employee.setUsername("arovcanin1");
        employee.setEmail("arovcanin1@etf.unsa.ba");
        employee.setPassword("someBadPass");
    }
}
