package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.domain.Employee;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ProfileController {
    Employee employee = new Employee();
    public Label welcomeLabel;
    public TextField firstNameFld;
    public TextField lastNameFld;
    public TextField emailFld;
    public TextField usernameFld;
    public PasswordField passwordFld;

    public Button updateBtn;

    public ProfileController(Employee employee) {
        this.employee = employee;
    }


    public void initialize() {
        welcomeLabel.setText("Welcome, " + employee.getFirstName());
        firstNameFld.setText(employee.getFirstName());
        lastNameFld.setText(employee.getLastName());
        emailFld.setText(employee.getEmail());
        usernameFld.setText(employee.getUsername());
        passwordFld.setText(employee.getPassword());
    }
}
