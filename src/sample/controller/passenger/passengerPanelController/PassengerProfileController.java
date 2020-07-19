package sample.controller.passenger.passengerPanelController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import sample.controller.passenger.PassengerMenuController;

public class PassengerProfileController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton HomeBTN;

    @FXML
    private JFXTextField FirstNameTXF;

    @FXML
    private JFXButton RegisterBTN;

    @FXML
    private JFXTextField LastnameTXF;

    @FXML
    private JFXTextField UsernameTXF;

    @FXML
    private JFXTextField PhoneTXF;

    @FXML
    private JFXTextField ValidityTXF;

    @FXML
    private JFXButton ChangePasswordBTN;

    @FXML
    private JFXTextField EmailTXF;

    @FXML
    private JFXTextField PasswordTXF;

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
