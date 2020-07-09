package sample.controller.superadminController;

import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.controller.LoginMenuController;
import sample.controller.managerController.ManagerMenuController;

public class SuperAdminMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton PersonalProfileManagementBTN;

    @FXML
    private JFXButton AirportManagerManagementBTN;

    @FXML
    private JFXButton ReportManagementBTN;

    @FXML
    private JFXButton FinancialUnitManagementSuperBTN;

    @FXML
    private JFXButton HomeBTN;

    @FXML
    void initialize() {

        HomeBTN.setOnAction(event -> {
            LoginMenuController loginMenuController =  new LoginMenuController();
            loginMenuController.Home(HomeBTN);
        });

        PersonalProfileManagementBTN.setOnAction(event -> {
            LoginMenuController loginMenuController =  new LoginMenuController();
            loginMenuController.ChangeWindow(PersonalProfileManagementBTN , "/sample/view/superadmin/superadminPanel/superadminProfileManagement.fxml" , "Personal Profile Management");
        });

        ReportManagementBTN.setOnAction(event -> {
            LoginMenuController loginMenuController =  new LoginMenuController();
            loginMenuController.ChangeWindow(ReportManagementBTN , "/sample/view/superadmin/superadminPanel/superadminReportManagement.fxml" , "Report Management");

        });

        AirportManagerManagementBTN.setOnAction(event -> {
            LoginMenuController loginMenuController =  new LoginMenuController();
            loginMenuController.ChangeWindow(AirportManagerManagementBTN , "/sample/view/superadmin/superadminPanel/superadminManagerManagement.fxml" , "Airport Manager Management");

        });


        FinancialUnitManagementSuperBTN.setOnAction(event -> {
            LoginMenuController loginMenuController =  new LoginMenuController();
            loginMenuController.ChangeWindow(FinancialUnitManagementSuperBTN , "/sample/view/superadmin/superadminPanel/superadminFinancialManagement.fxml" , "Financial unit Management");

        });

    }

    public void BackToSuperAdminMenu(Node node){
        node.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/view/superadmin/superAdminMenu.fxml"));
        try{
            loader.load();
        }catch (IOException e){
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setTitle("SuperAdmin Menu");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
