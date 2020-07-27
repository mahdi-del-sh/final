package sample.controller.manager.FlightAndPlane;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import sample.controller.LoginMenuController;
import sample.controller.manager.FlightAndPlane.Flight.Delete.Delete;
import sample.controller.manager.menu;

public class Panel {

    @FXML
    private JFXButton HomeBTN;

    @FXML
    private JFXButton ViewPlaneBTN;

    @FXML
    private JFXButton AddFlightBTN;

    @FXML
    private JFXButton ViewFlightBTN;

    @FXML
    private JFXButton AddPlaneBTN;

    LoginMenuController loginMenuController = new LoginMenuController();
    @FXML
    void initialize() {
     HomeBTN.setOnAction(event -> {
         menu managerMenuController =  new menu();
         managerMenuController.BackToManagerMenu(HomeBTN);
     });


     //plane :

     AddPlaneBTN.setOnAction(event -> {
         loginMenuController.ChangeWindow(AddPlaneBTN , "/sample/view/manager/FlightAndPlane/Plane/Add/Add.fxml" , "Add Plane");
     });

     ViewPlaneBTN.setOnAction(event -> {
         loginMenuController.ChangeWindow(ViewPlaneBTN , "/sample/view/manager/FlightAndPlane/Plane/View/View.fxml" , "View Plane");
     });

     //flight :


        AddFlightBTN.setOnAction(event -> {
            loginMenuController.ChangeWindow(AddFlightBTN , "/sample/view/manager/FlightAndPlane/Flight/Add/Add.fxml" , "Add Flight");
        });

        ViewFlightBTN.setOnAction(event -> {
            loginMenuController.ChangeWindow(ViewFlightBTN , "/sample/view/manager/FlightAndPlane/Flight/View/View.fxml" , "View Flight");
        });

    }
}
