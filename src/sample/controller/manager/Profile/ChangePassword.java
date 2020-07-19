package sample.controller.manager.Profile;

import com.jfoenix.controls.JFXButton;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import sample.Database.DatabaseHandler;
import sample.animations.Shaker;
import sample.controller.LoginMenuController;
import sample.controller.manager.login;

public class ChangePassword {


    @FXML
    private JFXButton HomeBTN;

    @FXML
    private Label ErrorLBL;

    @FXML
    private JFXButton ConfirmBTN;

    @FXML
    private JFXTextField PasswordTXF;

    @FXML
    private JFXTextField ConfirmPasswordTXF;

    LoginMenuController loginMenuController = new LoginMenuController();

    @FXML
    void initialize() {
        PasswordTXF.setUnFocusColor(Paint.valueOf("#4059a9"));
        ConfirmPasswordTXF.setUnFocusColor(Paint.valueOf("#4059a9"));
        ErrorLBL.setText("");


        ConfirmBTN.setOnAction(event -> {

            if(PasswordTXF.getText().equalsIgnoreCase("") && ConfirmPasswordTXF.getText().equalsIgnoreCase("")){

                ErrorLBL.setText("Please Enter the password and confirm it");

                Shaker shaker1 = new Shaker(PasswordTXF);
                shaker1.shake();

                Shaker shaker2 = new Shaker(ConfirmPasswordTXF);
                shaker2.shake();

                PasswordTXF.setUnFocusColor(Paint.valueOf("#d50000"));
                ConfirmPasswordTXF.setUnFocusColor(Paint.valueOf("#d50000"));

            }
            else if(!PasswordTXF.getText().equalsIgnoreCase("") && ConfirmPasswordTXF.getText().equalsIgnoreCase("")){
                ErrorLBL.setText("please Confirm the password");

                Shaker shaker2 = new Shaker(ConfirmPasswordTXF);
                shaker2.shake();

                ConfirmPasswordTXF.setUnFocusColor(Paint.valueOf("#d50000"));

            }
            else if(PasswordTXF.getText().equalsIgnoreCase("") && !ConfirmPasswordTXF.getText().equalsIgnoreCase("")){
                ErrorLBL.setText("Please Enter the Password");

                Shaker shaker1 = new Shaker(PasswordTXF);
                shaker1.shake();

                PasswordTXF.setUnFocusColor(Paint.valueOf("#d50000"));

            }
            else{

                if(PasswordTXF.getText().equals(ConfirmPasswordTXF.getText())){

                    if(CheckPass(PasswordTXF.getText())){

                        DatabaseHandler databaseHandler = new DatabaseHandler();
                        Connection connection ;
                        try {
                            connection = databaseHandler.getConnection();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        login managerLogInController1 = new login();

                        databaseHandler.UpdateManagerPassword(PasswordTXF.getText() ,managerLogInController1.IdManager );

                        ErrorLBL.setText("Password Changed Successfully");

                    }

                }
                else{
                    Shaker shaker1 = new Shaker(PasswordTXF);
                    Shaker shaker2 = new Shaker(ConfirmPasswordTXF);

                    shaker1.shake();
                    shaker2.shake();

                    ErrorLBL.setText("Password Dont Match");

                    PasswordTXF.setUnFocusColor(Paint.valueOf("#d50000"));
                    ConfirmPasswordTXF.setUnFocusColor(Paint.valueOf("#d50000"));

                }

            }


        });

        HomeBTN.setOnAction(event -> {
            loginMenuController.ChangeWindow(HomeBTN , "/sample/view/manager/Profile/ChnageProfile.fxml", "Profile Management");
        });


    }
    public boolean CheckPass(String password){
        boolean flag = false;
        Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");

        Matcher matcher = pattern.matcher(password);

        boolean found = matcher.find();

        if(!found){
            ErrorLBL.setText("Please Enter a valid Password ");
        }
        else {
            flag = true;
        }

return flag;
    }

}
