package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.CriminalRecordManager;
import ba.unsa.etf.rpr.dao.Dao;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Criminal;
import ba.unsa.etf.rpr.domain.CriminalRecord;
import ba.unsa.etf.rpr.exceptions.CriminalRecordsException;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * Controller for handle adding new record
 */
public class CriminalRecordController {

    /**
     * Attributes for CriminalRecordController
     */
    private Criminal criminal;
    private CriminalRecordManager criminalRecordMan = new CriminalRecordManager();
    public TextField placeFld;
    public DatePicker dateFld;
    public TextField codeFld;
    public TextField descriptionFld;
    public Button addRecordBtn;
    public ListView listView;

    /**
     * Constructor for CriminalRecordController with one parameter
     * @param criminal
     */
    public  CriminalRecordController(Criminal criminal) {
        this.criminal = criminal;
    }

    /**
     * Constructor for CriminalRecordController without parameters
     */
    public CriminalRecordController() {
        criminal = new Criminal();
    }



    /**
     * Method that add new record for criminal
     * @param event
     */

    public void addNewRecord(ActionEvent event) {
        try {
            CriminalRecord criminalRecord = new CriminalRecord();
            criminalRecord.setId(1);
            criminalRecord.setCriminal(criminal);
            criminalRecord.setPlace(placeFld.getText());
            criminalRecord.setDate(dateFld.getValue());
            criminalRecord.setCode(codeFld.getText());
            criminalRecord.setDescription(descriptionFld.getText());
            criminalRecordMan.add(criminalRecord);
            Stage stage = (Stage) addRecordBtn.getScene().getWindow();
            stage.close();
        } catch (CriminalRecordsException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
}
