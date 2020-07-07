package sample.controller.passengerController;

import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.controller.LoginMenuController;

public class PassengerMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private JFXButton HomeBTN;

    @FXML
    private JFXButton SendMessageToManagerBTN;

    @FXML
    private JFXButton RegisterBTN;

    @FXML
    private JFXButton ProfileManagementBTN;

    @FXML
    private JFXButton TicketManagementBTN;

    @FXML
    private JFXButton IncreaseValidityBTN;


    @FXML
    void initialize() {

        HomeBTN.setOnAction(event -> {

LoginMenuController loginMenuController =  new LoginMenuController();
loginMenuController.Home(HomeBTN);

        });


    }
}
