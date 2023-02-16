package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.domain.Employee;
import javafx.scene.control.Label;

public class ProfileController {
    Employee employee = new Employee();
    public Label welcomeLabel;

    public ProfileController(Employee employee) {
        this.employee = employee;
    }


    public void initialize() {
        welcomeLabel.setText("Welcome, " + employee.getFirstName());
    }
}
