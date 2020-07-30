package sample.model;


import javafx.scene.control.TextArea;
import sample.model.Person;
import sample.model.ShowAble;

public class Employee extends Person implements ShowAble {

    private int idEmployee;
    private int id;
    private String name;
    private String lastName;
    private String userName;
    private String password;
    private String phoneNumber;
    private String Address;
    private String Email;
    private double salary;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void show(){}

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public  String getLastName() {
        return lastName;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getEmail() {
        return Email;
    }


    public void Show(Employee employee){
        System.out.println("id                           "+employee.getId());
        System.out.println("name and lastName            "+employee.getName()+" "+employee.getLastName());
        System.out.println("userName and password        "+employee.getUserName()+" "+employee.getPassword());
        System.out.println("Phone number                 "+employee.getPhoneNumber());
        System.out.println("Address                      "+employee.getAddress());
        System.out.println("Email                        "+employee.getEmail());
        System.out.println("Salary                       "+employee.getSalary());
    }

}
