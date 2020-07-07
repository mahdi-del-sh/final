package sample.controller.superadminController;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import sample.controller.LoginMenuController;

public class SuperAdminMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton PersonalProfileManagementBTN;

    @FXML
    private JFXButton AirportManagerManagementBTN;

    @FXML
    private JFXButton ReportManagementBTN;

    @FXML
    private JFXButton FinancialUnitManagementSuperBTN;

    @FXML
    private JFXButton HomeBTN;

    @FXML
    void initialize() {

        HomeBTN.setOnAction(event -> {
            LoginMenuController loginMenuController =  new LoginMenuController();
            loginMenuController.Home(HomeBTN);
        });
    }
}
