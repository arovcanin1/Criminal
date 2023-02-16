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

    public void updateEmployee(ActionEvent event) {
        employee.setFirstName(firstNameFld.getText());
        employee.setLastName(lastNameFld.getText());
        employee.setEmail(emailFld.getText());
        employee.setEmail(emailFld.getText());
        employee.setUsername(usernameFld.getText());
        employee.setPassword(passwordFld.getText());

        System.out.println("THISS HAPPENS");
        try {
            DaoFactory.employeesDao().update(employee);
        } catch (CriminalRecordsException e) {
            e.printStackTrace();
        }

        Stage s = (Stage) updateBtn.getScene().getWindow();
        s.close();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
            loader.setController(new EmployeeController(employee));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("CR Criminals");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
