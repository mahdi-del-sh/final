package sample.model;

import sample.model.Person;
import sample.model.ShowAble;

public class Passenger extends Person implements ShowAble {


    private int id;
    private String name;
    private String lastName;
    private String userName;
    private String password;
    private String phoneNumber;
    private double credit;
    private String Email;

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

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public void show(){}

    public void Show(Passenger passenger){
        System.out.println("name:                   "+ passenger.getName()+" "+passenger.getLastName());
        System.out.println("userName and Password:  "+ passenger.getUserName()+" "+passenger.getPassword());
        System.out.println("Phone number:           "+ passenger.getPhoneNumber());
        System.out.println("Credit:                 "+ passenger.getCredit());
        System.out.println("Email:                  "+ passenger.getEmail());
    }


    @Override
    int getId() {
        return 0;
    }

    @Override
    String getName() {
        return name;
    }

    @Override
    String getLastName() {
        return lastName;
    }

    @Override
    String getUserName() {
        return userName;
    }

    @Override
    String getPassword() {
        return password;
    }

    @Override
    String getEmail() {
        return Email;
    }

}
