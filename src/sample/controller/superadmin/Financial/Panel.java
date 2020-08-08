package sample.controller.superadmin.Financial;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXCheckBox;
import com.sun.webkit.dom.CDATASectionImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.w3c.dom.CDATASection;
import sample.Database.DatabaseHandler;
import sample.Test;
import sample.controller.superadmin.Menu;
import sample.model.Employee;
import sample.model.Manager;
import sample.model.Ticket;

public class Panel {



    @FXML
    private JFXButton HomeBTN;

    @FXML
    private JFXButton ConfirmBTN;

    @FXML
    private JFXCheckBox First;

    @FXML
    private   JFXCheckBox Second;

    @FXML
    private Label label;



    DatabaseHandler databaseHandler = new DatabaseHandler();
    Connection connection =  databaseHandler.getConnection();

    public Panel() throws SQLException, ClassNotFoundException {
    }


    @FXML
    void initialize() throws SQLException, ClassNotFoundException {

        try {
            if(databaseHandler.ReadPolicy().equalsIgnoreCase("fixed")){
                First.setSelected(false);
                Second.setSelected(false);

            }

            else if(databaseHandler.ReadPolicy().equalsIgnoreCase("first")){

                First.setSelected(true);
                Second.setSelected(false);

            }
            else if(databaseHandler.ReadPolicy().equalsIgnoreCase("second")){

                First.setSelected(false);
                Second.setSelected(true);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }



        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection =  databaseHandler.getConnection();

        HomeBTN.setOnAction(event -> {
            Menu superAdminMenuController =  new Menu();
            superAdminMenuController.BackToSuperAdminMenu(HomeBTN);
        });

        ConfirmBTN.setOnAction(event -> {
            label.setText("");


            if(First.isSelected() && Second.isSelected()){
                label.setText("You can choose one policy");
            }

            else if(!First.isSelected() && !Second.isSelected()){

                label.setText("Financial policy : Fixed ");

                //back it :
                try {
                    if(databaseHandler.ReadPolicy().equalsIgnoreCase("first")){

                        //Increase 20% Ticket Prices :
                        DecreaseTicketPrice(120);

                        //Increase Employee salary :
                        DecreaseEmployeeSalary(120);

                    }

                    else if(databaseHandler.ReadPolicy().equalsIgnoreCase("second")){


                        //increase 10% ticket price :
                        IncreaseTicketPrice(110);

                        //increase 10% employee salary :

                        IncreaseEmployeeSalary(110);

                        //decrease 10% manager salary :

                        DecreaseManagerSalary(110);

                    }
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }

                try {
                    if(!databaseHandler.ReadPolicy().equalsIgnoreCase("fixed")){

                        try {
                            databaseHandler.AddPolicy("fixed");
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }


            }

            else {
                try {
                    if(databaseHandler.ReadPolicy().equalsIgnoreCase("first") && First.isSelected()){
                        label.setText("This policy is already using ");
                    }

                    else if(databaseHandler.ReadPolicy().equalsIgnoreCase("second") && Second.isSelected()){
                        label.setText("This policy is already using ");
                    }

                    else if(First.isSelected() && !databaseHandler.ReadPolicy().equalsIgnoreCase("first")){

                        //Back to the fixed model :
                        if(databaseHandler.ReadPolicy().equalsIgnoreCase("second")){

                            //increase 10% ticket price :
                            IncreaseTicketPrice(110);

                            //increase 10% employee salary :

                            IncreaseEmployeeSalary(110);

                            //decrease 10% manager salary :

                            DecreaseManagerSalary(110);
                        }


                        label.setText("Financial Policy : First");
                        try {
                            databaseHandler.AddPolicy("first");
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        //Do the First policy :

                        //Increase 20% Ticket Prices :
                        IncreaseTicketPrice(120);

                        //Increase Employee salary :
                        IncreaseEmployeeSalary(120);

                    }

                    else if(Second.isSelected() && !databaseHandler.ReadPolicy().equalsIgnoreCase("second")){

                        //Back to the fixed Policy
                        if(databaseHandler.ReadPolicy().equalsIgnoreCase("first")){

                            //Decrease 20% ticket price :
                            DecreaseTicketPrice(120);

                            //Decrease 20% employee price :
                            DecreaseEmployeeSalary(120);

                        }

                        label.setText("Financial Policy : Second");
                        try {
                            databaseHandler.AddPolicy("second");
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }


                        //Do the second  policy :

                        //Decrease 10% ticket price :
                        DecreaseTicketPrice(110);

                        //Decrease 10% employee salary :
                        DecreaseEmployeeSalary(110);

                        //Increase 10% manager salary :
                        IncreaseManagerSalary(110);

                    }
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

        });



//        FixedBTN.setOnAction(event -> {
//
//
//            try {
//                for(Ticket ticket  : databaseHandler.ReadTicket()){
//                databaseHandler.UpdateTicket(ticket.getId() , ticket.getTicketPrice() , (ticket.getPenalty()*4)/5);
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//
//
////**********************************************************************************************************************
//
//            try {
//                for(int i : getPass().keySet()){
//
//                    if(getPass().get(i) < 5){
//                        databaseHandler.UpdatePassengerCredit(123.23 , 30001);
//                        System.out.println("3");
//                    }
//
//                    else{
//
//                        double credit = get3Credit(30001);
//                        int id = i ;
//                      databaseHandler.UpdatePassengerCredit(1233545 , id);
//                        System.out.println("5");
//                        System.out.println(credit);
////                        System.out.println(get3Credit(i));
////                        System.out.println(i);
//                    }
//
//                }
//
//            }
//
//            catch (SQLException e) {
//                System.out.println("no");
//                e.printStackTrace();
//            } catch (ClassNotFoundException e) {
//                System.out.println("no");
//                e.printStackTrace();
//            }
//
//
//
//
//
//
//        });
//
//        SecondBTN.setOnAction(event -> {
//
//            try {
//                decreaseTickets();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//
//            try {
//                decreaseSalaryPrice();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//
//            try {
//                IncreaseManagerSalary();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//
//        });
//
//        FirstBTN.setOnAction(event -> {
//
//            try {
//                IncreaseTicketPrice();
//
//            } catch (SQLException e) {
//                e.printStackTrace();
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//            try {
//                IncreaseSalaryPrice();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//        });

    }

    //Ticket :

    private void IncreaseTicketPrice(int price) throws SQLException, ClassNotFoundException {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection =  databaseHandler.getConnection();
        for(Ticket ticket : databaseHandler.ReadTicket()){
            databaseHandler.UpdateTicket(ticket.getId() , ((ticket.getTicketPrice()*price)/100) , ticket.getPenalty());
        }
    }

    private void DecreaseTicketPrice(int price) throws SQLException, ClassNotFoundException {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection =  databaseHandler.getConnection();
        for(Ticket ticket : databaseHandler.ReadTicket()){
            databaseHandler.UpdateTicket(ticket.getId() , ((ticket.getTicketPrice() * 100 ) / price) , ticket.getPenalty());
        }
    }


    //employee :

    private void IncreaseEmployeeSalary(int price) throws SQLException, ClassNotFoundException {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection =  databaseHandler.getConnection();
        for(Employee employee : databaseHandler.ReadEmployee()){
            databaseHandler.UpdateEmployeeSalary((price * employee.getSalary()) / 100  , employee.getId());        }
    }

    private void DecreaseEmployeeSalary(int price) throws SQLException, ClassNotFoundException {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection =  databaseHandler.getConnection();
        for(Employee employee : databaseHandler.ReadEmployee()){
            databaseHandler.UpdateEmployeeSalary((100 * employee.getSalary()) / price , employee.getId());        }
    }


    //manager :

    private void IncreaseManagerSalary(int price) throws SQLException, ClassNotFoundException {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection =  databaseHandler.getConnection();
        for(Manager manager : databaseHandler.ReadManagers()){
            databaseHandler.UpdateManagerSalary((manager.getSalary() * price) / 100 , manager.getId());
        }
    }

    private void DecreaseManagerSalary(int price) throws SQLException, ClassNotFoundException {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection =  databaseHandler.getConnection();
        for(Manager manager : databaseHandler.ReadManagers()){
            databaseHandler.UpdateManagerSalary((manager.getSalary() * 100) / price , manager.getId());
        }
    }


    }



