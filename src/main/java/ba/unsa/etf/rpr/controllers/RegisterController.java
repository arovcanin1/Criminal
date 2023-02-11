package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.EmployeeManager;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterController {

    public TextField firstNameFld;
    public TextField lastNameFld;
    public TextField emailFld;
    public TextField usernameFld;
    public PasswordField passwordFld;
    public PasswordField passwordConfirmFld;
    public Button registerBtn;

    private EmployeeManager userManager = new EmployeeManager();

    public void registration(ActionEvent event) {
    }
}
