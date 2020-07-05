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

public class ManagerMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton PersonalProfileManagemantManagerPanelBTN;

    @FXML
    private JFXButton EmployeeManagemantManagerPanelBTN;

    @FXML
    private JFXButton MessageManagemantManagerBTN;

    @FXML
    private JFXButton FlightAndPlaneManagemantManagerBTN;

    @FXML
    private JFXButton HomeManagerPanelBTN;

    @FXML
    private JFXButton PassengerManagemantManagerPanelBTN;

    @FXML
    void initialize() {

        HomeManagerPanelBTN.setOnAction(event -> {

            HomeManagerPanelBTN.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/LoginMenu.fxml"));
            try{
                loader.load();
            }catch (IOException e){
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setTitle("Login Menu");
            stage.setScene(new Scene(root));
            stage.showAndWait();


        });

    }
}

