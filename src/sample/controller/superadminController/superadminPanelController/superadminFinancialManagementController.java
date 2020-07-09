package sample.controller.superadminController.superadminPanelController;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import sample.controller.superadminController.SuperAdminMenuController;

public class superadminFinancialManagementController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton FirstBTN;

    @FXML
    private JFXButton SecondBTN;

    @FXML
    private JFXButton FixedBTN;

    @FXML
    private JFXButton HomeBTN;

    @FXML
    void initialize() {

        HomeBTN.setOnAction(event -> {
            SuperAdminMenuController superAdminMenuController =  new SuperAdminMenuController();
            superAdminMenuController.BackToSuperAdminMenu(HomeBTN);
        });


    }
}
