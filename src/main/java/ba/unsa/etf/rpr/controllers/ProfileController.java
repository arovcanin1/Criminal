package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.exceptions.CriminalRecordsException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class ProfileController {
    Employee employee = new Employee();
    public Label welcomeLabel;
    public TextField firstNameFld;
    public TextField lastNameFld;
    public TextField emailFld;
    public TextField usernameFld;
    public PasswordField passwordFld;

    public Button updateBtn;
    public ProfileController() {
        employee = new Employee();
    }
    public ProfileController(Employee employee) {
        this.employee = employee;
    }

    public void initialize() {

        if (employee != null) {
            welcomeLabel.setText("Welcome, " + employee.getFirstName());
            firstNameFld.setText(employee.getFirstName());
            lastNameFld.setText(employee.getLastName());
            emailFld.setText(employee.getEmail());
            usernameFld.setText(employee.getUsername());
            passwordFld.setText(employee.getPassword());
        }
    }
}
