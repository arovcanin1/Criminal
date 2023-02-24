package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Criminal;
import ba.unsa.etf.rpr.domain.CriminalRecord;
import ba.unsa.etf.rpr.domain.Employee;
import ba.unsa.etf.rpr.exceptions.CriminalRecordsException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.ListView;
import static java.lang.String.valueOf;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

/**
 * Controller for handle employee window
 */
public class EmployeeController {

    /**
     * Attributes for EmployeeController
     */
    private Employee employee;
    public TextField firstNameFld;
    public TextField lastNameFld;
    public TextField birthDateFld;
    public TextField jmbgFld;
    public Button logoutBtn;
    public ListView<Criminal> listView;
    public ListView<CriminalRecord> listViewRecords;
    public TextField placeRecordFld;
    public TextField descriptionRecordFld;
    public TextField codeRecordFld;
    public TextField dateRecordFld;

    /**
     * Constructor for EmployeeController without parameter
     */
    public EmployeeController() {
        employee = new Employee();
    }

    /**
     * Constructor for EmployeeController with one parameter
     * @param employee
     */
    public EmployeeController(Employee employee) {
        this.employee = employee;
    }

    public void setListRecords(ListView listRecords) {
        this.listViewRecords = listRecords;
    }

    /**
     * Initialize method that is called as soon as window is opened
     * This method contains listeners for list of criminals and records
     */


    public void initialize() {

        try {
            listView.setItems(FXCollections.observableArrayList(DaoFactory.criminalsDao().getAll()));

            listView.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
                jmbgFld.setText(newValue.getJmbg());
                firstNameFld.setText(newValue.getFirstName());
                lastNameFld.setText(newValue.getLastName());
                birthDateFld.setText(newValue.getBirthDate().toString());

            try {
                listViewRecords.setItems(FXCollections.observableArrayList(DaoFactory.criminalRecordsDao().getByIdNew(newValue.getId())));
            } catch (CriminalRecordsException e) {
                throw new RuntimeException(e);
            }
        });

                listViewRecords.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
                    if (newValue != null) {
                        placeRecordFld.setText(newValue.getPlace());
                        descriptionRecordFld.setText(newValue.getDescription());
                        dateRecordFld.setText(valueOf(newValue.getDate()));
                        codeRecordFld.setText(newValue.getCode());
                    }
                });


            listViewRecords.refresh();

        } catch (CriminalRecordsException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method that opens window for adding new criminal
     * @param event
     */
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
            stage.setOnHiding(e -> {
                try {
                        listView.setItems(FXCollections.observableArrayList(DaoFactory.criminalsDao().getAll()));
                        listView.refresh();
                } catch (CriminalRecordsException ex) {
                    throw new RuntimeException(ex);
                }
            });
            CriminalController criminalController = loader.getController();
            criminalController.setList(listView);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method that opens window for adding new record
     * @param event
     */
    public void showAddRecord(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addRecord.fxml"));
            loader.setController(new CriminalRecordController(listView.getSelectionModel().getSelectedItem()));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("CR Criminals");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.show();
            stage.setOnHiding(e -> {
                try {
                    if (listViewRecords != null) {
                    listViewRecords.setItems(FXCollections.observableArrayList(DaoFactory.criminalRecordsDao().getByIdNew(listView.getSelectionModel().getSelectedItem().getId())));}
                } catch (CriminalRecordsException ex) {
                    throw new RuntimeException(ex);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method that open window for employee profile
     * @param event
     */
    public void showProfile(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/profile.fxml"));
            loader.setController(new ProfileController(employee));
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

    /**
     * Method that deletes record from list and also moves record from db
     * Also this method refreshes the list
     * @param event
     */
    public void deleteRecord(ActionEvent event) {
        CriminalRecord r = listViewRecords.getSelectionModel().getSelectedItem();
        if (r != null) {
            try {
                DaoFactory.criminalRecordsDao().delete(r.getId());
                listViewRecords.setItems(FXCollections.observableArrayList(DaoFactory.criminalRecordsDao().getByIdNew(listView.getSelectionModel().getSelectedItem().getId())));
                placeRecordFld.setText("");
                descriptionRecordFld.setText("");
                dateRecordFld.setText(valueOf(""));
                codeRecordFld.setText("");
            } catch (CriminalRecordsException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Method for logout
     * @param event
     */
    public void showLogout(ActionEvent event) {
            Stage stage = (Stage) logoutBtn.getScene().getWindow();
            stage.close();
    }
}
