package sample.controller.passenger;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import sample.Database.DatabaseHandler;
import sample.controller.LoginMenuController;

import java.sql.Connection;
import java.sql.SQLException;

public class Register {


    @FXML
    private JFXButton HomeBTN;

    @FXML
    private JFXButton RegisterBTN;

    @FXML
    private JFXTextField FirstNameTXF;

    @FXML
    private JFXTextField LastNameTXF;

    @FXML
    private JFXTextField UserNameTXF;

    @FXML
    private JFXTextField PhoneTXF;

    @FXML
    private JFXTextField CreditTXF;

    @FXML
    private JFXTextField EmailTXF;

    @FXML
    private JFXTextField PasswordTXF;

    @FXML
    private JFXButton QuestionBTN;

    @FXML
    private Label FirstNameLBL;

    @FXML
    private Label PhoneLBL;

    @FXML
    private Label LastNameLBL;

    @FXML
    private Label CreditLBL;

    @FXML
    private Label UserNameLBL;

    @FXML
    private Label PasswordLBL;

    @FXML
    private Label EmailLBL;



    @FXML
    void initialize() {
        FirstNameLBL.setText("");
        LastNameLBL.setText("");
        PhoneLBL.setText("");
        CreditLBL.setText("");
        UserNameLBL.setText("");
        PasswordLBL.setText("");
        EmailLBL.setText("");

        HomeBTN.setOnAction(event -> {
            LoginMenuController loginMenuController =  new LoginMenuController();
            loginMenuController.ChangeWindow(HomeBTN , "/sample/view/passenger/Login.fxml", "Passenger Login");
        });

        RegisterBTN.setOnAction(event -> {

            FirstNameLBL.setText("");
            LastNameLBL.setText("");
            PhoneLBL.setText("");
            CreditLBL.setText("");
            UserNameLBL.setText("");
            PasswordLBL.setText("");
            EmailLBL.setText("");


            boolean flags [] = new boolean[7];


            if(FirstNameTXF.getText().equalsIgnoreCase("")){
                FirstNameLBL.setText("Please Enter FirstName");
                flags[0] = false;
            }
            else {
                flags[0] = true;
            }

            if(PhoneTXF.getText().equalsIgnoreCase("")){
                PhoneLBL.setText("Please PhoneNumber");
                flags[1] = false;
            }
            else {
                flags[1] = true;
            }

            if(LastNameTXF.getText().equalsIgnoreCase("")){
                LastNameLBL.setText("Please Enter LastName");
                flags[2] = false;
            }
            else {
                flags[2] = true;
            }


            if(CreditTXF.getText().equalsIgnoreCase("")){
                CreditLBL.setText("Please Enter Credit");
                flags[3] = false;
            }
            else {
                flags[3] = true;
            }


            if(UserNameTXF.getText().equalsIgnoreCase("")){
                UserNameLBL.setText("Please UserName ");
                flags[4] = false;
            }
            else {
                flags[4] = true;
            }



            if(PasswordTXF.getText().equalsIgnoreCase("")){
                PasswordLBL.setText("Please Password");
                flags[5] = false;
            }
            else {
                flags[5] = true;
            }



            if(EmailTXF.getText().equalsIgnoreCase("")){
                EmailLBL.setText("Please Enter Email ");
                flags[6] = false;
            }
            else {
                flags[6] = true;
            }


            boolean flag = true;
            for(boolean i : flags){
                if(i== false){
                    flag = false;
                }
            }


            if(flag){

                try {
                    if(CheckRegex()){

                        System.out.println("Yes");

                        DatabaseHandler databaseHandler = new DatabaseHandler();
                        Connection connection ;
                        try {
                            connection = databaseHandler.getConnection();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        databaseHandler.AddPassenger(FirstNameTXF.getText() , LastNameTXF.getText() , UserNameTXF.getText() , PasswordTXF.getText() , PhoneTXF.getText() , Double.parseDouble(CreditTXF.getText().toString()) , EmailTXF.getText());

                    }

                    else{

                        System.out.println("Not valid");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            else{
                System.out.println("Not Fill");
            }
        });

    }

    public boolean CheckRegex() throws SQLException {
        boolean flag = true;

        boolean [] flags = new boolean[10];

        if(FirstNameTXF.getText().matches("^[\\p{L} .'-]+$")){
            flags[0] = true;
        }
        else{
            FirstNameTXF.setText("Enter Valid Name");
            flags[0] = false;
            flag = false;
        }


        if(LastNameTXF.getText().matches("^[\\p{L} .'-]+$")){
            flags[1] = true;
        }
        else{
            LastNameLBL.setText("Enter Valid LastName");
            flags[1] = false;
            flag = false;
        }

        if(PhoneTXF.getText().matches("^(\\+98|0)?9\\d{9}$")){
            flags[2] = true;
        }
        else{
            PhoneLBL.setText("Enter PhoneNumber ");
            flags[2] = false;
            flag = false;
        }


        if(CreditTXF.getText().matches("(\\d+\\.\\d+)")){
            flags[3] = true;
        }
        else{
            CreditLBL.setText("Enter Valid Credit");
            flags[3] = false;
            flag = false;
        }



        if(UserNameTXF.getText().matches("^(?=.{8,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$") ){
            flags[4] = true;
        }
        else{
            UserNameLBL.setText("Enter Valid UserName");
            flags[4] = false;
            flag = false;
        }




        if(EmailTXF.getText().matches("^(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])$") ){
            flags[5] = true;
        }
        else{
            EmailLBL.setText("Enter Valid Email");
            flags[5] = false;
            flag = false;
        }


        if(PasswordTXF.getText().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")){
            flags[6] = true;
        }
        else{
            PasswordLBL.setText("Enter Valid Password");
            flags[6] = false;
            flag = false;
        }






        return flag;
    }
}
