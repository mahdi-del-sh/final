package sample.controller.managerController.managerPanelController;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import sample.controller.managerController.ManagerMenuController;

public class ManagerFlightAndPlaneManagementController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton HomeBTN;

    @FXML
    private JFXButton ViewPlaneBTN;

    @FXML
    private JFXButton DeletePlaneBTN;

    @FXML
    private JFXButton EditPlaneBTN;

    @FXML
    private JFXButton AddFlightBTN;

    @FXML
    private JFXButton DeleteFlightBTN;

    @FXML
    private JFXButton EditFlightBTN;

    @FXML
    private JFXButton ViewFlightBTN;

    @FXML
    private JFXButton AddPlaneBTN;

    @FXML
    void initialize() {
     HomeBTN.setOnAction(event -> {
         ManagerMenuController managerMenuController =  new ManagerMenuController();
         managerMenuController.BackToManagerMenu(HomeBTN);
     });

    }
}
