package sample.controller.manager.FlightAndPlane.Plane.View;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Database.DatabaseHandler;
import sample.controller.LoginMenuController;
import sample.model.Airplane;
import sample.model.Flight;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class FlightList {
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
    private JFXButton HomeBTN;

    @FXML
    private JFXButton Ticket;

    @FXML
    private javafx.scene.control.Label Label;


    LoginMenuController loginMenuController = new LoginMenuController();

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {


        Ticket.setOnAction(event -> {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/manager/FlightAndPlane/Plane/View/Ticket.fxml"));
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

        HomeBTN.setOnAction(event -> {
            loginMenuController.ChangeWindow(HomeBTN , "/sample/view/manager/FlightAndPlane/Plane/View/View.fxml" , "Flight Management");
        });


        IdColumn.setCellValueFactory(new PropertyValueFactory<Flight , Integer>("Id"));
        PlaneIdColumn.setCellValueFactory(new PropertyValueFactory<Flight , Integer>("airplaneId"));
        TicketId.setCellValueFactory(new PropertyValueFactory<Flight , Integer>("ticketId"));
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

    }

    private ObservableList<Flight> getFlights() throws SQLException, ClassNotFoundException {
        ObservableList<Flight> flights = FXCollections.observableArrayList();
        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection ;
        connection = databaseHandler.getConnection();
        View view = new View();

        for(Airplane plane : databaseHandler.ReadPlanes()){

        if(view.PlaneId == plane.getId()){
            for(Flight flight : plane.getFlightLists()){
                flights.add(flight);
            }
        }


        }
        return flights;
    }

}
