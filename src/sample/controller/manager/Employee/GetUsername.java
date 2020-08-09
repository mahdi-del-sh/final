package sample.controller.manager.Employee;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import java.sql.Connection;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import sample.Database.DatabaseHandler;
import sample.animations.Shaker;
import sample.controller.LoginMenuController;

public class GetUsername {

    @FXML
    private JFXButton HomeBTN;

    @FXML
    private JFXTextField UserNameTXF;

    @FXML
    private Label ErrorLBL;

    @FXML
    private JFXButton NextBTN;
    static int id ;
    public static int EmployeeId ;
    LoginMenuController loginMenuController = new LoginMenuController();


    @FXML
    void initialize() {

        NextBTN.setOnAction(event -> {
            UserNameTXF.setUnFocusColor(Paint.valueOf("#4059a9"));

            if(UserNameTXF.getText().equalsIgnoreCase("")){
                ErrorLBL.setText("Please Enter the Username");
            }
            else {
                try {
                    if(CheckUserName()){

                        loginMenuController.ChangeWindow(NextBTN , "/sample/view/manager/Employee/Edit.fxml", "Edit Employee");
                    }
                    else {
                        ErrorLBL.setText("Username not found");
                        Shaker shaker1 = new Shaker(UserNameTXF);
                        shaker1.shake();
                        UserNameTXF.setUnFocusColor(Paint.valueOf("#d50000"));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        HomeBTN.setOnAction(event -> {
            loginMenuController.ChangeWindow(HomeBTN , "/sample/view/manager/Employee/Panel.fxml", "Employee management");

        });
    }

    private boolean CheckUserName() throws SQLException, ClassNotFoundException {
        boolean flag = false;

        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection ;
        connection = databaseHandler.getConnection();

        for(int i = 0 ; i < databaseHandler.ReadEmployee().size() ; i++){
            if(databaseHandler.ReadEmployee().get(i).getUserName().equals(UserNameTXF.getText())){
                flag = true;
                id = databaseHandler.ReadEmployee().get(i).getIdEmployee();
                EmployeeId = databaseHandler.ReadEmployee().get(i).getId() ;
            }
        }

        return flag;
    }
}
