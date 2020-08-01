package sample.controller.superadmin.Financial;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import com.sun.webkit.dom.CDATASectionImpl;
import javafx.fxml.FXML;
import org.w3c.dom.CDATASection;
import sample.Database.DatabaseHandler;
import sample.Test;
import sample.controller.superadmin.Menu;
import sample.model.Employee;
import sample.model.Manager;
import sample.model.Ticket;

public class Panel {


    @FXML
    private JFXButton FirstBTN;

    @FXML
    private JFXButton SecondBTN;

    @FXML
    private JFXButton FixedBTN;

    @FXML
    private JFXButton HomeBTN;




    @FXML
    void initialize() throws SQLException, ClassNotFoundException {


        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection =  databaseHandler.getConnection();

        HomeBTN.setOnAction(event -> {
            Menu superAdminMenuController =  new Menu();
            superAdminMenuController.BackToSuperAdminMenu(HomeBTN);
        });

        FixedBTN.setOnAction(event -> {


            try {
                for(Ticket ticket  : databaseHandler.ReadTicket()){
                databaseHandler.UpdateTicket(ticket.getId() , ticket.getTicketPrice() , (ticket.getPenalty()*4)/5);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }


//**********************************************************************************************************************

            try {
                for(int i : getPass().keySet()){

                    if(getPass().get(i) < 5){
                        databaseHandler.UpdatePassengerCredit(123.23 , 30001);
                        System.out.println("3");
                    }

                    else{

                        double credit = get3Credit(30001);
                        int id = i ;
                      databaseHandler.UpdatePassengerCredit(1233545 , id);
                        System.out.println("5");
                        System.out.println(credit);
//                        System.out.println(get3Credit(i));
//                        System.out.println(i);
                    }

                }

            }

            catch (SQLException e) {
                System.out.println("no");
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                System.out.println("no");
                e.printStackTrace();
            }





        });

        SecondBTN.setOnAction(event -> {

            try {
                decreaseTickets();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            try {
                decreaseSalaryPrice();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            try {
                IncreaseManagerSalary();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        });

        FirstBTN.setOnAction(event -> {

            try {
                IncreaseTicketPrice();

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                IncreaseSalaryPrice();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

    }

    private void decreaseTickets() throws SQLException, ClassNotFoundException {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection =  databaseHandler.getConnection();
        for(Ticket ticket : databaseHandler.ReadTicket()){
            databaseHandler.UpdateTicket(ticket.getId() , ((ticket.getTicketPrice()*10)/11) , ticket.getPenalty());
        }
    }

    private void decreaseSalaryPrice() throws SQLException, ClassNotFoundException {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection =  databaseHandler.getConnection();
        for(Employee employee : databaseHandler.ReadEmployee()){
            databaseHandler.UpdateEmployeeSalary((10 * employee.getSalary()/11) , employee.getId());        }
    }


    private void IncreaseSalaryPrice() throws SQLException, ClassNotFoundException {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection =  databaseHandler.getConnection();
        for(Employee employee : databaseHandler.ReadEmployee()){
databaseHandler.UpdateEmployeeSalary((6 * employee.getSalary()/5) , employee.getId());        }
    }

    private void IncreaseTicketPrice() throws SQLException, ClassNotFoundException {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection =  databaseHandler.getConnection();
        for(Ticket ticket : databaseHandler.ReadTicket()){
            databaseHandler.UpdateTicket(ticket.getId() , ((ticket.getTicketPrice()*6)/5) , ticket.getPenalty());
        }
    }

    private void IncreaseManagerSalary() throws SQLException, ClassNotFoundException {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection =  databaseHandler.getConnection();
        for(Manager manager : databaseHandler.ReadManagers()){
            databaseHandler.UpdateManagerSalary((manager.getSalary()* 11)/10 , manager.getId());
        }
    }


    public int findTekrar(int number , ArrayList<Integer> arrayList){
        int  j = 0 ;

        for(int i  = 0 ; i < arrayList.size() ; i++){
            if(arrayList.get(i) == number)
                j++;
        }

        return j;
    }

    public HashMap<Integer , Integer> getPass() throws SQLException, ClassNotFoundException {

        HashMap<Integer , Integer> items =  new HashMap<>();

        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection =  databaseHandler.getConnection();

        for(int i = 0 ; i < databaseHandler.PassengerRepeat().size() ; i++){

            if(!items.containsKey(databaseHandler.PassengerRepeat().get(i))){

                if(findTekrar(databaseHandler.PassengerRepeat().get(i) , databaseHandler.PassengerRepeat()) >= 3){

                    items.put(databaseHandler.PassengerRepeat().get(i) , findTekrar(databaseHandler.PassengerRepeat().get(i) , databaseHandler.PassengerRepeat()));
                }
            }
        }




        return items;
    }

    public double get5Credit(int id) throws SQLException, ClassNotFoundException {
        double credit = 0;
        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection =  databaseHandler.getConnection();

        for(int i = 0 ; i < databaseHandler.ReadPassengers().size() ; i++){
            if(databaseHandler.ReadPassengers().get(i).getId() == id){
                credit = (6/5) * databaseHandler.ReadPassengers().get(i).getCredit();
            }
        }

        return credit;
    }

    public double get3Credit(int id) throws SQLException, ClassNotFoundException {
        double credit = 0;
        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection =  databaseHandler.getConnection();

        for(int i = 0 ; i < databaseHandler.ReadPassengers().size() ; i++){
            if(databaseHandler.ReadPassengers().get(i).getId() == id){
                credit = (11/10) * databaseHandler.ReadPassengers().get(i).getCredit();
            }
        }

        return credit;
    }



    }



