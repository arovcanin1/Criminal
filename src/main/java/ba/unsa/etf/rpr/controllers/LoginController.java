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

import java.nio.FloatBuffer;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class LoginController {

    public Button loginBtn;
    public TextField usernameFld;
    public PasswordField passwordFld;

    Employee employee = new Employee();


    public void showEmployeeWindow(ActionEvent event) {

        try {
            Employee emp = DaoFactory.employeesDao().getByUsername(employee.getUsername());
            employee.setUsername(usernameFld.getText());
            employee.setPassword(passwordFld.getText());

            (new EmployeeManager()).loginSearch(employee.getUsername(), employee.getPassword());
            Stage s = (Stage) loginBtn.getScene().getWindow();
            s.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/example.fxml"));
            loader.setController(new ExampleController());
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
