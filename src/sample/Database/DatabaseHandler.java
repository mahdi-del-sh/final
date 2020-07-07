package sample.Database;

import sample.model.Employee;
import sample.model.Manager;
import sample.model.Passenger;

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
    //managers and superAdmins methods :

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

    }
