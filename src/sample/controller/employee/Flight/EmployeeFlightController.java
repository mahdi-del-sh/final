package sample.controller.employee.Flight;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import sample.controller.LoginMenuController;
import sample.controller.employee.Menu;

public class EmployeeFlightController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton HomeBTN;

    @FXML
    private JFXButton AddFlightBTN;

    @FXML
    private JFXButton DeleteFlightBTN;

    @FXML
    private JFXButton EditFlightBTN;

    @FXML
    private JFXButton ViewFlightBTN;

    @FXML
    private JFXButton ViewPassengersBTN;

    LoginMenuController loginMenuController =  new LoginMenuController();
    Menu passengerMenuController =  new Menu();

    @FXML
    void initialize() {

        HomeBTN.setOnAction(event -> {
            passengerMenuController.BackToEmployeeMenu(HomeBTN);
        });


    }
}
