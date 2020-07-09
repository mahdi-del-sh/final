package sample.controller.passengerController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import sample.Database.DatabaseHandler;
import sample.animations.Shaker;
import sample.controller.LoginMenuController;

import javax.xml.stream.events.EntityReference;

public class PassengerLogInController {
    static boolean username ;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton HomeBTN;

    @FXML
    private JFXPasswordField PasswordTXF;

    @FXML
    private JFXTextField UserNameTXF;

    @FXML
    private JFXButton RegisterBTN;

    @FXML
    private JFXButton EnterBTN;


    @FXML
    private Label ErrorLBL;

    @FXML
    void initialize() {

        HomeBTN.setOnAction(event -> {

            LoginMenuController loginMenuController =  new LoginMenuController();
            loginMenuController.Home(HomeBTN);
        });

        EnterBTN.setOnAction(event -> {

            username = false;

            UserNameTXF.setUnFocusColor(Paint.valueOf("blue"));
            PasswordTXF.setUnFocusColor(Paint.valueOf("blue"));

            if(UserNameTXF.getText().equals("")&&PasswordTXF.getText().equals("")){
                ErrorLBL.setText("Please Enter username and password");
            }
            else if (UserNameTXF.getText().equals("") && !PasswordTXF.getText().equals("")){
                ErrorLBL.setText("Please Enter the username");
            }
            else if (!UserNameTXF.getText().equals("") && PasswordTXF.getText().equals("")){
                ErrorLBL.setText("Please Enter the Password");
            }


            else {

                try {
                    if(loginChecker(UserNameTXF.getText() , PasswordTXF.getText())){

                        EnterBTN.getScene().getWindow().hide();
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("/sample/view/passenger/PassengerMenu.fxml"));
                        try{
                            loader.load();
                        }catch (IOException e){
                            e.printStackTrace();
                        }

                        Parent root = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setTitle("Passenger Panel");
                        stage.setScene(new Scene(root));
                        stage.show();

                    }
                    //********************
                    //********************
                    //********************

                    else {
                        if(username){

                            Shaker shaker1 = new Shaker(PasswordTXF);
                            shaker1.shake();
                            PasswordTXF.setUnFocusColor(Paint.valueOf("#d50000"));
                            ErrorLBL.setText("Password is wrong");

                        }
                        else{


                            Shaker shaker = new Shaker(UserNameTXF);
                            shaker.shake();

                            Shaker shaker1 = new Shaker(PasswordTXF);
                            shaker1.shake();

                            UserNameTXF.setUnFocusColor(Paint.valueOf("#d50000"));
                            PasswordTXF.setUnFocusColor(Paint.valueOf("#d50000"));

                            ErrorLBL.setText("Username and Password is wrong");

                        }
                    }


                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }



            }

        });

        RegisterBTN.setOnAction(event -> {
            LoginMenuController loginMenuController =  new LoginMenuController();loginMenuController.ChangeWindow(RegisterBTN , "/sample/view/passenger/PassengerRegister.fxml" ,"Register Panel" );
        });

    }



    public boolean loginChecker(String userName , String Password) throws SQLException, ClassNotFoundException {
        boolean flag = false;
        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection ;
        connection = databaseHandler.getConnection();

        for(int i = 0 ; i < databaseHandler.ReadPassengers().size() ; i++){
            if(databaseHandler.ReadPassengers().get(i).getUserName().equals(userName) && databaseHandler.ReadPassengers().get(i).getPassword().equals(Password)){
                flag = true;
            }
        }

        if(!flag) {
            for (int i = 0; i < databaseHandler.ReadPassengers().size(); i++) {

                if(databaseHandler.ReadPassengers().get(i).getUserName().equals(userName)){
                    username = true;
                }

            }
        }

        return flag;

    }
}
