package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.domain.Employee;

public class EmployeeController {

    private Employee employee;

    public EmployeeController() {
        employee = new Employee();
    }

    public EmployeeController(Employee employee) {
        this.employee = employee;
    }

    public void initialize() {

    }
}
