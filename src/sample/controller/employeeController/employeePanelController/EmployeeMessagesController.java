package sample.controller.employeeController.employeePanelController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import sample.controller.LoginMenuController;
import sample.controller.employeeController.EmployeeMenuController;

public class EmployeeMessagesController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton HomeBTN;

    @FXML
    private JFXButton SendBTN;

    @FXML
    private Label ErrorLBL;

    @FXML
    private JFXTextArea MessageTXA;

    EmployeeMenuController employeeMenuController =  new EmployeeMenuController();
    LoginMenuController loginMenuController = new LoginMenuController();

    @FXML
    void initialize() {

        HomeBTN.setOnAction(event -> {
            employeeMenuController.BackToEmployeeMenu(HomeBTN);
        });


    }
}
