package ba.unsa.etf.rpr.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for domain class Employee
 */
public class EmployeeTest {
    /**
     * Attribute new Employee
     */
    private Employee employee = new Employee();

    /**
     * Setting Employee for testing
     */
    @BeforeEach
    public void setEmployee() {
        employee.setId(1);
        employee.setFirstName("Amila");
        employee.setLastName("Rovcanin");
        employee.setUsername("arovcanin1");
        employee.setEmail("arovcanin1@etf.unsa.ba");
        employee.setPassword("amilaamila1-");
    }

    /**
     * Test which tests getter for attribute firstName
     */
    @Test
    void getFirstName() {
        assertEquals("Amila", employee.getFirstName());
    }

    /**
     * Test which tests getter for attribute lastName
     */
    @Test
    void getLastName() {
        assertEquals("Rovcanin", employee.getLastName());
    }

    /**
     * Test which tests getter for attribute username
     */
    @Test
    void getUsername() {
        assertEquals("arovcanin1", employee.getUsername());
    }

    /**
     * Test which tests getter for attribute email
     */
    @Test
    void getEmail() {
        assertEquals("arovcanin1@etf.unsa.ba", employee.getEmail());
    }

    /**
     * Test which tests getter for attribute password
     */
    @Test
    void getPassword() {
        assertEquals("amilaamila1-", employee.getPassword());
    }
}
