package sample.controller.superadmin.Manager;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import sample.controller.LoginMenuController;
import sample.controller.superadmin.Menu;

public class Panel {


    @FXML
    private JFXButton EngageManagerBTN;

    @FXML
    private JFXButton EditManagersBTN;

    @FXML
    private JFXButton HomeBTN;

    LoginMenuController loginMenuController = new LoginMenuController();
    @FXML
    void initialize() {

        HomeBTN.setOnAction(event -> {
            Menu superAdminMenuController = new Menu();
            superAdminMenuController.BackToSuperAdminMenu(HomeBTN);
        });

        EngageManagerBTN.setOnAction(event -> {
            loginMenuController.ChangeWindow(EngageManagerBTN , "/sample/view/superadmin/Manager/EngageManager.fxml" , "Engage Manager");
        });
    }
}
