package sample.controller.employee.panel;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import sample.controller.LoginMenuController;
import sample.controller.employee.EmployeeMenuController;

public class EmployeeProfileController {

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

    EmployeeMenuController employeeMenuController =  new EmployeeMenuController();
    LoginMenuController loginMenuController = new LoginMenuController();


    @FXML
    void initialize() {

        HomeBTN.setOnAction(event -> {
            employeeMenuController.BackToEmployeeMenu(HomeBTN);
        });

    }
}
