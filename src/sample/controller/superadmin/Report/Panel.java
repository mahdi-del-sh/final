package sample.controller.superadmin.Report;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import sample.controller.superadmin.Menu;

public class Panel {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton ManagerBTN;

    @FXML
    private JFXButton EmployeeBTN;

    @FXML
    private JFXButton PassengerBTN;

    @FXML
    private JFXButton HomeBTN;

    @FXML
    void initialize() {

        HomeBTN.setOnAction(event -> {
            Menu superAdminMenuController = new Menu();
            superAdminMenuController.BackToSuperAdminMenu(HomeBTN);
        });

    }
}
