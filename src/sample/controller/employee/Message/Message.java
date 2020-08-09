package sample.controller.employee.Message;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;

import java.sql.Connection;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import sample.Database.DatabaseHandler;
import sample.controller.LoginMenuController;
import sample.controller.employee.Login;
import sample.controller.employee.Menu;
import sample.model.Employee;

public class Message {


    @FXML
    private JFXButton HomeBTN;

    @FXML
    private JFXButton SendBTN;

    @FXML
    private Label ErrorLBL;

    @FXML
    private JFXTextArea MessageTXA;

    LoginMenuController loginMenuController =  new LoginMenuController();


Menu menu = new Menu();
    @FXML
    void initialize() throws SQLException {

        setMessageTXA();



        HomeBTN.setOnAction(event -> {

            loginMenuController.ChangeWindow(HomeBTN , "/sample/view/employee/Menu.fxml", "");

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

                sample.controller.employee.Login login = new sample.controller.employee.Login();

                databaseHandler.UpdateEmployeeMessage(MessageTXA.getText() , login.PassengerId);
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

        Login login = new Login();


        for(Employee employee : databaseHandler.ReadEmployee()){
            if(login.PassengerId==employee.getId()){
                MessageTXA.setText(employee.getMessage());
            }
        }

    }
}
