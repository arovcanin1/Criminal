package ba.unsa.etf.rpr.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class HomeController {
    public Button registerBtn;

    public void showRegistration(ActionEvent event) {

        try {
            Stage s = (Stage) registerBtn.getScene().getWindow();
            s.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/registration.fxml"));
            loader.setController(new RegistrationController());
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("CR Registration");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
