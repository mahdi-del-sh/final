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

public class PassengerMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton HomePassengerPanelBTN;

    @FXML
    private JFXButton SendMessageToManagerPassengerPanelBTN;

    @FXML
    private JFXButton RegisterPassengerPanelBTN;

    @FXML
    private JFXButton ProfileManagemantPassengerPanelBTN;

    @FXML
    private JFXButton TicketManagemantPassengerPanelBTN;

    @FXML
    private JFXButton IncreaseValidityPassengerPanelBTN;

    @FXML
    void initialize() {

        HomePassengerPanelBTN.setOnAction(event -> {
            HomePassengerPanelBTN.getScene().getWindow().hide();
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
