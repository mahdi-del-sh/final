package sample.controller.superadminController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;

public class SuperAdminLogInController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton HomeSuperAdminPanelBTN;

    @FXML
    private JFXPasswordField PasswordTXF;

    @FXML
    private JFXTextField UserNameTXF;

    @FXML
    private JFXButton EnterBTN;

    @FXML
    void initialize() {
        assert HomeSuperAdminPanelBTN != null : "fx:id=\"HomeSuperAdminPanelBTN\" was not injected: check your FXML file 'superAdminLogIn.fxml'.";
        assert PasswordTXF != null : "fx:id=\"PasswordTXF\" was not injected: check your FXML file 'superAdminLogIn.fxml'.";
        assert UserNameTXF != null : "fx:id=\"UserNameTXF\" was not injected: check your FXML file 'superAdminLogIn.fxml'.";
        assert EnterBTN != null : "fx:id=\"EnterBTN\" was not injected: check your FXML file 'superAdminLogIn.fxml'.";

    }
}
