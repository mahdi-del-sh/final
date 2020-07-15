package sample.controller.managerController.managerPanelController.managerEmployeeManagementController;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import sample.controller.LoginMenuController;
import sample.controller.managerController.ManagerMenuController;

public class ManagerEmployeeManagementController {

    @FXML
    private JFXButton EngageEmployeeBTN;

    @FXML
    private JFXButton FireEmployeeBTN;

    @FXML
    private JFXButton EditEmployeeBTN;

    @FXML
    private JFXButton HomeBTN;

    LoginMenuController loginMenuController = new LoginMenuController();
    @FXML
    void initialize() {

        HomeBTN.setOnAction(event -> {
            ManagerMenuController managerMenuController =  new ManagerMenuController();
            managerMenuController.BackToManagerMenu(HomeBTN);
        });

        EngageEmployeeBTN.setOnAction(event -> {
            loginMenuController.ChangeWindow(EngageEmployeeBTN , "/sample/view/manager/managerEmployeeManagement/EngageEmployee.fxml" , "Engage Employee");
        });

        FireEmployeeBTN.setOnAction(event -> {
            loginMenuController.ChangeWindow(FireEmployeeBTN , "/sample/view/manager/managerEmployeeManagement/FireEmployee.fxml" , "Fire Employee");
        });

        EditEmployeeBTN.setOnAction(event -> {
            loginMenuController.ChangeWindow(EditEmployeeBTN , "/sample/view/manager/managerEmployeeManagement/EnterUsernameEmployee.fxml" , "Edit Employee");
        });
    }
}
