package sample.model;

import sample.model.Person;
import sample.model.ShowAble;

public class Manager extends Person implements ShowAble {

private boolean isSuperAdmin = false;
private int id;
private String name;
private String lastName;
private String userName;
private String password;
private String phoneNumber;
private String Address;
private String Email;
private double salary;

    public boolean isSuperAdmin() {
        return isSuperAdmin;
    }

    public void setSuperAdmin(boolean superAdmin) {
        isSuperAdmin = superAdmin;
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
        this.Email = email;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void show(){

    }

    public void Show(Manager manager){

        System.out.println("name:                   "+ manager.getName()+" "+manager.getLastName());
        System.out.println("userName and Password:  "+ manager.getUserName()+" "+manager.getPassword());
        System.out.println("Phone number:           "+ manager.getPhoneNumber());
        System.out.println("Address:                "+ manager.getAddress());
        System.out.println("Salary:                 "+ manager.getSalary());
        System.out.println("Id:                     "+ manager.getId());
        System.out.println("Email:                  "+ manager.getEmail());

    }

    @Override
    int getId() {

        return id;

    }

    @Override
    String getName() { return name;}

    @Override
    String getLastName() {return lastName;}

    @Override
    String getUserName() { return userName;}

    @Override
    String getPassword() {return password;}

    @Override
    String getEmail()  { return Email ;}

}
