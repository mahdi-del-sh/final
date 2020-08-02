package sample.controller.employee.Profile;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import sample.Database.DatabaseHandler;
import sample.controller.LoginMenuController;
import sample.controller.employee.Login;

public class Profile {


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
    private JFXButton ChangePasswordBTN;

    @FXML
    private JFXTextField EmailTXF;

    @FXML
    private Label EmailLBL;

    @FXML
    private Label LastnameLBL;

    @FXML
    private Label PhoneLBL;

    @FXML
    private Label FirstnameLBL;

    @FXML
    private Label UserNameLBL;

    @FXML
    private JFXTextField AddressTXF;

    @FXML
    private Label AddresssLBL;

    LoginMenuController loginMenuController = new LoginMenuController();

    static String Password;
    static String message;
    static double salary;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {

        ChangePasswordBTN.setOnAction(event -> {
            loginMenuController.ChangeWindow(ChangePasswordBTN , "/sample/view/employee/Profile/Password.fxml", "Change Password");


        });

            Login login = new Login();
            SetProfile(login.PassengerId);


        HomeBTN.setOnAction(event -> {
loginMenuController.ChangeWindow(HomeBTN , "/sample/view/employee/EmployeeMenu.fxml" , "Employee Management");
        });

        ConfirmBTN.setOnAction(event -> {
            EmailLBL.setText("");
            LastnameLBL.setText("");
            PhoneLBL.setText("");
            FirstnameLBL.setText("");
            UserNameLBL.setText("");
            AddresssLBL.setText("");

            try {
                if(CheckRegex()){
                    System.out.println("Yes");
                    //change data base :
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
                        databaseHandler.UpdateEmployee( FirstNameTXF.getText()  , LastnameTXF.getText() , UsernameTXF.getText() , PhoneTXF.getText() , AddressTXF.getText() , salary , EmailTXF.getText() , login.PassengerId);
                        AddresssLBL.setText("Changed successfully");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                else {
                    System.out.println("No");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        });



    }



    public void SetProfile(int id) throws SQLException, ClassNotFoundException {

        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection ;
        connection = databaseHandler.getConnection();

        for (int i = 0 ; i < databaseHandler.ReadEmployee().size() ; i++ ){
            if(databaseHandler.ReadEmployee().get(i).getId() == id){

                FirstNameTXF.setText(databaseHandler.ReadEmployee().get(i).getName());
                LastnameTXF.setText(databaseHandler.ReadEmployee().get(i).getLastName());
                UsernameTXF.setText(databaseHandler.ReadEmployee().get(i).getUserName());
                PhoneTXF.setText(databaseHandler.ReadEmployee().get(i).getPhoneNumber());
                EmailTXF.setText(databaseHandler.ReadEmployee().get(i).getEmail());
                AddressTXF.setText(databaseHandler.ReadEmployee().get(i).getAddress());
                message = databaseHandler.ReadEmployee().get(i).getMessage();
                salary = databaseHandler.ReadEmployee().get(i).getSalary();
                Password = databaseHandler.ReadEmployee().get(i).getPassword();


            }
        }

    }


    public boolean CheckRegex() throws SQLException {
        boolean flag = false;

        Pattern UsernamePattern = Pattern.compile("^(?=.{8,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$" , Pattern.CASE_INSENSITIVE);
        Pattern NamePattern = Pattern.compile("^[\\p{L} .'-]+$" , Pattern.CASE_INSENSITIVE);
        Pattern phoneNumber = Pattern.compile("^(\\+98|0)?9\\d{9}$");
        Pattern emailPattern = Pattern.compile("^(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])$");


        Matcher FirstNameMatcher = NamePattern.matcher(FirstNameTXF.getText());
        Matcher LastNameMatcher = NamePattern.matcher(LastnameTXF.getText());
        Matcher UsernameMatcher = UsernamePattern.matcher(UsernameTXF.getText());
        Matcher phoneMatcher = phoneNumber.matcher(PhoneTXF.getText());
        Matcher emailMatcher = emailPattern.matcher(EmailTXF.getText());
        Matcher AddressMatcher = NamePattern.matcher(AddressTXF.getText());

        boolean FirstNameFound = FirstNameMatcher.find();
        boolean LastNameFound = LastNameMatcher.find();
        boolean UsernameFound = UsernameMatcher.find();
        boolean PhoneFound = phoneMatcher.find();
        boolean EmailFound = emailMatcher.find();
        boolean AddressFound = AddressMatcher.find();

        if(!FirstNameFound){
            FirstnameLBL.setText("Enter a valid name");
        }

        if(!AddressFound){
            AddresssLBL.setText("Enter a valid Address");
        }

        if(!LastNameFound){
            LastnameLBL.setText("Enter a valid Last Name");
        }

        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection ;
        try {
            connection = databaseHandler.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
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

        if(FirstNameFound && LastNameFound && UsernameFound && PhoneFound && EmailFound && AddressFound ){
            flag = true;
        }

        if(databaseHandler.CheckUsername(UsernameTXF.getText())){
            UserNameLBL.setText("Enter Valid username");
        }




        return flag;
    }



}
