package sample.controller.managerController.managerEmployeeManagementController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import sample.Database.DatabaseHandler;
import sample.controller.LoginMenuController;
import sample.controller.managerController.ManagerMenuController;

public class EngageEmployeeController {


    @FXML
    private JFXButton HomeBTN;

    @FXML
    private JFXTextField FirstNameTXF;

    @FXML
    private JFXButton ConfirmBTN;

    @FXML
    private JFXTextField LastnameTXF;

    @FXML
    private JFXTextField UsernameTXF;

    @FXML
    private JFXTextField PhoneTXF;

    @FXML
    private JFXTextField AddressTXF;

    @FXML
    private JFXTextField PasswordTXF;

    @FXML
    private Label PasswordLBL;

    @FXML
    private JFXTextField EmailTXF;

    @FXML
    private Label EmailLBL;

    @FXML
    private Label AddressLBL;

    @FXML
    private Label LastnameLBL;

    @FXML
    private Label PhoneLBL;

    @FXML
    private Label FirstnameLBL;

    @FXML
    private Label UserNameLBL;

    @FXML
    private JFXTextField SalaryTXF;

    @FXML
    private Label SalaryLBL;

    LoginMenuController loginMenuController = new LoginMenuController();

    @FXML
    void initialize() {

        HomeBTN.setOnAction(event -> {
            loginMenuController.ChangeWindow(HomeBTN , "/sample/view/manager/managerPanel/managerEmployeeManagement/ManagerEmployeeManagement.fxml" , "Manager Panel");
        });


        ConfirmBTN.setOnAction(event -> {

            EmailLBL.setText("");
            AddressLBL.setText("");
            LastnameLBL.setText("");
            PhoneLBL.setText("");
            FirstnameLBL.setText("");
            UserNameLBL.setText("");
            SalaryLBL.setText("");
            PasswordLBL.setText("");

            boolean flags [] = new boolean[8];

            if(FirstNameTXF.getText().equalsIgnoreCase("")){
                FirstnameLBL.setText("Please Enter first name");
                flags[0] = false;
            }
            else {
                flags[0] = true;
            }

            if(LastnameTXF.getText().equalsIgnoreCase("")){
                LastnameLBL.setText("Please Enter last name");
                flags[1] = false;
            }
            else {
                flags[1] = true;
            }

            if(UsernameTXF.getText().equalsIgnoreCase("")){
                UserNameLBL.setText("Please Enter Username");
                flags[2] = false;
            }
            else {
                flags[2] = true;
            }

            if(PhoneTXF.getText().equalsIgnoreCase("")) {
                PhoneLBL.setText("Please Enter Phone Number");
                flags[3] = false;
            }
            else {
                flags[3] = true;
            }

            if(AddressTXF.getText().equalsIgnoreCase("")){
                AddressLBL.setText("Please Enter Address");
                flags[4] = false;
            }
            else {
                flags[4] = true;
            }

            if(SalaryTXF.getText().equalsIgnoreCase("")){
                SalaryLBL.setText("Please Enter Salary");
                flags[5] = false;
            }
            else {
                flags[5] = true;
            }

            if(EmailTXF.getText().equalsIgnoreCase("")){
                EmailLBL.setText("Please Enter Email");
                flags[6] = false;
            }
            else {
                flags[6] = true;
            }

            if(PasswordTXF.getText().equalsIgnoreCase("")){
                PasswordLBL.setText("Please Enter Password");
                flags[7] = false;
            }
            else {
                flags[7] = true;
            }

            boolean flag = true;
            for(boolean i : flags){
                if(i== false){
                    flag = false;
                }
            }


            if(flag ) {

                if (CheckRegex()) {
                    DatabaseHandler databaseHandler = new DatabaseHandler();
                    Connection connection ;
                    try {
                        connection = databaseHandler.getConnection();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    try {
                        if(databaseHandler.CheckUsername(UsernameTXF.getText())){

                            databaseHandler.AddEmployee(FirstNameTXF.getText() , LastnameTXF.getText() , UsernameTXF.getText() , PhoneTXF.getText() , AddressTXF.getText() , Double.valueOf(SalaryTXF.getText()) , EmailTXF.getText() , PasswordTXF.getText());
                            
                        }
                        else{
                            UserNameLBL.setText("This Username is taken choose another one");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }
            }
    });



    }

        public boolean CheckRegex(){
            boolean flag = false;

            Pattern UsernamePattern = Pattern.compile("^(?=.{8,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$" , Pattern.CASE_INSENSITIVE);
            Pattern NamePattern = Pattern.compile("^[\\p{L} .'-]+$" , Pattern.CASE_INSENSITIVE);
            Pattern phoneNumber = Pattern.compile("^(\\+98|0)?9\\d{9}$");
            Pattern emailPattern = Pattern.compile("^(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])$");
            Pattern PasswordPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
            Pattern salaryPattern = Pattern.compile("^(\\d+\\.\\d+)$");

            Matcher FirstNameMatcher = NamePattern.matcher(FirstNameTXF.getText());
            Matcher LastNameMatcher = NamePattern.matcher(LastnameTXF.getText());
            Matcher UsernameMatcher = UsernamePattern.matcher(UsernameTXF.getText());
            Matcher phoneMatcher = phoneNumber.matcher(PhoneTXF.getText());
            Matcher emailMatcher = emailPattern.matcher(EmailTXF.getText());
            Matcher passMatcher = PasswordPattern.matcher(PasswordTXF.getText());
            Matcher salaryMatcher = salaryPattern.matcher(SalaryTXF.getText());

            boolean FirstNameFound = FirstNameMatcher.find();
            boolean LastNameFound = LastNameMatcher.find();
            boolean UsernameFound = UsernameMatcher.find();
            boolean PhoneFound = phoneMatcher.find();
            boolean EmailFound = emailMatcher.find();
            boolean PassFound = passMatcher.find();
            boolean salaryFound = salaryMatcher.find();

            if(!FirstNameFound){
                FirstnameLBL.setText("Enter a valid name");
            }

            if(!LastNameFound){
                LastnameLBL.setText("Enter a valid Last Name");
            }

            if(!UsernameFound){
                UserNameLBL.setText("Enter a valid UserName");
            }

            if(!PhoneFound){
                PhoneLBL.setText("Enter a valid phone number");
            }

            if(!EmailFound){
                EmailLBL.setText("Enter Valid Email");
            }

            if(!PassFound){
                PasswordLBL.setText("Enter Valid Password");
            }

            if(!salaryFound){
                SalaryLBL.setText("Enter Valid Salary");
            }

            if(FirstNameFound && LastNameFound && UsernameFound && PhoneFound && EmailFound && PassFound && salaryFound){
                flag = true;
            }


            return flag;
        }

    }






