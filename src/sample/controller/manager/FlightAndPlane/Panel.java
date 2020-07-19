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

     EditPlaneBTN.setOnAction(event -> {
         loginMenuController.ChangeWindow(EditPlaneBTN , "/sample/view/manager/FlightAndPlane/Plane/Edit/Edit.fxml" , "Edit Plane");
     });

     DeletePlaneBTN.setOnAction(event -> {
         loginMenuController.ChangeWindow(DeletePlaneBTN , "/sample/view/manager/FlightAndPlane/Plane/Delete/Delete.fxml" , "Delete Plane");
     });

     //flight :


        AddFlightBTN.setOnAction(event -> {
            loginMenuController.ChangeWindow(AddFlightBTN , "/sample/view/manager/FlightAndPlane/Flight/Add/Add.fxml" , "Add Flight");
        });

        ViewFlightBTN.setOnAction(event -> {
            loginMenuController.ChangeWindow(ViewFlightBTN , "/sample/view/manager/FlightAndPlane/Flight/View/View.fxml" , "View Flight");
        });

        EditFlightBTN.setOnAction(event -> {
            loginMenuController.ChangeWindow(EditFlightBTN , "/sample/view/manager/FlightAndPlane/Flight/Edit/Edit.fxml" , "Edit Flight");
        });

        DeleteFlightBTN.setOnAction(event -> {
            loginMenuController.ChangeWindow(DeleteFlightBTN , "/sample/view/manager/FlightAndPlane/Flight/Delete/Delete.fxml" , "Delete Flight");
        });

    }
}
