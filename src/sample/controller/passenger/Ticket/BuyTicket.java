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
import sample.controller.passenger.Login;
import sample.model.Airplane;
import sample.model.Flight;
import sample.model.Passenger;
import sample.model.Ticket;

public class BuyTicket {

    @FXML
    private TableView<Flight> tableView1;

    @FXML private TableColumn<Flight , Integer> FlightId;

    @FXML private TableColumn<Flight , String> OriginColumn;

    @FXML private TableColumn<Flight , String> DestinationColumn;

    @FXML private TableColumn<Flight , String> DateColumn;

    @FXML private TableColumn<Flight , String> TimeColumn;

    @FXML private TableColumn<Flight , Integer> S_T_NColumn;

    @FXML private TableColumn<Flight , Double> DurationColumn;


//**************************************************************
//**************************************************************
//**************************************************************


    @FXML
    private TableView<Ticket> tableView2;

    @FXML private TableColumn<Flight , Integer> TicketId;

    @FXML private TableColumn<Flight , Double> PriceId;

    @FXML private TableColumn<Flight , Double> PenaltyId;

    @FXML private Label label ;


    @FXML
    private JFXButton HomeBTN;

    @FXML
    private JFXButton BuyBTN;

    LoginMenuController loginMenuController = new LoginMenuController();

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {

        HomeBTN.setOnAction(event -> {
            loginMenuController.ChangeWindow(HomeBTN , "/sample/view/passenger/Ticket/PassengerTicket.fxml" , "Ticket Management");
        });

        BuyBTN.setOnAction(event -> {

            if(!tableView1.getSelectionModel().isEmpty()){

                Login login = new Login();
                try {
                    if(checkTheCapacity() ){
                        if( CheckTime(login.IdManager)){

                            DatabaseHandler databaseHandler = new DatabaseHandler();
                            Connection connection ;
                            connection = databaseHandler.getConnection();

                            //lower credit :
                            LowerCredit(login.IdManager , tableView1.getSelectionModel().getSelectedItem().getId());


                            //increase S_T_N :
                            increaseSoldTicketNumber(tableView1.getSelectionModel().getSelectedItem().getId() , tableView1.getSelectionModel().getSelectedItem().getId());


                            //write to database :
                            databaseHandler.AddTicketPassenger(login.IdManager , getTicketId() , tableView1.getSelectionModel().getSelectedItem().getId() );

                            //check the Discount :
                                Discount(CheckDiscount(login.IdManager));

                        }
                        else{                        label.setText("Flight time interference");

                        }
                    }
                    else {                            label.setText("Complete flight capacity");

                    }


                }

                catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }


            }
            else {
                this.BuyBTN.setDisable(true);
            }


        });


        //first table :
        FlightId.setCellValueFactory(new PropertyValueFactory<Flight , Integer>("Id"));
        OriginColumn.setCellValueFactory(new PropertyValueFactory<Flight , String>("Origin"));
        DestinationColumn.setCellValueFactory(new PropertyValueFactory<Flight , String>("Destination"));
        DateColumn.setCellValueFactory(new PropertyValueFactory<Flight , String>("FlightsDate"));
        TimeColumn.setCellValueFactory(new PropertyValueFactory<Flight , String>("FlightsTime"));
        S_T_NColumn.setCellValueFactory(new PropertyValueFactory<Flight , Integer>("Sold_Tickets_Number"));
        DurationColumn.setCellValueFactory(new PropertyValueFactory<Flight , Double>("FlightDuration"));

        //load dummy data :
        tableView1.setItems(getFlights());

        tableView1.setEditable(true);


        //second table :
        TicketId.setCellValueFactory(new PropertyValueFactory<Flight , Integer>("Id"));
        PriceId.setCellValueFactory(new PropertyValueFactory<Flight , Double>("ticketPrice"));
        PenaltyId.setCellValueFactory(new PropertyValueFactory<Flight , Double>("penalty"));

        //load dummy data :
        tableView2.setItems(getTicket());

