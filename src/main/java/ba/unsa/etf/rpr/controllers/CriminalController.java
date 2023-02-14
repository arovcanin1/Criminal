package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.domain.Criminal;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class CriminalController {
     Button addCriminalBtn;

    public void showAddCriminal(ActionEvent event) {
        try {
            Stage stage = new Stage();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addCriminal.fxml"));
            loader.setController(new AddCriminalController());
            Parent root = loader.load();
            stage.setTitle("CR Add Criminals");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
