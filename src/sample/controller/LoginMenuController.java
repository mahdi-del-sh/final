package sample.controller;

import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton passengerLoginBTN;

    @FXML
    private JFXButton employeeLoginBTN;

    @FXML
    private JFXButton managerLoginBTN;

    @FXML
    private JFXButton superadminLoginBTN;

    @FXML
    void initialize() {


        superadminLoginBTN.setOnAction(event -> {

            superadminLoginBTN.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/superadmin/superAdminLogIn.fxml"));
            try{
                loader.load();
            }catch (IOException e){
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setTitle("SuperAdmin Login");
            stage.setScene(new Scene(root));
            stage.show();


        });

        managerLoginBTN.setOnAction(event -> {

            superadminLoginBTN.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/manager/managerLogIn.fxml"));
            try{
                loader.load();
            }catch (IOException e){
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setTitle("Manager Login");
            stage.setScene(new Scene(root));
            stage.show();


        });

        employeeLoginBTN.setOnAction(event -> {

            superadminLoginBTN.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/employee/EmployeeLogIn.fxml"));
            try{
                loader.load();
            }catch (IOException e){
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setTitle("Employee Login");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });

        passengerLoginBTN.setOnAction(event -> {

            superadminLoginBTN.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/passenger/PassengerLogIn.fxml"));
        try{
            loader.load();
        }catch (IOException e){
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setTitle("Passenger Login");
        stage.setScene(new Scene(root));
        stage.showAndWait();
    });




    }
}

