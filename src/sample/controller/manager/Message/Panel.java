package sample.controller.manager.Message;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import sample.controller.manager.menu;

public class Panel {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton HomeBTN;

    @FXML
    private JFXButton ViewPassengerMasssegeBTN;

    @FXML
    private JFXButton DeletePassengerMessageBTN;

    @FXML
    private JFXButton ViewEmployeeMessageBTN;

    @FXML
    private JFXButton DeleteEmployeeMessageBTN;

    @FXML
    void initialize() {

        HomeBTN.setOnAction(event -> {
            menu managerMenuController =  new menu();
            managerMenuController.BackToManagerMenu(HomeBTN);
        });

    }
}
