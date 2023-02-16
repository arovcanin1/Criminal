package ba.unsa.etf.rpr.controllers;


import ba.unsa.etf.rpr.domain.Employee;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Controller for handle profile window
 */
public class ProfileController {
    /**
     * Attributes for ProfileController
     */
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

    /**
     * Initialize method that is called as soon as window is opened
     * This method fills data for employee profile
     */
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
