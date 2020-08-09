package sample.controller.employee.FlightAndPlane;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import sample.controller.LoginMenuController;
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
         loginMenuController.ChangeWindow(HomeBTN , "/sample/view/employee/Menu.fxml", "");
     });


     //plane :

     AddPlaneBTN.setOnAction(event -> {
         loginMenuController.ChangeWindow(AddPlaneBTN , "/sample/view/employee/FlightAndPlane/Plane/Add/Add.fxml" , "Add Plane");
     });

     ViewPlaneBTN.setOnAction(event -> {
         loginMenuController.ChangeWindow(ViewPlaneBTN , "/sample/view/employee/FlightAndPlane/Plane/View/View.fxml" , "View Plane");
     });

     //flight :


        AddFlightBTN.setOnAction(event -> {

           loginMenuController.ChangeWindow(AddFlightBTN , "/sample/view/employee/FlightAndPlane/Flight/Add/Add.fxml" , "Add Flight");
        });

        ViewFlightBTN.setOnAction(event -> {
            loginMenuController.ChangeWindow(ViewFlightBTN , "/sample/view/employee/FlightAndPlane/Flight/View/View.fxml" , "View Flight");
        });

    }
}
