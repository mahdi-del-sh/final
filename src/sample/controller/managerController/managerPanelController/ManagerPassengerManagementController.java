package sample.controller.managerController.managerPanelController;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import sample.controller.managerController.ManagerMenuController;

public class ManagerPassengerManagementController {

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
            ManagerMenuController managerMenuController =  new ManagerMenuController();
            managerMenuController.BackToManagerMenu(HomeBTN);
        });

    }
}
