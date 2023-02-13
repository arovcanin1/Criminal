package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.EmployeeManager;
import ba.unsa.etf.rpr.dao.Dao;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class LoginController {

    public Button loginBtnClick;
    public TextField inputUsernameFld;
    public PasswordField inputPasswordFld;

    Employee employee = new Employee();


    public void showEmployeeWindow(ActionEvent event) {

        try {
            employee.setUsername(inputUsernameFld.getText());
            employee.setPassword(inputPasswordFld.getText());
            System.out.println("Testing if button is OK!");
            /* Checking if username and password is ok
            System.out.println("PASSWORD JE " + employee.getPassword());
            System.out.println("USERNAME JE " + employee.getUsername());*/

            (new EmployeeManager()).loginSearch(employee.getUsername(), employee.getPassword());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/employee.fxml"));
            // Employee employee1 = DaoFactory.employeesDao().getByUsername(employee.getUsername());

            Stage s = (Stage) loginBtnClick.getScene().getWindow();
            s.close();

            loader.setController(new EmployeeController(employee));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("CR Employee");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage());
        }

    }
}
