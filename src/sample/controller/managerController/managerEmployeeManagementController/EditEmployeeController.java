package sample.controller.managerController.managerEmployeeManagementController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import sample.controller.LoginMenuController;

public class EditEmployeeController {

    @FXML
    private JFXButton HomeBTN;

    @FXML
    private JFXTextField FirstNameTXF;

    @FXML
    private JFXButton ConfirmBTN;

    @FXML
    private JFXTextField LastnameTXF;

    @FXML
    private JFXTextField UsernameTXF;

    @FXML
    private JFXTextField PhoneTXF;

    @FXML
    private JFXTextField AddressTXF;

    @FXML
    private JFXButton SetPasswordBTN;

    @FXML
    private JFXTextField EmailTXF;

    @FXML
    private Label EmailLBL;

    @FXML
    private Label AddressLBL;

    @FXML
    private Label LastnameLBL;

    @FXML
    private Label PhoneLBL;

    @FXML
    private Label FirstnameLBL;

    @FXML
    private Label UserNameLBL;

    @FXML
    private JFXTextField SalaryTXF;

    @FXML
    private Label SalaryLBL;

    LoginMenuController loginMenuController = new LoginMenuController();
    @FXML
    void initialize() {

        HomeBTN.setOnAction(event -> {
            loginMenuController.ChangeWindow(HomeBTN , "/sample/view/manager/managerPanel/managerEmployeeManagement/ManagerEmployeeManagement.fxml" , "Employee management");
        });

    }
}