        tableView2.setEditable(true);



    }
    public void Discount(int count) throws SQLException, ClassNotFoundException {
        Login login = new Login();
        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection ;
        connection = databaseHandler.getConnection();
        double credit =  0;

        for(Passenger passenger : databaseHandler.ReadPassengers()){
            if (passenger.getId() == login.IdManager){
                credit = passenger.getCredit();
            }
        }


        if(count  >= 3 && count < 5 ){
            databaseHandler.UpdatePassengerCredit((credit*11)/10 , login.IdManager);
        }
        else if(count >= 5){
            databaseHandler.UpdatePassengerCredit((credit*12)/10 , login.IdManager);
        }
    }

    private int CheckDiscount(int idManager) throws SQLException, ClassNotFoundException {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection ;
        connection = databaseHandler.getConnection();
        int count = 0  ;

        for(int i =  0 ; i < databaseHandler.ReadPassengerTicket().size() ; i++){
            if(databaseHandler.ReadPassengerTicket().get(i).getPassengerId() == idManager){
                count++;
            }
        }

        return count;
    }

    private int getTicketId() throws SQLException, ClassNotFoundException {
        int id = 0 ;

        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection ;
        connection = databaseHandler.getConnection();

        for(Flight flight : databaseHandler.ReadFlights()){
            if(flight.getId() == tableView1.getSelectionModel().getSelectedItem().getId()){
                id = flight.getTicketId();
            }
        }

        return id;
    }

    public void increaseSoldTicketNumber(int id , int flightId) throws SQLException, ClassNotFoundException {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection ;
        connection = databaseHandler.getConnection();
        int S_T_N = 0;

        for(Flight flight : databaseHandler.ReadFlights()){
            if(flight.getId() == id){
                S_T_N = flight.getSold_Tickets_Number() + 1;
                databaseHandler.UpdateFlight(flight.getAirplaneId() , flight.getTicketId() , flight.getOrigin() , flight.getDestination() , flight.getFlightsDate() , flight.getFlightsTime() , S_T_N , flight.getFlightDuration() , flight.getFlightStatus() , flight.getId());

            }
        }

    }

    public  void LowerCredit(int PassengerId , int flightId) throws SQLException, ClassNotFoundException {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection ;
        connection = databaseHandler.getConnection();
        double credit = 0;

        for(Passenger passenger : databaseHandler.ReadPassengers()){
            if(passenger.getId() == PassengerId){
                credit = passenger.getCredit() - getFlightPrice(flightId) ;

                databaseHandler.UpdatePassengerCredit(credit , PassengerId);
            }
        }
    }

    public double getFlightPrice(int flightId) throws SQLException, ClassNotFoundException {
        double price = 0 ;
        int ticketId = 0 ;
        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection ;
        connection = databaseHandler.getConnection();

        for(Flight flight : databaseHandler.ReadFlights()){
            if (flight.getId() == flightId){
                ticketId = flight.getTicketId();
            }
        }

        for(Ticket ticket : databaseHandler.ReadTicket()){
            if(ticketId == ticket.getId()){
                price = ticket.getTicketPrice();
            }
        }


        return  price ;
    }

    private boolean CheckTime(int PassengerId) throws SQLException, ClassNotFoundException {
        boolean flag = true;

        String date = "";

        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection ;
        connection = databaseHandler.getConnection();

        for(Test passenger : databaseHandler.ReadPassengerTicket()){

            if(passenger.getPassengerId() == PassengerId){

            if(GetDate(passenger.getFlightId()).equalsIgnoreCase(tableView1.getSelectionModel().getSelectedItem().getFlightsDate())){
                flag = false;
            }

            }

        }


        return flag;
    }

    private boolean checkTheCapacity() throws SQLException, ClassNotFoundException {
        boolean flag = false;
        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection ;
        connection = databaseHandler.getConnection();

        int capacity = 0;
        int S_T_N = tableView1.getSelectionModel().getSelectedItem().getSold_Tickets_Number();


        for(Airplane plane : databaseHandler.ReadPlanes()){
            if(tableView1.getSelectionModel().getSelectedItem().getAirplaneId() == plane.getId()){
                capacity = plane.getNumber_of_seats();
            }
        }

        if(capacity > S_T_N){
            flag = true ;
        }
        else{
            label.setText("No Capacity");
        }


        return flag;
    }

    private String GetDate(int flightId) throws SQLException, ClassNotFoundException {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection ;
        connection = databaseHandler.getConnection();
        String date = "";

        for(Flight flight : databaseHandler.ReadFlights()){
            if(flight.getId() == flightId){
                date = flight.getFlightsDate();
            }
        }

        return date;
    }

    private ObservableList<Ticket> getTicket() throws SQLException, ClassNotFoundException {

        ObservableList<Ticket> tickets = FXCollections.observableArrayList();
        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection ;
        connection = databaseHandler.getConnection();

        for(Flight flight : databaseHandler.ReadFlights()){

            if(flight.getStatus().equals("undone")){
                for(Ticket ticket  :databaseHandler.ReadTicket()){

                    if(flight.getTicketId() == ticket.getId()){

                        tickets.add(ticket);

                    }
                }
            }



        }
        if(tickets.size()==0){
            this.BuyBTN.setDisable(true);

        }
        else{
            this.BuyBTN.setDisable(false);

        }
        return tickets;
    }

    private ObservableList<Flight> getFlights() throws SQLException , ClassNotFoundException {

        ObservableList<Flight> flights = FXCollections.observableArrayList();
        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection ;
        connection = databaseHandler.getConnection();

        for(Flight flight : databaseHandler.ReadFlights()){

            if(flight.getStatus()== "undone"){
                flights.add(flight);
            }

        }
        if(flights.size()==0){
            this.BuyBTN.setDisable(true);

        }
        else{
            this.BuyBTN.setDisable(false);

        }
        return flights;
    }

}
