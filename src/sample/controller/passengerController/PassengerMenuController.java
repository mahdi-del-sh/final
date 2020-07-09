package sample.controller.passengerController;

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
    private JFXButton ProfileManagementBTN;

    @FXML
    private JFXButton TicketManagementBTN;

    @FXML
    private JFXButton IncreaseValidityBTN;

    LoginMenuController loginMenuController =  new LoginMenuController();


    @FXML
    void initialize() {

        HomeBTN.setOnAction(event -> {
            loginMenuController.Home(HomeBTN);
        });

        ProfileManagementBTN.setOnAction(event -> {
             loginMenuController.ChangeWindow(ProfileManagementBTN , "/sample/view/passenger/passengerPanel/PassengerProfile.fxml" , "Profile Management");
        });

        SendMessageToManagerBTN.setOnAction(event -> {
            loginMenuController.ChangeWindow(SendMessageToManagerBTN , "/sample/view/passenger/passengerPanel/PassengerMessage.fxml" , "Message Management");
        });

        TicketManagementBTN.setOnAction(event -> {
            loginMenuController.ChangeWindow(TicketManagementBTN , "/sample/view/passenger/passengerPanel/PassengerTicket.fxml" , "Ticket Management");
        });

        IncreaseValidityBTN.setOnAction(event -> {
            loginMenuController.ChangeWindow(IncreaseValidityBTN , "/sample/view/passenger/passengerPanel/PassengerValidity.fxml" , "Increase Validity");
        });
    }

    public void BackToPassengerMenu(Node node){
        node.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/view/passenger/PassengerMenu.fxml"));
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
