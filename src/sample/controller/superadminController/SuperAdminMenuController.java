package sample.controller.superadminController;

import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SuperAdminMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton PersonalProfileManagementSuperAdminPanelBTN;

    @FXML
    private JFXButton AirportManagerManagementSuperAdminPanelBTN;

    @FXML
    private JFXButton ReportManagementSuperAdminPanelBTN;

    @FXML
    private JFXButton FinancialUnitManagementSuperAdminPanelBTN;

    @FXML
    private JFXButton HomeSuperAdminPanelBTN;

    @FXML
    void initialize() {

        HomeSuperAdminPanelBTN.setOnAction(event -> {
            HomeSuperAdminPanelBTN.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/passenger/PassengerMenu.fxml"));
            try{
                loader.load();
            }catch (IOException e){
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setTitle("Passenger Menu");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });


    }
}

