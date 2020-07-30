package sample.controller.manager.Message;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import sample.controller.LoginMenuController;
import sample.controller.manager.menu;

public class Panel {


    @FXML
    private JFXButton HomeBTN;

    @FXML
    private JFXButton ViewPassenger;


    @FXML
    private JFXButton ViewEmployee;


    LoginMenuController loginMenuController = new LoginMenuController();
    @FXML
    void initialize() {

        HomeBTN.setOnAction(event -> {
            menu managerMenuController =  new menu();
            managerMenuController.BackToManagerMenu(HomeBTN);
        });

        ViewPassenger.setOnAction(event -> {
            loginMenuController.ChangeWindow(ViewEmployee , "/sample/view/manager/Message/ViewPassenger.fxml" , "Message management");

        });


        ViewEmployee.setOnAction(event -> {
loginMenuController.ChangeWindow(ViewEmployee , "/sample/view/manager/Message/ViewEmployee.fxml" , "Message management");
        });



    }
}
