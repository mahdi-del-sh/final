package sample.controller.passenger.Message;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import sample.Database.DatabaseHandler;
import sample.controller.passenger.Login;
import sample.controller.passenger.Menu;
import sample.model.Passenger;

public class PassengerMessageController {


    @FXML
    private JFXButton HomeBTN;

    @FXML
    private JFXButton SendBTN;

    @FXML
    private Label ErrorLBL;

    @FXML
    private JFXTextArea MessageTXA;

    Menu passengerMenuController =  new Menu();

    @FXML
    void initialize() throws SQLException {

        setMessageTXA();



        HomeBTN.setOnAction(event -> {
            passengerMenuController.BackToPassengerMenu(HomeBTN);
        });

        SendBTN.setOnAction(event -> {
            ErrorLBL.setText("");


            if(MessageTXA.getText().equalsIgnoreCase("")){

                ErrorLBL.setText("Enter some thing");
            }
            else {

                DatabaseHandler databaseHandler = new DatabaseHandler();
                Connection connection ;
                try {
                    connection = databaseHandler.getConnection();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                Login login = new Login();

                databaseHandler.UpdatePassengerMessage(MessageTXA.getText() , login.IdManager);
                ErrorLBL.setText("Messaged Send successfully !");

            }
        });
    }



    public void setMessageTXA() throws SQLException {

        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection ;
        try {
            connection = databaseHandler.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Login login =  new Login();

        for(Passenger passenger : databaseHandler.ReadPassengers()){
            if(login.IdManager==passenger.getId()){
                MessageTXA.setText(passenger.getMessage());
            }
        }

    }
}
