package sample.controller.passenger.Valdity;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import sample.Database.DatabaseHandler;
import sample.controller.passenger.Login;
import sample.controller.passenger.Menu;
import sample.model.Passenger;

public class PassengerValidityController {



    @FXML
    private JFXButton HomeBTN;

    @FXML
    private JFXTextField CreditTXF;

    @FXML
    private JFXButton ConfirmBTN;

    @FXML
    private Label ErrorLBL;

    Menu passengerMenuController =  new Menu();

    @FXML
    void initialize() {
        ErrorLBL.setText("");

        HomeBTN.setOnAction(event -> {
            passengerMenuController.BackToPassengerMenu(HomeBTN);
        });

        ConfirmBTN.setOnAction(event -> {
            ErrorLBL.setText("");
            if(CreditTXF.getText().equalsIgnoreCase("")){
                ErrorLBL.setText("Enter Credit");
            }
            else{
                if(!CreditTXF.getText().matches("(\\d+\\.\\d+)")){
                    ErrorLBL.setText("Enter Valid Credit");
                }
                else{
                    DatabaseHandler databaseHandler = new DatabaseHandler();
                    Connection connection ;
                    try {
                        connection = databaseHandler.getConnection();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }


                    Login lo = new Login();


                    System.out.println(lo.IdManager);
                    ErrorLBL.setText("Salary increased !");

                    databaseHandler.UpdatePassengerCredit(Double.parseDouble(CreditTXF.getText()) , lo.IdManager);
                }

            }
        });

    }
}
