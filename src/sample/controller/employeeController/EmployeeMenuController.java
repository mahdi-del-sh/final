package sample.controller.employeeController;

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

public class EmployeeMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private JFXButton SendMessageToManagementBTN;

    @FXML
    private JFXButton PersonalProfileManagementBTN;

    @FXML
    private JFXButton FlightManagementBTN;

    @FXML
    private JFXButton HomeBTN;

    @FXML
    void initialize() {


        HomeBTN.setOnAction(event -> {
            LoginMenuController loginMenuController = new LoginMenuController();
            loginMenuController.Home(HomeBTN);
        });


    }
}
