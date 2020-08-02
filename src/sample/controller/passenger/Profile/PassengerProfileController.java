package sample.controller.passenger.Profile;

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
import sample.controller.passenger.Login;
import sample.controller.passenger.Menu;

public class PassengerProfileController {


    @FXML
    private JFXButton HomeBTN;

    @FXML
    private JFXTextField FirstNameTXF;

    @FXML
    private JFXTextField LastnameTXF;

    @FXML
    private JFXTextField UsernameTXF;

    @FXML
    private JFXTextField PhoneTXF;

    @FXML
    private JFXButton ChangePasswordBTN;

    @FXML
    private JFXButton ConfirmBTN;

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

    Menu passengerMenuController =  new Menu();

    static String password ;
    static double credit ;
    static int flightId ;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {

        HomeBTN.setOnAction(event -> {
            passengerMenuController.BackToPassengerMenu(HomeBTN);
        });

        Login login = new Login();
        SetProfile(login.IdManager);

        ConfirmBTN.setOnAction(event -> {
            EmailLBL.setText("");
            LastnameLBL.setText("");
            PhoneLBL.setText("");
            FirstnameLBL.setText("");
            UserNameLBL.setText("");

            if (CheckRegex()){

                //change data base
                DatabaseHandler databaseHandler = new DatabaseHandler();
                Connection connection ;
                try {
                    connection = databaseHandler.getConnection();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                databaseHandler.UpdatePassenger(login.IdManager , FirstNameTXF.getText() , LastnameTXF.getText() , UsernameTXF.getText() , password  , PhoneTXF.getText()  , EmailTXF.getText() , credit  );
                EmailLBL.setText("Changed Successfully!");
            }

        });

        ChangePasswordBTN.setOnAction(event -> {
            LoginMenuController loginMenuController = new LoginMenuController();
            loginMenuController.ChangeWindow(ChangePasswordBTN , "/sample/view/passenger/Profile/ChangePassword.fxml", "Change Password");
        });

    }

    public void SetProfile(int id) throws SQLException, ClassNotFoundException {

        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection ;
        connection = databaseHandler.getConnection();

        for (int i = 0 ; i < databaseHandler.ReadPassengers().size() ; i++ ){
            if(databaseHandler.ReadPassengers().get(i).getId() == id){

                FirstNameTXF.setText(databaseHandler.ReadPassengers().get(i).getName());
                LastnameTXF.setText(databaseHandler.ReadPassengers().get(i).getLastName());
                UsernameTXF.setText(databaseHandler.ReadPassengers().get(i).getUserName());
                PhoneTXF.setText(databaseHandler.ReadPassengers().get(i).getPhoneNumber());
                EmailTXF.setText(databaseHandler.ReadPassengers().get(i).getEmail());
                password = databaseHandler.ReadPassengers().get(i).getPassword();
                credit = databaseHandler.ReadPassengers().get(i).getCredit();

            }
        }

    }


    public boolean CheckRegex(){
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

        boolean FirstNameFound = FirstNameMatcher.find();
        boolean LastNameFound = LastNameMatcher.find();
        boolean UsernameFound = UsernameMatcher.find();
        boolean PhoneFound = phoneMatcher.find();
        boolean EmailFound = emailMatcher.find();

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

        if(FirstNameFound && LastNameFound && UsernameFound && PhoneFound && EmailFound ){
            flag = true;
        }




        return flag;
    }

}
