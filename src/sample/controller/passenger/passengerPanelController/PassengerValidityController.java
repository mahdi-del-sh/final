package sample.controller.passenger.passengerPanelController;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import sample.controller.passenger.PassengerMenuController;

public class PassengerValidityController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton HomeBTN;

    @FXML
    private JFXButton ConfirmBTN;

    @FXML
    private Label ErrorLBL;

    PassengerMenuController passengerMenuController =  new PassengerMenuController();

    @FXML
    void initialize() {

        HomeBTN.setOnAction(event -> {
            passengerMenuController.BackToPassengerMenu(HomeBTN);
        });

    }
}
