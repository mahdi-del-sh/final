package sample.controller.manager.Employee;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import sample.controller.LoginMenuController;
import sample.controller.manager.menu;

public class Panel {

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
            menu managerMenuController =  new menu();
            managerMenuController.BackToManagerMenu(HomeBTN);
        });

        EngageEmployeeBTN.setOnAction(event -> {
            loginMenuController.ChangeWindow(EngageEmployeeBTN , "/sample/view/manager/Employee/Engage.fxml", "Engage Employee");
        });

        FireEmployeeBTN.setOnAction(event -> {
            loginMenuController.ChangeWindow(FireEmployeeBTN , "/sample/view/manager/Employee/Fire.fxml", "Fire Employee");
        });

        EditEmployeeBTN.setOnAction(event -> {
            loginMenuController.ChangeWindow(EditEmployeeBTN , "/sample/view/manager/Employee/GetUsername.fxml", "Edit Employee");
        });
    }
}
