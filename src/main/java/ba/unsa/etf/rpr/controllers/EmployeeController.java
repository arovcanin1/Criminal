package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.Dao;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Criminal;
import ba.unsa.etf.rpr.domain.CriminalRecord;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.exceptions.CriminalRecordsException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.AccessibleRole;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javafx.scene.control.ListView;

import javax.swing.*;

import static java.lang.String.valueOf;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class EmployeeController {

    private Employee employee;

    public TextField firstNameFld;
    public TextField lastNameFld;
    public TextField birthDateFld;
    public TextField jmbgFld;

    public Button logoutBtn;

    public ListView listView;

    public ListView listViewRecords;
    Criminal criminal = new Criminal();
    Criminal c = new Criminal();

    public EmployeeController() {
        employee = new Employee();
    }

    public EmployeeController(Employee employee) {
        this.employee = employee;
    }

    public void initialize() {
        ObservableList criminalItems = FXCollections.observableArrayList();



        try {
            List<Criminal> criminalsList = DaoFactory.criminalsDao().allCriminals();

            for (int i = 0; i < criminalsList.size(); i++) {
                criminalItems.add(criminalsList.get(i).getJmbg());
            }
            listView.setItems(criminalItems);


        } catch (Exception e) {
            e.printStackTrace();
        }

            listView.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
                List<CriminalRecord> allRecords= new ArrayList<>();
                ObservableList allRecordsItems = FXCollections.observableArrayList();
                jmbgFld.setText(listView.getSelectionModel().getSelectedItem().toString());

                    try {
                        c = DaoFactory.criminalsDao().getByJMBG(listView.getSelectionModel().getSelectedItem().toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                try {
                    firstNameFld.setText(DaoFactory.criminalsDao().getByJMBG(listView.getSelectionModel().getSelectedItem().toString()).getFirstName());
                    lastNameFld.setText(DaoFactory.criminalsDao().getByJMBG(listView.getSelectionModel().getSelectedItem().toString()).getLastName());
                    birthDateFld.setText(valueOf(DaoFactory.criminalsDao().getByJMBG(listView.getSelectionModel().getSelectedItem().toString()).getBirthDate()));
                    List<CriminalRecord>  criminalRecords= DaoFactory.criminalRecordsDao().getAll();
                    allRecords = (DaoFactory.criminalRecordsDao().getByIdNew(DaoFactory.criminalsDao().getByJMBG(listView.getSelectionModel().getSelectedItem().toString()).getId()));

                    for (int i = 0; i < allRecords.size(); i++) {
                        allRecordsItems.add(allRecords.get(i).getCode());
                    }
                    listViewRecords.setItems(allRecordsItems);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

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
            CriminalController criminalController = loader.getController();
            listView.refresh();
            criminalController.setList(listView);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showAddRecord(ActionEvent event) {
        try {


            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addRecord.fxml"));
            loader.setController(new CriminalRecordController(c));
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




    public void showLogout(ActionEvent event) {
            Stage stage = (Stage) logoutBtn.getScene().getWindow();
            stage.close();
    }

    public void deleteCriminal (ActionEvent event) {
        int selected = listView.getSelectionModel().getSelectedIndex();
        if (selected != -1) {
            Object delete = listView.getSelectionModel().getSelectedItem();

            int newSelected = (selected == listView.getItems().size() - 1)
                    ? selected-1
                    : selected;

            listView.getItems().remove(selected);

            try {
                DaoFactory.criminalsDao().allCriminals().remove(delete);
                String[] i = delete.toString().split(" ");
                int iDelete = Integer.parseInt(i[2]);
                DaoFactory.criminalsDao().delete(iDelete);
            } catch (CriminalRecordsException e) {
                throw new RuntimeException();
            }

            listView.refresh();
            listView.getSelectionModel().select(newSelected);
        }
    }

}
