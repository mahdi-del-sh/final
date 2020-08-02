package sample.controller.employee;

import com.jfoenix.controls.JFXButton;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.controller.LoginMenuController;

public class Menu {


    @FXML
    private JFXButton SendMessageToManagementBTN;

    @FXML
    private JFXButton PersonalProfileManagementBTN;

    @FXML
    private JFXButton FlightManagementBTN;

    @FXML
    private JFXButton HomeBTN;
    LoginMenuController loginMenuController = new LoginMenuController();


    @FXML
    void initialize() {


        HomeBTN.setOnAction(event -> {
            loginMenuController.Home(HomeBTN);
        });

        PersonalProfileManagementBTN.setOnAction(event -> {
            loginMenuController.ChangeWindow(PersonalProfileManagementBTN , "/sample/view/employee/Profile/Profile.fxml", "Employee Profile");
        });

        SendMessageToManagementBTN.setOnAction(event -> {
            loginMenuController.ChangeWindow(SendMessageToManagementBTN , "/sample/view/employee/panel/EmployeeMessages.fxml", "Employee Message");
        });

        FlightManagementBTN.setOnAction(event -> {
            loginMenuController.ChangeWindow(SendMessageToManagementBTN , "/sample/view/employee/FlightAndPlane/Panel.fxml", "Employee Flight");

        });

    }

    public void BackToEmployeeMenu(Node node){
        node.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/view/employee/EmployeeMenu.fxml"));
        try{
            loader.load();
        }catch (IOException e){
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setTitle("Employee Menu");
        stage.setScene(new Scene(root));
        stage.show();
    }

}
