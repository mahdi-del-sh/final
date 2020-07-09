package sample.controller.managerController.managerPanelController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import sample.controller.managerController.ManagerMenuController;

public class ManagerProfileManagementController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton HomeBTN;

    @FXML
    private JFXTextField FirstNameTXF;

    @FXML
    private JFXButton RegisterBTN;

    @FXML
    private JFXTextField LastnameTXF;

    @FXML
    private JFXTextField UsernameTXF;

    @FXML
    private JFXTextField PhoneTXF;

    @FXML
    private JFXTextField AddressTXF;

    @FXML
    private JFXButton ChangePasswordBTN;

    @FXML
    private JFXTextField EmailTXF;

    @FXML
    private Label ErrorLBL;


    @FXML
    void initialize() {

        HomeBTN.setOnAction(event -> {
            ManagerMenuController managerMenuController =  new ManagerMenuController();
            managerMenuController.BackToManagerMenu(HomeBTN);
        });

    }
}
