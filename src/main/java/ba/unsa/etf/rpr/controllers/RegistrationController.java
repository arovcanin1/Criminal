package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.EmployeeManager;
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
 * Controller for handle registration window
 */
public class RegistrationController {

    /**
     * Attributes for RegistrationController
     */
    public TextField firstNameFld;
    public TextField lastNameFld;
    public TextField emailFld;
    public TextField usernameFld;
    public PasswordField passwordFld;
    public PasswordField passwordConfirmFld;
    public Button registerBtn;

    private EmployeeManager employeeManager = new EmployeeManager();

    /**
     * This is method for adding Employee in database when Employee makes registration
     * Also this method switch window to log in after employee presses button for registration
     * @param event
     */
    public void registration(ActionEvent event) {
        try {
            Employee employee = new Employee();
            employee.setFirstName(firstNameFld.getText());
            employee.setLastName(lastNameFld.getText());
            employee.setEmail(emailFld.getText());
            employee.setUsername(usernameFld.getText());
            employee.setPassword(passwordFld.getText());
            employeeManager.add(employee, passwordConfirmFld.getText());
            Stage stage = (Stage) registerBtn.getScene().getWindow();
            stage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
            loader.setController(new LoginController());
            Parent root = loader.load();
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
}
