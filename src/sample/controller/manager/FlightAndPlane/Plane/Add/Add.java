package sample.controller.manager.FlightAndPlane.Plane.Add;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import sample.Database.DatabaseHandler;
import sample.animations.Shaker;
import sample.controller.LoginMenuController;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Add {

    @FXML
    private JFXButton HomeBTN;

    @FXML
    private JFXTextField CapacityTXF;

    @FXML
    private Label ErrorLBL;

    @FXML
    private JFXButton AddBTN;

    DatabaseHandler databaseHandler = new DatabaseHandler();
    Connection connection = databaseHandler.getConnection();

    LoginMenuController loginMenuController = new LoginMenuController();

    public Add() throws SQLException, ClassNotFoundException {
    }

    @FXML
    void initialize() {

        HomeBTN.setOnAction(event -> {
            loginMenuController.ChangeWindow(HomeBTN , "/sample/view/manager/FlightAndPlane/Panel.fxml" , "Flight and Plane Management");
        });

        AddBTN.setOnAction(event -> {
            CapacityTXF.setUnFocusColor(Paint.valueOf("#4059a9"));

            if(!CapacityTXF.getText().equalsIgnoreCase("")) {

                if (CheckCapacity()) {



                    try {
                        databaseHandler.AddPlane(Integer.parseInt(CapacityTXF.getText()));
                    } catch (SQLException e) {
                        System.out.println("Enter a number");
                        ErrorLBL.setText("Enter a number");
                    }

                    ErrorLBL.setText("Plane Added successfully");

                }
            }

            else {
                ErrorLBL.setText("Enter the Capacity");
                Shaker shaker1 = new Shaker(CapacityTXF);
                shaker1.shake();
                CapacityTXF.setUnFocusColor(Paint.valueOf("#d50000"));
            }
        });

    }

    public boolean CheckCapacity(){
        boolean flag = false;
        Pattern NamePattern = Pattern.compile( "^(?<=\\s|^)\\d+(?=\\s|$)$" , Pattern.CASE_INSENSITIVE);
        Matcher FirstNameMatcher = NamePattern.matcher(CapacityTXF.getText());
        boolean FirstNameFound = FirstNameMatcher.find();

        if(!FirstNameFound){
            ErrorLBL.setText("You Should Enter Just Number");
            Shaker shaker1 = new Shaker(CapacityTXF);
            shaker1.shake();
            CapacityTXF.setUnFocusColor(Paint.valueOf("#d50000"));
        }

        if(Integer.parseInt(CapacityTXF.getText()) > 0 && Integer.parseInt(CapacityTXF.getText()) < 400){
            flag = true ;
        }
        else {
            ErrorLBL.setText("The Capacity Should Between 0 to 400");
            Shaker shaker1 = new Shaker(CapacityTXF);
            shaker1.shake();
            CapacityTXF.setUnFocusColor(Paint.valueOf("#d50000"));
        }

        if(flag && FirstNameFound){
            flag = true;
        }
        else {
            flag = false;
        }
        return flag;
    }

}
