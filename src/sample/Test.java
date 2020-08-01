package sample;

import sample.Database.DatabaseHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Test {

   private int passengerId ;
   private int ticketId;
   private int flightId;

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection =  databaseHandler.getConnection();

        System.out.println(databaseHandler.ReadEmployee().get(0).getUserName());
        System.out.println(databaseHandler.ReadEmployee().get(2).getUserName());
        System.out.println(databaseHandler.ReadEmployee().get(1).getUserName());

      //  databaseHandler.AddPassenger("kamran" , "WEdewd" , "dwedwed" , "e32e" , "0195823486952" , 2536.25 , "rewfrewfwerf");

    }
}
