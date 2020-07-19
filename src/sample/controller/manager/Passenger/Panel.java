package sample.controller.manager.Passenger;

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
    private JFXButton EditPassengerBTN;

    @FXML
    private JFXButton ViewPassengerBTN;

    @FXML
    private JFXButton DeletePassengerBTN;

    @FXML
    void initialize() {

        HomeBTN.setOnAction(event -> {
            menu managerMenuController =  new menu();
            managerMenuController.BackToManagerMenu(HomeBTN);
        });

    }
}
