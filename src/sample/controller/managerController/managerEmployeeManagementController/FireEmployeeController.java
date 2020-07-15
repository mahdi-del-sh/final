package sample.controller.managerController.managerEmployeeManagementController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import sample.Database.DatabaseHandler;
import sample.animations.Shaker;
import sample.controller.LoginMenuController;
import sample.model.Employee;

public class FireEmployeeController {


    @FXML
    private JFXButton HomeBTN;

    @FXML
    private JFXTextField UserNameTXF;

    @FXML
    private Label ErrorLBL;

    @FXML
    private JFXButton FireBTN;

    LoginMenuController loginMenuController = new LoginMenuController();

    static int id;
    @FXML
    void initialize() {

       UserNameTXF.setUnFocusColor(Paint.valueOf("#4059a9"));

        HomeBTN.setOnAction(event -> {
            loginMenuController.ChangeWindow(HomeBTN , "/sample/view/manager/managerPanel/managerEmployeeManagement/ManagerEmployeeManagement.fxml" , "Employee management");
        });

        FireBTN.setOnAction(event -> {
            UserNameTXF.setUnFocusColor(Paint.valueOf("#4059a9"));

            if(UserNameTXF.getText().equalsIgnoreCase("")){
                ErrorLBL.setText("Enter the Username");
            }
            else{
                try {
                    if(CheckUserName(UserNameTXF.getText())){

                        DatabaseHandler databaseHandler = new DatabaseHandler();
                        Connection connection ;
                        connection = databaseHandler.getConnection();
                        databaseHandler.DeleteEmployee(id);
                        ErrorLBL.setText("Employee has been fired successfully");

                    }
                    else {
                        ErrorLBL.setText("Cant find the username");
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

    }

    private boolean CheckUserName(String text) throws SQLException, ClassNotFoundException {
        boolean flag = false ;

        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection ;
        connection = databaseHandler.getConnection();

        databaseHandler.ReadEmployee();

        for (Employee employee : databaseHandler.ReadEmployee()) {
            if(employee.getUserName().equals(text)){
               id =  employee.getIdEmployee();
                flag = true ;
            }
        }
        return flag;
    }


}