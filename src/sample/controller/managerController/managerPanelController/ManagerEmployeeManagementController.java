package sample.controller.managerController.managerPanelController;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import sample.controller.managerController.ManagerMenuController;

public class ManagerEmployeeManagementController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton EngageEmployeeBTN;

    @FXML
    private JFXButton FireEmployeeBTN;

    @FXML
    private JFXButton EditEmployeeBTN;

    @FXML
    private JFXButton HomeBTN;

    @FXML
    void initialize() {
        HomeBTN.setOnAction(event -> {
            ManagerMenuController managerMenuController =  new ManagerMenuController();
            managerMenuController.BackToManagerMenu(HomeBTN);
        });
    }
}
