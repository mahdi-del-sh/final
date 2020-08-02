package sample.controller.employee.FlightAndPlane.Flight.View;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import sample.Database.DatabaseHandler;
import sample.controller.LoginMenuController;
import sample.controller.employee.FlightAndPlane.Flight.Add.Add;
import sample.model.Flight;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class View {


    @FXML
    private TableView<Flight> tableView;

    @FXML private TableColumn<Flight , Integer> IdColumn;

    @FXML private TableColumn<Flight , Integer> TicketId;

    @FXML private TableColumn<Flight , Integer> PlaneIdColumn;

    @FXML private TableColumn<Flight , String> OriginColumn;

    @FXML private TableColumn<Flight , String> DestinationColumn;

    @FXML private TableColumn<Flight , String> DateColumn;

    @FXML private TableColumn<Flight , String> TimeColumn;

    @FXML private TableColumn<Flight , Integer> S_T_NColumn;

    @FXML private TableColumn<Flight , Double> DurationColumn;

    @FXML private TableColumn<Flight , String> StatusColumn;

    @FXML private TableColumn<Flight , String> PassengerColumn;


    @FXML
    private JFXButton ShowPassengerList;

    @FXML
    private JFXButton HomeBTN;

    @FXML
    private JFXButton Ticket;

    @FXML
    private JFXButton Delete;

    @FXML
    private JFXButton Confirm;

    @FXML
    private Label Label;

   public static int flightId ;

    ArrayList<Flight> changes = new ArrayList<>();
    LoginMenuController loginMenuController = new LoginMenuController();

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {

        HomeBTN.setOnAction(event -> {
         loginMenuController.ChangeWindow(HomeBTN , "/sample/view/employee/FlightAndPlane/Panel.fxml" , "Flight And Plane Management");
        });

        Confirm.setOnAction(event -> {
            DatabaseHandler databaseHandler = new DatabaseHandler();
            Connection connection ;
            try {
                connection = databaseHandler.getConnection();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            for(Flight flight  :changes){
                databaseHandler.UpdateFlight(flight.getAirplaneId() , flight.getTicketId() , flight.getOrigin() , flight.getDestination() , flight.getFlightsDate() , flight.getFlightsTime() , flight.getSold_Tickets_Number() , flight.getFlightDuration() , flight.getFlightStatus() , flight.getId());
            }
            Label.setText("Changed successfully");
        });

        Delete.setOnAction(event -> {
            try {
                deleteButtonPushed();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        Ticket.setOnAction(event -> {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/employee/FlightAndPlane/Flight/View/TicketView.fxml"));
            try{
                loader.load();
            }catch (IOException e){
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setTitle("Ticket Management");
            stage.setScene(new Scene(root));
            stage.show();

        });


        ShowPassengerList.setOnAction(event -> {
            if(tableView.getSelectionModel().isEmpty()){
                Label.setText("Choose a row");
            }
            else{
                flightId = tableView.getSelectionModel().getSelectedItem().getId();
                loginMenuController.ChangeWindow(ShowPassengerList , "/sample/view/employee/FlightAndPlane/Flight/View/PassengerList.fxml" , "Passenger List");
            }
        });

        //set up the columns in table :
        IdColumn.setCellValueFactory(new PropertyValueFactory<Flight , Integer>("Id"));
        PlaneIdColumn.setCellValueFactory(new PropertyValueFactory<Flight , Integer>("airplaneId"));
        TicketId.setCellValueFactory(new PropertyValueFactory<Flight , Integer>("ticketId"));
        //TicketPriceColumn.setCellValueFactory(new PropertyValueFactory<Ticket , Double>("ticketPrice"));
        //PenaltyColumn.setCellValueFactory(new PropertyValueFactory<Ticket , Double>("penalty"));
        OriginColumn.setCellValueFactory(new PropertyValueFactory<Flight , String>("Origin"));
        DestinationColumn.setCellValueFactory(new PropertyValueFactory<Flight , String>("Destination"));
        DateColumn.setCellValueFactory(new PropertyValueFactory<Flight , String>("FlightsDate"));
        TimeColumn.setCellValueFactory(new PropertyValueFactory<Flight , String>("FlightsTime"));
        S_T_NColumn.setCellValueFactory(new PropertyValueFactory<Flight , Integer>("Sold_Tickets_Number"));
        DurationColumn.setCellValueFactory(new PropertyValueFactory<Flight , Double>("FlightDuration"));
        StatusColumn.setCellValueFactory(new PropertyValueFactory<Flight , String>("status"));
        PassengerColumn.setCellValueFactory(new PropertyValueFactory<Flight , String>("PassengerListString"));


        //load dummy data :
        tableView.setItems(getFlights());

        tableView.setEditable(true);

        PlaneIdColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
       // TicketId.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        OriginColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        DestinationColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        DateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        TimeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        StatusColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        PassengerColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        S_T_NColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        DurationColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        tableView.getSelectionModel().setCellSelectionEnabled(true);
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }

    private void deleteButtonPushed() throws SQLException, ClassNotFoundException {


            ObservableList<Flight> selectedRows, allFlights;
            allFlights = tableView.getItems();

            selectedRows = tableView.getSelectionModel().getSelectedItems();

            DatabaseHandler databaseHandler = new DatabaseHandler();
            Connection connection ;
            connection = databaseHandler.getConnection();

            databaseHandler.DeleteFlight(tableView.getSelectionModel().getSelectedItem().getId());
            for (Flight flight : selectedRows)
                allFlights.remove(flight);



    }

    //edit :
    public void changePlaneIdCellEvent(TableColumn.CellEditEvent edittedCell) throws SQLException, ClassNotFoundException {

        Flight FlightSelected =  tableView.getSelectionModel().getSelectedItem();
        FlightSelected.setAirplaneId((Integer) edittedCell.getNewValue());
        Add add = new Add();
        if(add.FindPlane((Integer) edittedCell.getNewValue())){

            this.Confirm.setDisable(false);
            Label.setText("");
            for(Flight flight : changes){
                if(flight.getId()==tableView.getSelectionModel().getSelectedItem().getId()){
                    flight.setAirplaneId((Integer) edittedCell.getNewValue());
                }
            }

        }
        else{
            this.Confirm.setDisable(true);
            Label.setText("Enter Valid Plane Id");
        }

    }

    public void changeOriginCellEvent(TableColumn.CellEditEvent edittedCell)  {

        Flight FlightSelected =  tableView.getSelectionModel().getSelectedItem();
        FlightSelected.setOrigin((String) edittedCell.getNewValue());

        if(edittedCell.getNewValue().toString().matches("^[a-zA-Z]*$")){

            this.Confirm.setDisable(false);
            Label.setText("");
            for(Flight flight : changes){
                if(flight.getId()==tableView.getSelectionModel().getSelectedItem().getId()){
                    flight.setOrigin((edittedCell.getNewValue().toString()));
                }
            }
        }
        else{
            this.Confirm.setDisable(true);
            Label.setText("Enter Valid Plane Origin");
        }

    }//*

    public void changeDestinationCellEvent(TableColumn.CellEditEvent edittedCell) {

        Flight FlightSelected =  tableView.getSelectionModel().getSelectedItem();
        FlightSelected.setDestination((String) edittedCell.getNewValue());
        if(edittedCell.getNewValue().toString().matches("^[a-zA-Z]*$")){

            this.Confirm.setDisable(false);
            Label.setText("");
            for(Flight flight : changes){
                if(flight.getId()==tableView.getSelectionModel().getSelectedItem().getId()){
                    flight.setDestination((edittedCell.getNewValue().toString()));
                }
            }
        }
        else{
            this.Confirm.setDisable(true);
            Label.setText("Enter Valid Destination");
        }
    } //*

    public void changeDateCellEvent(TableColumn.CellEditEvent edittedCell) {

        Flight FlightSelected =  tableView.getSelectionModel().getSelectedItem();
        FlightSelected.setFlightsDate((String) edittedCell.getNewValue());
        if(edittedCell.getNewValue().toString().matches("([1-9]|[12][0-9]|3[01])-(0?[1-9]|1[012])-((13|20)\\d\\d)")){

            this.Confirm.setDisable(false);
            Label.setText("");
            for(Flight flight : changes){
                if(flight.getId()==tableView.getSelectionModel().getSelectedItem().getId()){
                    flight.setFlightsDate((edittedCell.getNewValue().toString()));
                }
            }
        }
        else{
            this.Confirm.setDisable(true);
            Label.setText("Enter Valid Date");
        }
    } //*

    public void changeTimeCellEvent(TableColumn.CellEditEvent edittedCell) {

        Flight FlightSelected =  tableView.getSelectionModel().getSelectedItem();
        FlightSelected.setFlightsTime((String) edittedCell.getNewValue());

        if(edittedCell.getNewValue().toString().matches("(\\d\\d:\\d\\d)")){

            this.Confirm.setDisable(false);
            Label.setText("");
            for(Flight flight : changes){
                if(flight.getId()==tableView.getSelectionModel().getSelectedItem().getId()){
                    flight.setFlightsTime((edittedCell.getNewValue().toString()));
                }
            }
        }
        else{
            this.Confirm.setDisable(true);
            Label.setText("Enter Valid Hour");
        }

    }

    public void changeS_T_NCellEvent(TableColumn.CellEditEvent edittedCell){

        Flight FlightSelected =  tableView.getSelectionModel().getSelectedItem();
        FlightSelected.setSold_Tickets_Number((Integer) edittedCell.getNewValue());

        if(edittedCell.getNewValue().toString().matches("(?<=\\s|^)\\d+(?=\\s|$)")&& (Integer) edittedCell.getNewValue() > 0 && (Integer) edittedCell.getNewValue() < 400){

            this.Confirm.setDisable(false);
            Label.setText("");
            for(Flight flight : changes){
                if(flight.getId()==tableView.getSelectionModel().getSelectedItem().getId()){
                    flight.setSold_Tickets_Number(((Integer)edittedCell.getNewValue()));
                }
            }
        }
        else{
            this.Confirm.setDisable(true);
            Label.setText("Enter Valid S_T_N");
        }

    }

    public void changeDurationCellEvent(TableColumn.CellEditEvent edittedCell){

        Flight FlightSelected =  tableView.getSelectionModel().getSelectedItem();
        FlightSelected.setFlightDuration((Double) edittedCell.getNewValue());
        if(edittedCell.getNewValue().toString().matches("(\\d+\\.\\d+)")){

            this.Confirm.setDisable(false);
            Label.setText("");
            for(Flight flight : changes){
                if(flight.getId()==tableView.getSelectionModel().getSelectedItem().getId()){
                    flight.setFlightDuration(((Double)edittedCell.getNewValue()));
                }
            }

        }
        else{
            this.Confirm.setDisable(true);
            Label.setText("Enter Valid Duration");
        }

    }

    public void changeStatusCellEvent(TableColumn.CellEditEvent edittedCell) {

        Flight FlightSelected =  tableView.getSelectionModel().getSelectedItem();
        FlightSelected.SetStatus((String) edittedCell.getNewValue());

        if(edittedCell.getNewValue().toString().equalsIgnoreCase("done") || edittedCell.getNewValue().toString().equalsIgnoreCase("undone") || edittedCell.getNewValue().toString().equalsIgnoreCase("flying") ){

            this.Confirm.setDisable(false);
            Label.setText("");
            for(Flight flight : changes){
                if(flight.getId()==tableView.getSelectionModel().getSelectedItem().getId()){
                    flight.SetStatus(((String)edittedCell.getNewValue()));
                }
            }
        }
        else{
            this.Confirm.setDisable(true);
            Label.setText("Enter Valid Status");
        }

    }


    private ObservableList<Flight> getFlights() throws SQLException, ClassNotFoundException {

        ObservableList<Flight> flights = FXCollections.observableArrayList();
        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection ;
        connection = databaseHandler.getConnection();

        for(Flight flight : databaseHandler.ReadFlights()){
            //  airplane.SetFlightListString();
            flights.add(flight);
            changes.add(flight);

        }
        if(flights.size()==0){
            this.Delete.setDisable(true);

        }
        else{
            this.Delete.setDisable(false);

        }
        return flights;
    }



}
