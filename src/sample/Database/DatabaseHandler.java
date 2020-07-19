package sample.Database;

import sample.model.*;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseHandler extends Config {
    Connection connection;
   public PreparedStatement preparedStatement;

    public Connection getConnection() throws ClassNotFoundException , SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");

        String connectionString = "jdbc:mysql://" + dbHost +
                ":" + dbPort + "/" + dbName;
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/airport","root",dbPass);
        return connection;
    }

    //Read :

    public ArrayList<Manager> ReadManagers() throws SQLException {
        ArrayList<Manager> managers = new ArrayList<>();

        String query = "SELECT * from manager";

        preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            Manager manager = new Manager();
            manager.setIdmanager(resultSet.getInt(1));
            manager.setId(resultSet.getInt(2));
            manager.setName(resultSet.getString(3));
            manager.setLastName(resultSet.getString(4));
            manager.setUserName(resultSet.getString(5));
            manager.setPassword(resultSet.getString(6));
            manager.setPhoneNumber(resultSet.getString(7));
            manager.setAddress(resultSet.getString(8));
            manager.setEmail(resultSet.getString(9));
            manager.setSalary(resultSet.getDouble(10));
            manager.setIsSuperAdmin(resultSet.getInt(11));

            managers.add(manager);

        }
        return managers;
    }

    public ArrayList<Passenger> ReadPassengers() throws SQLException {

        ArrayList<Passenger> passengers = new ArrayList<>();

        String query = "SELECT * from passenger";

        preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
        Passenger passenger =  new Passenger();
        passenger.setIdPassengers(resultSet.getInt(1));
        passenger.setId(resultSet.getInt(2));
        passenger.setName(resultSet.getString(3));
        passenger.setLastName(resultSet.getString(4));
        passenger.setUserName(resultSet.getString(5));
        passenger.setPassword(resultSet.getString(6));
        passenger.setPhoneNumber(resultSet.getString(7));
        passenger.setCredit(resultSet.getDouble(8));
        passenger.setEmail(resultSet.getString(9));
            passengers.add(passenger);
        }
        return passengers;
    }

    public ArrayList<Employee> ReadEmployee() throws SQLException {
        ArrayList<Employee> employees = new ArrayList<>();
        String query = "SELECT * from employee";

        preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Employee employee =  new Employee();

            employee.setIdEmployee(resultSet.getInt(1));
            employee.setId(resultSet.getInt(2));
            employee.setName(resultSet.getString(3));
            employee.setLastName(resultSet.getString(4));
            employee.setUserName(resultSet.getString(5));
            employee.setPassword(resultSet.getString(6));
            employee.setPhoneNumber(resultSet.getString(7));
            employee.setAddress(resultSet.getString(8));
            employee.setEmail(resultSet.getString(9));
            employee.setSalary(resultSet.getDouble(10));


            employees.add(employee);
        }
        return  employees;
        }

    public ArrayList<Flight> ReadFlights() throws SQLException{

        ArrayList<Flight> flights = new ArrayList<>();
        String query = "SELECT * from flight";

        preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Flight flight = new Flight();

            flight.setId(resultSet.getInt(2));
            flight.setAirplaneId(resultSet.getInt(3));
            flight.setTicketId(resultSet.getInt(4));
            flight.setOrigin(resultSet.getString(5));
            flight.setDestination(resultSet.getString(6));
            flight.setFlightsDate(resultSet.getString(7));
            flight.setFlightsTime(resultSet.getString(8));
            flight.setSold_Tickets_Number(resultSet.getInt(9));
            flight.setFlightDuration(resultSet.getDouble(10));

            if (resultSet.getInt(11) == 0) {
                flight.setFlightStatus(Flight.FlightStatus.flying);
            }

            else if (resultSet.getInt(11) == 1) {
                flight.setFlightStatus(Flight.FlightStatus.done);
            }

            else if (resultSet.getInt(11) == 2){
                flight.setFlightStatus(Flight.FlightStatus.undone);
            }


            for (int i= 0 ; i < passengerList(resultSet.getInt(2)).size() ; i++){
                flight.AddPassengersList(passengerList(resultSet.getInt(2)).get(i));
            }

            }

        return flights;
        }

    public ArrayList<Airplane> ReadPlanes() throws SQLException{

        ArrayList<Airplane> planes = new ArrayList<>();
        String query = "SELECT * from plane";

        preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Airplane plane = new Airplane();
            plane.setIdplane(resultSet.getInt(1));
            plane.setId(resultSet.getInt(2));
            plane.setNumber_of_seats(resultSet.getInt(3));

            // add the list :
            for(int i = 0 ; i < flightList(resultSet.getInt(2)).size() ; i++ ){
                plane.addFlightLists(flightList(resultSet.getInt(2)).get(i));
            }

            planes.add(plane);
        }
            return planes;
    }


    //manager :

    public  void updateManagers(String firstname, String lastname ,String username ,String phone, String address , String Email , int id) throws SQLException {

            String query = "UPDATE manager SET name = ? , lastname =? , username = ? , phonenumber = ? , address = ? , email = ?"
                    + "where idmanager = ?";

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, firstname);
                preparedStatement.setString(2, lastname );
                preparedStatement.setString(3, username );
                preparedStatement.setString(4, phone    );
                preparedStatement.setString(5, address  );
                preparedStatement.setString(6, Email    );
                preparedStatement.setInt   (7, id       );
                preparedStatement.executeUpdate();
                preparedStatement.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    public void UpdateManagerPassword(String password , int id){

            String query = "UPDATE manager SET password = ?"
                    + "where idmanager = ?";

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, password);
                preparedStatement.setInt   (2, id       );
                preparedStatement.executeUpdate();
                preparedStatement.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }


    //employee :

    public void AddEmployee(String firstname , String lastname , String username , String Phone , String Address , double Salary , String Email , String Password ) throws SQLException {
                int id = ReadEmployee().size() + 20000;
            String insert = "INSERT INTO employee(id,name,lastname,username,password,phonenumber,address,email,salary)"+"VALUES(?,?,?,?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setInt(1    , id);
            preparedStatement.setString(2 , firstname);
            preparedStatement.setString(3 , lastname);
            preparedStatement.setString(4 , username);
            preparedStatement.setString(5 , Password);
            preparedStatement.setString(6 , Phone);
            preparedStatement.setString(7 , Address);
            preparedStatement.setString(8 , Email);
            preparedStatement.setDouble(9 , Salary);

            preparedStatement.executeUpdate();
        }

    public void DeleteEmployee(int id){
        String query = "DELETE FROM employee where idemployee  = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void UpdateEmployeePassword(String password , int id){

        String query = "UPDATE employee SET password = ?"
                + "where idemployee = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, password);
            preparedStatement.setInt   (2, id       );
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateEmployee(String firstname , String lastname , String username , String Phone , String Address , double Salary , String Email , int id)throws SQLException{

        String query = "UPDATE employee SET name = ? , lastname = ? , username = ? , phonenumber = ? , address = ? , email = ? , salary = ?"
                + "where idemployee = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, firstname);
            preparedStatement.setString(2, lastname );
            preparedStatement.setString(3, username );
            preparedStatement.setString(4, Phone    );
            preparedStatement.setString(5, Address  );
            preparedStatement.setString(6, Email    );
            preparedStatement.setDouble(7 , Salary  );
            preparedStatement.setInt   (8, id       );
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    //plane :
    public void AddPlane(int capacity) throws SQLException {
        int id = ReadEmployee().size() + 50000;

        String insert = "INSERT INTO plane(id , number_of_seats)"+"VALUES(?,?)";
        preparedStatement = connection.prepareStatement(insert);
        preparedStatement.setInt(1    , id);
        preparedStatement.setInt(2    , capacity);

        preparedStatement.executeUpdate();

    }

    public void UpdatePlane(int capacity , int id){

        String query = "UPDATE plane SET number_of_seats = ? "
                + "where idplane = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, capacity);
            preparedStatement.setInt(2, id );
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void DeletePlane(int id){

        String query = "DELETE FROM plane where idplane  = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //flight :
    public void AddFlight(int planeId , int ticketId , String origin , String destination , String flightsDate , String flightsTime , int sold_ticket_number , Double flightDuration , Flight.FlightStatus flightStatus) throws SQLException {
        int id = ReadFlights().size() + 50000;
        String insert = "INSERT INTO flight(id , airplaneId , ticketId , origin  , destination , flightsDate , flightsTime , flightsTime , flightDuration , flightStatus)"+"VALUES(?,?,?,?,?,?,?,?,?,?)";
        preparedStatement = connection.prepareStatement(insert);
        preparedStatement.setInt(1 , id);
        preparedStatement.setInt(2 , planeId);
        preparedStatement.setInt(3 , ticketId);
        preparedStatement.setString(4 , origin);
        preparedStatement.setString(5 , destination);
        preparedStatement.setString(6 , flightsDate);
        preparedStatement.setString(7 , flightsTime);
        preparedStatement.setInt(8, sold_ticket_number);
        preparedStatement.setDouble(9 , flightDuration);
        if(flightStatus.equals(Flight.FlightStatus.flying)){
            preparedStatement.setInt(10 , 0);
        }
        else if(flightStatus.equals(Flight.FlightStatus.done)){
            preparedStatement.setInt(10 , 1);
        }
        else if(flightStatus.equals(Flight.FlightStatus.undone)){
            preparedStatement.setInt(10 , 2);
        }

        preparedStatement.executeUpdate();


    }

    public void UpdateFlight(int planeId , int ticketId , String origin , String destination , String flightsDate , String flightsTime , int sold_ticket_number , Double flightDuration , Flight.FlightStatus flightStatus , int id){

        String query = "UPDATE flight SET airplaneId = ? , ticketId = ? , origin = ? , destination = ? , flightsDate = ? ,  flightsTime = ? , sold_ticket_number = ? , flightDuration = ? , flightStatus = ? "
                + "where idflight = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, planeId);
            preparedStatement.setInt(2, ticketId);
            preparedStatement.setString(3 , origin);
            preparedStatement.setString(4 , destination);
            preparedStatement.setString(5 , flightsDate);
            preparedStatement.setString(6 , flightsTime);
            preparedStatement.setInt(7 , sold_ticket_number);
            preparedStatement.setDouble(8 , flightDuration);

            if(flightStatus.equals(Flight.FlightStatus.flying)){
                preparedStatement.setInt(9 , 0);
            }

            else if(flightStatus.equals(Flight.FlightStatus.done)){
                preparedStatement.setInt(9 , 1);
            }
            else if(flightStatus.equals(Flight.FlightStatus.undone)){
                preparedStatement.setInt(9 , 2);
            }


            preparedStatement.setInt(10 , id);
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void DeleteFlight(int id) {

        String query = "DELETE FROM flight where idflight  = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // other methods :

    public boolean CheckUsername(String username) throws SQLException {
        boolean flag = true;

        for(int i = 0 ; i < ReadEmployee().size() ; i++){
            if(ReadEmployee().get(i).getUserName().equalsIgnoreCase(username)){
                flag = false;
            }
        }

        for(int i = 0 ; i < ReadManagers().size() ; i++){
            if(ReadManagers().get(i).getUserName().equalsIgnoreCase(username)){
                flag = false;
            }
        }

        for(int i = 0 ; i < ReadPassengers().size() ; i++){
            if(ReadPassengers().get(i).getUserName().equalsIgnoreCase(username)){
                flag = false;
            }
        }


        return  flag;
    }

    public ArrayList<Flight> flightList (int planeId) throws SQLException {
        ArrayList<Flight> flights = new ArrayList<>();

        for(int i= 0 ; i < ReadFlights().size() ; i++){
            if(ReadFlights().get(i).getAirplaneId()== planeId){
                flights.add(ReadFlights().get(i));
            }
        }

        return flights;
    }

    public ArrayList<Passenger> passengerList (int flightId) throws SQLException{
        ArrayList<Passenger> passengers = new ArrayList<>();

        for(int i = 0 ; i < ReadPassengers().size() ; i++){
            if(ReadPassengers().get(i).getFlightid() == flightId){
                passengers.add(ReadPassengers().get(i));
            }
        }
        return passengers;
    }

}
