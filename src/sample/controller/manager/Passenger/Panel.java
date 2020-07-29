package sample.controller.manager.Passenger;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import sample.Database.DatabaseHandler;
import sample.controller.manager.menu;
import sample.model.Flight;
import sample.model.Passenger;

public class Panel {

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

    @FXML private TableColumn<Passenger , Integer> flightIdColumn;

    @FXML
    private JFXButton Confirm;

    @FXML
    private JFXButton Delete;

    @FXML
    private Label label;

    @FXML
    private JFXButton HomeBTN;

    ArrayList<Passenger> changes = new ArrayList<>();

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {

        HomeBTN.setOnAction(event -> {
            menu managerMenuController =  new menu();
            managerMenuController.BackToManagerMenu(HomeBTN);
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
    for(Passenger passenger  :changes){
        databaseHandler.UpdatePassenger(passenger.getId() , passenger.getName() , passenger.getLastName() , passenger.getUserName() , passenger.getPassword() , passenger.getPhoneNumber() , passenger.getEmail() , passenger.getCredit() , passenger.getFlightid());
    }
    label.setText("Changed successfully");
});
        //set up columns in table :
        IdColumn.setCellValueFactory(new PropertyValueFactory<Passenger , Integer>("Id"));
        flightIdColumn.setCellValueFactory(new PropertyValueFactory<Passenger , Integer>("flightid"));
        creditColumn.setCellValueFactory(new PropertyValueFactory<Passenger , Double>("credit"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Passenger , String>("name"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Passenger , String>("lastName"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<Passenger , String>("userName"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<Passenger , String>("password"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<Passenger , String>("phoneNumber"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<Passenger , String>("Email"));


        //load dummy data :
        tableView.setItems(getPassengers());

        tableView.setEditable(true);

        flightIdColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        creditColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        usernameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        passwordColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        phoneColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        emailColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        tableView.getSelectionModel().setCellSelectionEnabled(true);
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }

    private void deleteButtonPushed() throws SQLException, ClassNotFoundException {
        ObservableList<Passenger> selectedRows, allFlights;
        allFlights = tableView.getItems();

        selectedRows = tableView.getSelectionModel().getSelectedItems();

        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection ;
        connection = databaseHandler.getConnection();

        databaseHandler.DeletePassenger(tableView.getSelectionModel().getSelectedItem().getId());
        for (Passenger flight : selectedRows)
            allFlights.remove(flight);

    }

    public void changeFirstNameCellEvent(TableColumn.CellEditEvent edittedCell)  {

        Passenger PassengerSelected =  tableView.getSelectionModel().getSelectedItem();
        PassengerSelected.setName((String) edittedCell.getNewValue());

        if(edittedCell.getNewValue().toString().matches("^[\\p{L} .'-]+$")){

            this.Confirm.setDisable(false);
            label.setText("");
            for(Passenger passenger : changes){
                if(passenger.getId()==tableView.getSelectionModel().getSelectedItem().getId()){
                    passenger.setName((edittedCell.getNewValue().toString()));
                }
            }
        }
        else{
            this.Confirm.setDisable(true);
            label.setText("Enter Valid Name");
        }

    }//*

    public void changeLastNameCellEvent(TableColumn.CellEditEvent edittedCell)  {

        Passenger PassengerSelected =  tableView.getSelectionModel().getSelectedItem();
        PassengerSelected.setLastName((String) edittedCell.getNewValue());

        if(edittedCell.getNewValue().toString().matches("^[\\p{L} .'-]+$")){

            this.Confirm.setDisable(false);
            label.setText("");
            for(Passenger passenger : changes){
                if(passenger.getId()==tableView.getSelectionModel().getSelectedItem().getId()){
                    passenger.setLastName((edittedCell.getNewValue().toString()));
                }
            }
        }
        else{
            this.Confirm.setDisable(true);
            label.setText("Enter Valid LastName");
        }

    }//*

    public void changeUserNameCellEvent(TableColumn.CellEditEvent edittedCell) throws SQLException, ClassNotFoundException {

        Passenger PassengerSelected =  tableView.getSelectionModel().getSelectedItem();
        PassengerSelected.setUserName((String) edittedCell.getNewValue());

        if(edittedCell.getNewValue().toString().matches("^(?=.{8,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$") && checkUserName(edittedCell.getNewValue().toString())){

            this.Confirm.setDisable(false);
            label.setText("");
            for(Passenger passenger : changes){
                if(passenger.getId()==tableView.getSelectionModel().getSelectedItem().getId()){
                    passenger.setUserName((edittedCell.getNewValue().toString()));
                }
            }
        }
        else{
            this.Confirm.setDisable(true);
            label.setText("Enter Valid/Unrepeated Username");
        }

    }//*

    public void changePasswordCellEvent(TableColumn.CellEditEvent edittedCell)  {

        Passenger PassengerSelected =  tableView.getSelectionModel().getSelectedItem();
        PassengerSelected.setPassword((String) edittedCell.getNewValue());

        if(edittedCell.getNewValue().toString().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")){

            this.Confirm.setDisable(false);
            label.setText("");
            for(Passenger passenger : changes){
                if(passenger.getId()==tableView.getSelectionModel().getSelectedItem().getId()){
                    passenger.setPassword((edittedCell.getNewValue().toString()));
                }
            }
        }
        else{
            this.Confirm.setDisable(true);
            label.setText("Enter Valid Password");
        }

    }//*

    public void changePhoneCellEvent(TableColumn.CellEditEvent edittedCell)  {

        Passenger PassengerSelected =  tableView.getSelectionModel().getSelectedItem();
        PassengerSelected.setPhoneNumber((String) edittedCell.getNewValue());

        if(edittedCell.getNewValue().toString().matches("^(\\+98|0)?9\\d{9}$")){

            this.Confirm.setDisable(false);
            label.setText("");
            for(Passenger passenger : changes){
                if(passenger.getId()==tableView.getSelectionModel().getSelectedItem().getId()){
                    passenger.setPhoneNumber((edittedCell.getNewValue().toString()));
                }
            }
        }
        else{
            this.Confirm.setDisable(true);
            label.setText("Enter Valid Phone number");
        }

    }//*

    public void changeEmailCellEvent(TableColumn.CellEditEvent edittedCell)  {

        Passenger PassengerSelected =  tableView.getSelectionModel().getSelectedItem();
        PassengerSelected.setEmail((String) edittedCell.getNewValue());

        if(edittedCell.getNewValue().toString().matches("^(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])$")){

            this.Confirm.setDisable(false);
            label.setText("");
            for(Passenger passenger : changes){
                if(passenger.getId()==tableView.getSelectionModel().getSelectedItem().getId()){
                    passenger.setEmail((edittedCell.getNewValue().toString()));
                }
            }
        }
        else{
            this.Confirm.setDisable(true);
            label.setText("Enter Valid Email");
        }

    }//*

    public void changeCreditCellEvent(TableColumn.CellEditEvent edittedCell)  {

        Passenger PassengerSelected =  tableView.getSelectionModel().getSelectedItem();
        PassengerSelected.setCredit((Double) edittedCell.getNewValue());

        if(edittedCell.getNewValue().toString().matches("(\\d+\\.\\d+)")){

            this.Confirm.setDisable(false);
            label.setText("");
            for(Passenger passenger : changes){
                if(passenger.getId()==tableView.getSelectionModel().getSelectedItem().getId()){
                    passenger.setCredit(Double.parseDouble(edittedCell.getNewValue().toString()));
                }
            }
        }
        else{
            this.Confirm.setDisable(true);
            label.setText("Enter Valid Credit");
        }

    }//*

    public void changeFlightIdCellEvent(TableColumn.CellEditEvent edittedCell) throws SQLException, ClassNotFoundException {



        Passenger PassengerSelected =  tableView.getSelectionModel().getSelectedItem();
        PassengerSelected.setFlightid((Integer) edittedCell.getNewValue());

        if(checkFlightId(Integer.parseInt(edittedCell.getNewValue().toString()))){

            this.Confirm.setDisable(false);
            label.setText("");
            for(Passenger passenger : changes){
                if(passenger.getId()==tableView.getSelectionModel().getSelectedItem().getId()){
                    passenger.setFlightid(Integer.parseInt(edittedCell.getNewValue().toString()));
                }
            }
        }
        else{
            this.Confirm.setDisable(true);
            label.setText("Enter Valid flight id");
        }

    }//*




    private boolean checkUserName(String username) throws SQLException, ClassNotFoundException {
        boolean flag = true;
        for(Passenger passenger : getPassengers()){
            if(passenger.getUserName().equalsIgnoreCase(username)){
                flag  = false;
            }
        }
        return flag;
    }

    private boolean checkFlightId(int id) throws SQLException, ClassNotFoundException {

        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection ;
        connection = databaseHandler.getConnection();

        boolean flag = false;
        for(Flight flight : databaseHandler.ReadFlights()){
            if(flight.getId()==id){
                flag  = true;
            }
        }
        return flag;
    }

    private ObservableList<Passenger> getPassengers() throws SQLException, ClassNotFoundException {
        ObservableList<Passenger> passengers = FXCollections.observableArrayList();
        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection ;
        connection = databaseHandler.getConnection();

        for(Passenger passenger : databaseHandler.ReadPassengers()){
            passengers.add(passenger);
            changes.add(passenger);

        }
        if(passengers.size()==0){
            this.Delete.setDisable(true);

        }
        else{
            this.Delete.setDisable(false);

        }
        return passengers;

    }
}
