package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.domain.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

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

    public void showCriminals(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/criminals.fxml"));
            loader.setController(new CriminalController());
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

    public void showAbout(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/about.fxml"));
            loader.setController(new HomeController());
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("CR About");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showLogout(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/home.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("CR About");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
