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


        //Update :
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

}
