

package sample.controller.managerController;

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

public class ManagerMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton PersonalProfileManagementBTN;

    @FXML
    private JFXButton EmployeeManagementBTN;

    @FXML
    private JFXButton MessageManagementBTN;

    @FXML
    private JFXButton FlightAndPlaneManagementBTN;

    @FXML
    private JFXButton HomeBTN;

    @FXML
    private JFXButton PassengerManagementBTN;

    @FXML
    void initialize() {

        HomeBTN.setOnAction(event -> {

            LoginMenuController loginMenuController =  new LoginMenuController();
            loginMenuController.Home(HomeBTN);

        });


    }
}




