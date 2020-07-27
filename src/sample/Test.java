package sample;

import sample.Database.DatabaseHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection ;
        connection = databaseHandler.getConnection();

databaseHandler.UpdatePlane(2 , 00);

    }
}
