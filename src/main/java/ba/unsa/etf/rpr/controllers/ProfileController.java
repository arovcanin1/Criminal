package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.Dao;
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

        try {
            DaoFactory.employeesDao().update(employee);
        } catch (CriminalRecordsException e) {
            e.printStackTrace();
        }


        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
            loader.setController(new LoginController());
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("CR Criminals");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(ActionEvent event) {
        employee.setFirstName(firstNameFld.getText());
        employee.setLastName(lastNameFld.getText());
        employee.setEmail(emailFld.getText());
        employee.setEmail(emailFld.getText());
        employee.setUsername(usernameFld.getText());
        employee.setPassword(passwordFld.getText());
    }
}
