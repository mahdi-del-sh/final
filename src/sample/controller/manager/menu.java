

package sample.controller.manager;

import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.controller.LoginMenuController;

public class menu {

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

        PersonalProfileManagementBTN.setOnAction(event -> {
            LoginMenuController loginMenuController = new LoginMenuController();
            loginMenuController.ChangeWindow(PersonalProfileManagementBTN , "/sample/view/manager/Profile/ChnageProfile.fxml","Manager Profile Management" );
        });

        EmployeeManagementBTN.setOnAction(event -> {
            LoginMenuController loginMenuController = new LoginMenuController();
loginMenuController.ChangeWindow(EmployeeManagementBTN , "/sample/view/manager/Employee/Panel.fxml", "Employee Management" );
        });

        FlightAndPlaneManagementBTN.setOnAction(event -> {
            LoginMenuController loginMenuController = new LoginMenuController();
            loginMenuController.ChangeWindow(EmployeeManagementBTN , "/sample/view/manager/FlightAndPlane/Panel.fxml", "Flight and Plane Management" );

        });


        MessageManagementBTN.setOnAction(event -> {
            LoginMenuController loginMenuController = new LoginMenuController();
            loginMenuController.ChangeWindow(EmployeeManagementBTN , "/sample/view/manager/Message/Panel.fxml", "Messages Management" );

        });

        PassengerManagementBTN.setOnAction(event -> {
            LoginMenuController loginMenuController = new LoginMenuController();
            loginMenuController.ChangeWindow(EmployeeManagementBTN , "/sample/view/manager/Passenger/Panel.fxml", "Passenger Management" );
        });


    }

    public void BackToManagerMenu(Node node){
        node.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/view/manager/menu.fxml"));
        try{
            loader.load();
        }catch (IOException e){
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setTitle("Manager Menu");
        stage.setScene(new Scene(root));
        stage.show();
    }


}




