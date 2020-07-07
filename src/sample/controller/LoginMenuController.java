package sample.controller;

import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.mysql.cj.jdbc.DatabaseMetaData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Database.DatabaseHandler;
import sample.model.Employee;
import sample.model.Manager;
import sample.model.Passenger;
import sample.model.Person;

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

            ChangeWindow(superadminLoginBTN, "/sample/view/superadmin/SuperAdminLogIn.fxml" , "SuperAdmin Login"  );




        });

        managerLoginBTN.setOnAction(event -> {

            ChangeWindow(managerLoginBTN, "/sample/view/manager/managerLogIn.fxml" , "Manager Login"  );




        });

        employeeLoginBTN.setOnAction(event -> {

            ChangeWindow(employeeLoginBTN, "/sample/view/employee/EmployeeLogIn.fxml" , "Employee Login"  );


        });

        passengerLoginBTN.setOnAction(event -> {

            ChangeWindow(passengerLoginBTN, "/sample/view/passenger/PassengerLogIn.fxml" , "Passenger Login"  );

    });


    }


    // back to home method :

    public void ChangeWindow(Node node , String name , String Title){

        node.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(name));
        try{
            loader.load();
        }catch (IOException e){
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setTitle(Title);
        stage.setScene(new Scene(root));
        stage.show();

    }

    public void Home(Node node){
        node.getScene().getWindow().hide();
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
        stage.show();
    }

}

