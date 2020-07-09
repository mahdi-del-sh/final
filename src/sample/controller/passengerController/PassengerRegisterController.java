package sample.controller.passengerController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import sample.controller.LoginMenuController;

public class PassengerRegisterController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton HomeBTN;

    @FXML
    private JFXButton RegisterBTN;

    @FXML
    private JFXTextField FirstNameTXF;

    @FXML
    private JFXTextField LastNameTXF;

    @FXML
    private JFXTextField UserNameTXF;

    @FXML
    private JFXTextField PhoneTXF;

    @FXML
    private JFXTextField CreditTXF;

    @FXML
    private JFXTextField EmailTXF;

    @FXML
    private JFXTextField PasswordTXF;

    @FXML
    private JFXButton QuestionBTN;

    @FXML
    void initialize() {

        HomeBTN.setOnAction(event -> {
            LoginMenuController loginMenuController =  new LoginMenuController();
            loginMenuController.ChangeWindow(HomeBTN , "/sample/view/passenger/PassengerLogIn.fxml" , "Passenger Login");
        });

    }
}
