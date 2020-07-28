package sample;

import sample.Database.DatabaseHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection ;
        connection = databaseHandler.getConnection();


//        if("0.0".matches("(\\d+\\.\\d+)")){
//            System.out.println("Yes");
//        }
//        else {
//            System.out.println("No");
//        }




    }


}
