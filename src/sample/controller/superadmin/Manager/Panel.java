package sample.controller.superadmin.Manager;

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
            Menu superAdminMenuController = new Menu();
            superAdminMenuController.BackToSuperAdminMenu(HomeBTN);
        });
    }
}
