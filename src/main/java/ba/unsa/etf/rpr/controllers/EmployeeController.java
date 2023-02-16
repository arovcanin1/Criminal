package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Criminal;
import ba.unsa.etf.rpr.domain.CriminalRecord;
import ba.unsa.etf.rpr.domain.Employee;
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
    public ListView listView;
    public ListView listViewRecords;
    public TextField placeRecordFld;
    public TextField descriptionRecordFld;
    public TextField codeRecordFld;
    public TextField dateRecordFld;
    Criminal c = new Criminal();

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

    /**
     * Initialize method that is called as soon as window is opened
     * This method contains listeners for list of criminals and records
     */
    public void initialize() {
        try {
            ObservableList criminalItems = FXCollections.observableArrayList();
            List<Criminal> criminalsList = DaoFactory.criminalsDao().allCriminals();

            for (int i = 0; i < criminalsList.size(); i++) {
                criminalItems.add(criminalsList.get(i).getJmbg());
            }
            listView.setItems(criminalItems);
        } catch (Exception e) {
            e.printStackTrace();
        }

            listView.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {

                ObservableList allRecordsItems = FXCollections.observableArrayList();

                jmbgFld.setText(listView.getSelectionModel()
                        .getSelectedItem()
                        .toString());
                    try {
                        c = DaoFactory.criminalsDao().getByJMBG(listView
                                .getSelectionModel()
                                .getSelectedItem()
                                .toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                try {
                    if (listView.getSelectionModel().getSelectedItem() != null) {
                        firstNameFld.setText(DaoFactory.criminalsDao().
                                     getByJMBG(listView.getSelectionModel().getSelectedItem().toString()).getFirstName());

                        lastNameFld.setText(DaoFactory
                                .criminalsDao()
                                .getByJMBG(listView
                                        .getSelectionModel()
                                        .getSelectedItem()
                                        .toString())
                                .getLastName());

                        birthDateFld.setText(valueOf(DaoFactory
                                .criminalsDao().
                                getByJMBG(listView
                                        .getSelectionModel()
                                        .getSelectedItem()
                                        .toString())
                                .getBirthDate()));

                        List<CriminalRecord> allRecords = new ArrayList<>();
                        allRecords = (DaoFactory
                                .criminalRecordsDao()
                                .getByIdNew(DaoFactory
                                        .criminalsDao()
                                        .getByJMBG(listView
                                                .getSelectionModel()
                                                .getSelectedItem()
                                                .toString())
                                        .getId()));

                        for (int i = 0; i < allRecords.size(); i++) {
                            allRecordsItems.add(allRecords.get(i).getCode());
                        }
                        listViewRecords.setItems(allRecordsItems);
                    }
                    } catch(Exception e){
                        e.printStackTrace();
                    }
            });

        listViewRecords.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            try {
                placeRecordFld.setText("");
                dateRecordFld.setText("");
                descriptionRecordFld.setText("");
                codeRecordFld.setText("");
                if (listViewRecords.getSelectionModel().getSelectedItem() != null) {
                    placeRecordFld.setText(DaoFactory
                            .criminalRecordsDao()
                            .getByCode(listViewRecords
                                    .getSelectionModel()
                                    .getSelectedItem()
                                    .toString())
                            .getPlace());

                    dateRecordFld.setText(valueOf(DaoFactory
                            .criminalRecordsDao()
                            .getByCode(listViewRecords
                                    .getSelectionModel()
                                    .getSelectedItem()
                                    .toString())
                            .getDate()));

                    descriptionRecordFld.setText(DaoFactory
                            .criminalRecordsDao()
                            .getByCode(listViewRecords
                                    .getSelectionModel()
                                    .getSelectedItem()
                                    .toString())
                            .getDescription());

                    codeRecordFld.setText(DaoFactory
                            .criminalRecordsDao()
                            .getByCode(listViewRecords
                                    .getSelectionModel()
                                    .getSelectedItem()
                                    .toString())
                            .getCode());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
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
            CriminalController criminalController = loader.getController();
            listView.refresh();
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
        int selected = listViewRecords.getSelectionModel().getSelectedIndex();
        if (selected != -1) {
            Object delete = listViewRecords.getSelectionModel().getSelectedItem();

            int newSelected = (selected == listViewRecords.getItems().size() - 1)
                    ? selected-1
                    : selected;

            listViewRecords.getItems().remove(selected);

            try {
                DaoFactory.criminalRecordsDao()
                        .delete(DaoFactory
                                .criminalRecordsDao()
                                .getByCode(listViewRecords
                                        .getSelectionModel()
                                        .getSelectedItem()
                                        .toString())
                                .getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
            listView.refresh();
            listView.getSelectionModel().select(newSelected);
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
