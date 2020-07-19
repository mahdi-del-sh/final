package sample.controller.superadmin.superadminPanelController;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import sample.controller.superadmin.SuperAdminMenuController;

public class superadminManagerManagementController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton EngageManagerBTN;

    @FXML
    private JFXButton FireManagersBTN;

    @FXML
    private JFXButton EditManagersBTN;

    @FXML
    private JFXButton HomeBTN;

    @FXML
    void initialize() {

        HomeBTN.setOnAction(event -> {
            SuperAdminMenuController superAdminMenuController = new SuperAdminMenuController();
            superAdminMenuController.BackToSuperAdminMenu(HomeBTN);
        });
    }
}
