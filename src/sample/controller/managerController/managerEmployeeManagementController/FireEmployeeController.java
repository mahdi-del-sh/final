package sample.controller.managerController.managerEmployeeManagementController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import sample.controller.LoginMenuController;

public class FireEmployeeController {


    @FXML
    private JFXButton HomeBTN;

    @FXML
    private JFXTextField UserNameTXF;

    @FXML
    private Label ErrorLBL;

    @FXML
    private JFXButton FireBTN;

    LoginMenuController loginMenuController = new LoginMenuController();

    @FXML
    void initialize() {

        HomeBTN.setOnAction(event -> {
            loginMenuController.ChangeWindow(HomeBTN , "/sample/view/manager/managerPanel/managerEmployeeManagement/ManagerEmployeeManagement.fxml" , "Employee management");
        });

    }
}
