package sample.model;

public class Manager extends Person implements ShowAble {

private int idmanager;

private int id;
private String name;
private String lastName;
private String userName;
private String password;
private String phoneNumber;
private String Address;
private String Email;
private double salary;

private int isSuperAdmin = 0;

    public int getIdmanager() {
        return idmanager;
    }

    public void setIdmanager(int idmanager) {
        this.idmanager = idmanager;
    }

    public int getIsSuperAdmin() {
        return isSuperAdmin;
    }

    public void setIsSuperAdmin(int isSuperAdmin) {
        this.isSuperAdmin = isSuperAdmin;
    }

    public int isSuperAdmin() {
        return isSuperAdmin;
    }

    public void setSuperAdmin(int superAdmin) {
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
    public int getId() {

        return id;

    }

    @Override
    public String getName() { return name;}

    @Override
    public String getLastName() {return lastName;}

    @Override
    public String getUserName() { return userName;}

    @Override
    public String getPassword() {return password;}

    @Override
    public String getEmail()  { return Email ;}

}
