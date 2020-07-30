package sample.controller.superadmin.Financial;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import sample.controller.superadmin.Menu;

public class Panel {


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
            Menu superAdminMenuController =  new Menu();
            superAdminMenuController.BackToSuperAdminMenu(HomeBTN);
        });


    }
}
