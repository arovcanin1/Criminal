package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Criminal;
import ba.unsa.etf.rpr.domain.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.List;
import javafx.scene.control.ListView;

import javax.swing.*;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class EmployeeController {

    private Employee employee;

    public ListView listView;

    Criminal criminal = new Criminal();

    public EmployeeController() {
        employee = new Employee();
    }

    public EmployeeController(Employee employee) {
        this.employee = employee;
    }

    public void initialize() {
        ObservableList items = FXCollections.observableArrayList();
        try {
            List<Criminal> criminalsList = DaoFactory.criminalsDao().allCriminals();
            for (int i = 0; i < criminalsList.size(); i++) {
                items.add(criminalsList.get(i).getFirstName() + " " + criminalsList.get(i).getLastName());
            }

            listView.setItems(items);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void showAddCriminals(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addCriminal.fxml"));
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

    public void showCriminal(ActionEvent event) {
        criminal = (Criminal) listView.getSelectionModel().getSelectedItems();
        System.out.println(criminal.getId());
    }

    public void showLogout(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/home.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("CR Logout");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
