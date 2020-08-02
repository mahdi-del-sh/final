package sample.controller.employee.FlightAndPlane.Flight.View;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Database.DatabaseHandler;
import sample.controller.LoginMenuController;
import sample.model.Flight;
import sample.model.Passenger;

import java.sql.Connection;
import java.sql.SQLException;

public class PassengerList {



    @FXML
    private TableView<Passenger> tableView;

    @FXML private TableColumn<Passenger , Integer> IdColumn;

    @FXML private TableColumn<Passenger , String> nameColumn;

    @FXML private TableColumn<Passenger , String> lastNameColumn;

    @FXML private TableColumn<Passenger , String> usernameColumn;

    @FXML private TableColumn<Passenger , String> passwordColumn;

    @FXML private TableColumn<Passenger , String> phoneColumn;

    @FXML private TableColumn<Passenger , Double> creditColumn;

    @FXML private TableColumn<Passenger , String> emailColumn;

    @FXML
    private Label label;

    @FXML
    private JFXButton HomeBTN;

    LoginMenuController loginMenuController =  new LoginMenuController();

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        HomeBTN.setOnAction(event -> {
            loginMenuController.ChangeWindow(HomeBTN , "/sample/view/employee/FlightAndPlane/Flight/View/View.fxml" , "Flight Management");
        });

        //set up columns in table :
        IdColumn.setCellValueFactory(new PropertyValueFactory<Passenger , Integer>("Id"));
        creditColumn.setCellValueFactory(new PropertyValueFactory<Passenger , Double>("credit"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Passenger , String>("name"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Passenger , String>("lastName"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<Passenger , String>("userName"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<Passenger , String>("password"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<Passenger , String>("phoneNumber"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<Passenger , String>("Email"));

        //load dummy data :
        tableView.setItems(getPassengers());


    }

    private ObservableList<Passenger> getPassengers() throws SQLException, ClassNotFoundException {

        ObservableList<Passenger> passengers = FXCollections.observableArrayList();
        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection ;
        connection = databaseHandler.getConnection();

        View view = new View();

        for(Flight flight : databaseHandler.ReadFlights()){
            if(flight.getId() == view.flightId){
                for(Passenger passenger : flight.getPassengersList()){
                    passengers.add(passenger);
                }
            }
        }


        return passengers;
    }


}
