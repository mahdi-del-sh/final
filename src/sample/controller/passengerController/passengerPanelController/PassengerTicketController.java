package sample.controller.passengerController.passengerPanelController;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import sample.controller.passengerController.PassengerMenuController;

public class PassengerTicketController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton HomeBTN;

    @FXML
    private JFXButton CancelBTN;

    @FXML
    private JFXButton BuyBTN;

    PassengerMenuController passengerMenuController  = new PassengerMenuController();

    @FXML
    void initialize() {

        HomeBTN.setOnAction(event -> {

            passengerMenuController.BackToPassengerMenu(HomeBTN);
        });

    }
}
