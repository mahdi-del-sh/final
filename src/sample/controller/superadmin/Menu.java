package sample.controller.superadmin;

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

public class Menu {

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
    LoginMenuController loginMenuController =  new LoginMenuController();

    @FXML
    void initialize() {

        HomeBTN.setOnAction(event -> {
            loginMenuController.Home(HomeBTN);
        });

        PersonalProfileManagementBTN.setOnAction(event -> {
            loginMenuController.ChangeWindow(PersonalProfileManagementBTN , "/sample/view/superadmin/Profile/Profile.fxml", "Personal Profile Management");
        });

        ReportManagementBTN.setOnAction(event -> {
            loginMenuController.ChangeWindow(ReportManagementBTN , "/sample/view/superadmin/Report/Panel.fxml", "Report Management");

        });

        AirportManagerManagementBTN.setOnAction(event -> {
            loginMenuController.ChangeWindow(AirportManagerManagementBTN , "/sample/view/superadmin/Manager/Panel.fxml", "Airport Manager Management");

        });


        FinancialUnitManagementSuperBTN.setOnAction(event -> {
            loginMenuController.ChangeWindow(FinancialUnitManagementSuperBTN , "/sample/view/superadmin/Financial/Panel.fxml", "Financial unit Management");

        });

    }

    public void BackToSuperAdminMenu(Node node){
        node.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/view/superadmin/Menu.fxml"));
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
