package sample.controller.passenger.Ticket;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Database.DatabaseHandler;
import sample.Test;
import sample.controller.LoginMenuController;
import sample.controller.manager.login;
import sample.controller.passenger.Login;
import sample.model.Flight;
import sample.model.Passenger;
import sample.model.Ticket;

public class CancelTicket {

    @FXML
    private TableView<Flight> tableView1;

    @FXML private TableColumn<Flight , Integer> FlightId;

    @FXML private TableColumn<Flight , String> OriginColumn;

    @FXML private TableColumn<Flight , String> DestinationColumn;

    @FXML private TableColumn<Flight , String> DateColumn;

    @FXML private TableColumn<Flight , String> TimeColumn;

    @FXML private TableColumn<Flight , Double> DurationColumn;

    //second :

    @FXML
    private TableView<Test> tableView2;

    @FXML private TableColumn<Test , Integer> TicketId;




    @FXML
    private JFXButton HomeBTN;

    @FXML
    private JFXButton Cancel;

    @FXML
    private Label label;

    LoginMenuController loginMenuController =  new LoginMenuController();

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {

        HomeBTN.setOnAction(event -> {
            loginMenuController.ChangeWindow(HomeBTN , "/sample/view/passenger/Ticket/PassengerTicket.fxml" , "Ticket Management");
        });

        Cancel.setOnAction(event -> {
            if(tableView2.getSelectionModel().isEmpty()){
                System.out.println("Choose Some thing");
            }
            else{
                DatabaseHandler databaseHandler = new DatabaseHandler();
                Connection connection ;
                try {
                    connection = databaseHandler.getConnection();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                //Upper Credit :
                try {
                    UpperCredit();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                //decrease S_T_N :
                try {
                    decreaseS_T_N();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                //write to database :
                databaseHandler.DeleteTicketPassenger(tableView2.getSelectionModel().getSelectedItem().getPassengerId() , tableView2.getSelectionModel().getSelectedItem().getTicketId() , tableView2.getSelectionModel().getSelectedItem().getFlightId());


            }
        });

        //first table :
        FlightId.setCellValueFactory(new PropertyValueFactory<Flight , Integer>("Id"));
        OriginColumn.setCellValueFactory(new PropertyValueFactory<Flight , String>("Origin"));
        DestinationColumn.setCellValueFactory(new PropertyValueFactory<Flight , String>("Destination"));
        DateColumn.setCellValueFactory(new PropertyValueFactory<Flight , String>("FlightsDate"));
        TimeColumn.setCellValueFactory(new PropertyValueFactory<Flight , String>("FlightsTime"));
        DurationColumn.setCellValueFactory(new PropertyValueFactory<Flight , Double>("FlightDuration"));

        //load dummy data :
        tableView1.setItems(getFlights());

        tableView1.setEditable(true);


        //second table :
        TicketId.setCellValueFactory(new PropertyValueFactory<Test , Integer>("ticketId"));
        //load dummy data :
        tableView2.setItems(getTicket());

        tableView2.setEditable(true);
    }

    private void decreaseS_T_N() throws SQLException, ClassNotFoundException {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection ;
        connection = databaseHandler.getConnection();
        int id = tableView2.getSelectionModel().getSelectedItem().getFlightId();
        int S_T_N = 0;

        for(Flight flight : databaseHandler.ReadFlights()){
            if(flight.getId() == id){
                S_T_N = flight.getSold_Tickets_Number() - 1;
                databaseHandler.UpdateFlight(flight.getAirplaneId() , flight.getTicketId() , flight.getOrigin() , flight.getDestination() , flight.getFlightsDate() , flight.getFlightsTime() , S_T_N , flight.getFlightDuration() , flight.getFlightStatus() , flight.getId());

            }
        }

    }

    private void UpperCredit() throws SQLException, ClassNotFoundException {

        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection ;
        connection = databaseHandler.getConnection();

        Login login = new Login();
        int id = login.IdManager;

        double price  = 0;

        double penalty = 0;

        double credit = 0;

        for(Ticket ticket : databaseHandler.ReadTicket()){
            if(ticket.getId() == tableView2.getSelectionModel().getSelectedItem().getTicketId()){
                price = ticket.getTicketPrice();
                penalty = ticket.getPenalty();
            }
        }

        for(Passenger passenger : databaseHandler.ReadPassengers()){
            if(passenger.getId() == id){
                credit = passenger.getCredit() + price - penalty ;
            }
        }

        databaseHandler.UpdatePassengerCredit(credit , id);
    }

    private ObservableList<Test> getTicket() throws SQLException, ClassNotFoundException {

        ObservableList<Test> tickets = FXCollections.observableArrayList();
        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection ;
        connection = databaseHandler.getConnection();

        Login login = new Login();

        for(Test ticket : databaseHandler.ReadPassengerTicket()){
            if(ticket.getPassengerId() == login.IdManager && CheckFlight(ticket.getFlightId())){
                tickets.add(ticket);
            }
        }


        return tickets;
    }

    private boolean CheckFlight(int flightId) throws SQLException, ClassNotFoundException {
        boolean flag = false ;
        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection ;
        connection = databaseHandler.getConnection();

        for(Flight flight : databaseHandler.ReadFlights()){
            if(flight.getId()==flightId){
                if(flight.getStatus().equalsIgnoreCase("undone")){
                    flag = true;
                }
            }
        }

        return flag;
    }

    private ObservableList<Flight> getFlights() throws SQLException, ClassNotFoundException {
        ObservableList<Flight> flights = FXCollections.observableArrayList();
        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection ;
        connection = databaseHandler.getConnection();

        for(Test ticket : getTicket()){

            for(Flight flight : databaseHandler.ReadFlights()){
                if(ticket.getFlightId()== flight.getId() && flight.getStatus().equalsIgnoreCase("undone")){
                    flights.add(flight);
                }
            }

        }

        return flights;
    }
}
