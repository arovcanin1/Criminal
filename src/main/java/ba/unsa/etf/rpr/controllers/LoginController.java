package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.EmployeeManager;
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

/**
 * Controller for handle log in window
 */
public class LoginController {

    /**
     * Attributes for LoginController
     */
    public Button loginBtnClick;
    public TextField inputUsernameFld;
    public PasswordField inputPasswordFld;

    /**
     * This is method for showing Employee window after employee log in
     * @param event
     */
    public void showEmployeeWindow(ActionEvent event) {

        try {
            Employee employee = new Employee();
            employee.setUsername(inputUsernameFld.getText());
            employee.setPassword(inputPasswordFld.getText());
            (new EmployeeManager()).loginSearch(employee.getUsername(), employee.getPassword());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/employee.fxml"));
            Employee employee1 = DaoFactory.employeesDao().getByUsername(employee.getUsername());
            loader.setController(new EmployeeController(employee1));

            Stage s = (Stage) loginBtnClick.getScene().getWindow();
            s.close();
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("CR Employee");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }
}
