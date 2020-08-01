package sample.controller.passenger.passengerPanelController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import sample.controller.passenger.Menu;

public class PassengerMessageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton HomeBTN;

    @FXML
    private JFXButton SendBTN;

    @FXML
    private Label ErrorLBL;

    @FXML
    private JFXTextArea MessageTXA;

    Menu passengerMenuController =  new Menu();

    @FXML
    void initialize() {

        HomeBTN.setOnAction(event -> {
            passengerMenuController.BackToPassengerMenu(HomeBTN);
        });

    }
}
