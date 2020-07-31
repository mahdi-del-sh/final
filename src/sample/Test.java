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


        databaseHandler.addManager("mahdi" , "wdwed" , "e332e" , "23e3" , "32r2r" , "23rr23" , "ewfewf" , 2000.0);

    }


}
