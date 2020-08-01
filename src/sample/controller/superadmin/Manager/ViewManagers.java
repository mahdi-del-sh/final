package sample.controller.superadmin.Manager;

import com.jfoenix.controls.JFXButton;
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
import sample.Database.DatabaseHandler;
import sample.controller.LoginMenuController;
import sample.controller.manager.FlightAndPlane.Flight.Add.Add;
import sample.model.Flight;
import sample.model.Manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class ViewManagers {

    @FXML
    private TableView<Manager> tableView;

    @FXML private TableColumn<Manager , Integer> IdColumn;

    @FXML private TableColumn<Manager , String> FirstNameColumn;
    @FXML private TableColumn<Manager , String> LastNameColumn;
    @FXML private TableColumn<Manager , String> UserNameColumn;
    @FXML private TableColumn<Manager , String> PasswordColumn;
    @FXML private TableColumn<Manager , String> PhoneColumn;
    @FXML private TableColumn<Manager , String> AddressColumn;
    @FXML private TableColumn<Manager , String> EmailColumn;
    @FXML private TableColumn<Manager , Double> SalaryColumn;

    @FXML
    private JFXButton HomeBTN;

    @FXML
    private JFXButton Delete;

    @FXML
    private JFXButton Confirm;

    @FXML
    private javafx.scene.control.Label Label;

    ArrayList<Manager> changes = new ArrayList<>();
    LoginMenuController loginMenuController = new LoginMenuController();

    DatabaseHandler databaseHandler = new DatabaseHandler();
    Connection connection =  databaseHandler.getConnection();

    public ViewManagers() throws SQLException, ClassNotFoundException {
    }


    @FXML
    void initialize() throws SQLException, ClassNotFoundException {

        HomeBTN.setOnAction(event -> {
            loginMenuController.ChangeWindow(HomeBTN , "/sample/view/superadmin/Manager/Panel.fxml" , "Manager Management");
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
            for(Manager manager  :changes){
                databaseHandler.UpdateManager(manager.getName() , manager.getLastName() , manager.getUserName() , manager.getPassword() , manager.getPhoneNumber() , manager.getAddress() ,  manager.getEmail() , manager.getSalary() , manager.getId());
            }
            Label.setText("Changed successfully");
        });


        IdColumn.setCellValueFactory(new PropertyValueFactory<Manager , Integer>("Id"));
        FirstNameColumn.setCellValueFactory(new PropertyValueFactory<Manager , String>("name"));
        LastNameColumn.setCellValueFactory(new PropertyValueFactory<Manager , String>("lastName"));
        UserNameColumn.setCellValueFactory(new PropertyValueFactory<Manager , String>("userName"));
        PasswordColumn.setCellValueFactory(new PropertyValueFactory<Manager , String>("password"));
        PhoneColumn.setCellValueFactory(new PropertyValueFactory<Manager , String>("phoneNumber"));
        AddressColumn.setCellValueFactory(new PropertyValueFactory<Manager , String>("address"));
        EmailColumn.setCellValueFactory(new PropertyValueFactory<Manager , String>("Email"));
        SalaryColumn.setCellValueFactory(new PropertyValueFactory<Manager , Double>("salary"));

        tableView.setItems(getMangers());

        tableView.setEditable(true);

        FirstNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        LastNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        UserNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        PasswordColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        PhoneColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        AddressColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        EmailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        SalaryColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));


        tableView.getSelectionModel().setCellSelectionEnabled(true);
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }


    //edit :
    public void changeNameCellEvent(TableColumn.CellEditEvent edittedCell)  {

        Manager FlightSelected =  tableView.getSelectionModel().getSelectedItem();
        FlightSelected.setName((String) edittedCell.getNewValue());

        if(edittedCell.getNewValue().toString().matches("^[a-zA-Z]*$")){

            this.Confirm.setDisable(false);
            Label.setText("");
            for(Manager flight : changes){
                if(flight.getId()==tableView.getSelectionModel().getSelectedItem().getId()){
                    flight.setName((edittedCell.getNewValue().toString()));
                }
            }
        }
        else{
            this.Confirm.setDisable(true);
            Label.setText("Enter Valid Name");
        }

    }//*

    public void changeLastNameCellEvent(TableColumn.CellEditEvent edittedCell)  {

        Manager FlightSelected =  tableView.getSelectionModel().getSelectedItem();
        FlightSelected.setLastName((String) edittedCell.getNewValue());

        if(edittedCell.getNewValue().toString().matches("^[a-zA-Z]*$")){

            this.Confirm.setDisable(false);
            Label.setText("");
            for(Manager flight : changes){
                if(flight.getId()==tableView.getSelectionModel().getSelectedItem().getId()){
                    flight.setLastName((edittedCell.getNewValue().toString()));
                }
            }
        }
        else{
            this.Confirm.setDisable(true);
            Label.setText("Enter LastName");
        }

    }//*

    public void changeUserNameCellEvent(TableColumn.CellEditEvent edittedCell) throws SQLException {

        Manager FlightSelected =  tableView.getSelectionModel().getSelectedItem();
        FlightSelected.setUserName((String) edittedCell.getNewValue());

        if(edittedCell.getNewValue().toString().matches("^(?=.{8,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$") && databaseHandler.CheckUsername(edittedCell.getNewValue().toString())){

            this.Confirm.setDisable(false);
            Label.setText("");
            for(Manager flight : changes){
                if(flight.getId()==tableView.getSelectionModel().getSelectedItem().getId()){
                    flight.setUserName((edittedCell.getNewValue().toString()));
                }
            }
        }
        else{
            this.Confirm.setDisable(true);
            Label.setText("Enter Valid Username");
        }

    }//*

    public void changePasswordCellEvent(TableColumn.CellEditEvent edittedCell)  {

        Manager FlightSelected =  tableView.getSelectionModel().getSelectedItem();
        FlightSelected.setPassword((String) edittedCell.getNewValue());

        if(edittedCell.getNewValue().toString().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")){

            this.Confirm.setDisable(false);
            Label.setText("");
            for(Manager flight : changes){
                if(flight.getId()==tableView.getSelectionModel().getSelectedItem().getId()){
                    flight.setPassword((edittedCell.getNewValue().toString()));
                }
            }
        }
        else{
            this.Confirm.setDisable(true);
            Label.setText("Enter Valid Password");
        }

    }//*

    public void changePhoneCellEvent(TableColumn.CellEditEvent edittedCell)  {

        Manager FlightSelected =  tableView.getSelectionModel().getSelectedItem();
        FlightSelected.setPhoneNumber((String) edittedCell.getNewValue());

        if(edittedCell.getNewValue().toString().matches("^(\\+98|0)?9\\d{9}$")){

            this.Confirm.setDisable(false);
            Label.setText("");
            for(Manager flight : changes){
                if(flight.getId()==tableView.getSelectionModel().getSelectedItem().getId()){
                    flight.setPhoneNumber((edittedCell.getNewValue().toString()));
                }
            }
        }
        else{
            this.Confirm.setDisable(true);
            Label.setText("Enter Valid Phone  number");
        }

    }//*

    public void changeAddressCellEvent(TableColumn.CellEditEvent edittedCell)  {

        Manager FlightSelected =  tableView.getSelectionModel().getSelectedItem();
        FlightSelected.setAddress((String) edittedCell.getNewValue());

        if(edittedCell.getNewValue().toString().matches("^[\\p{L} .'-]+$")){

            this.Confirm.setDisable(false);
            Label.setText("");
            for(Manager flight : changes){
                if(flight.getId()==tableView.getSelectionModel().getSelectedItem().getId()){
                    flight.setAddress((edittedCell.getNewValue().toString()));
                }
            }
        }
        else{
            this.Confirm.setDisable(true);
            Label.setText("Enter Valid Address");
        }

    }//*

    public void changeEmailCellEvent(TableColumn.CellEditEvent edittedCell)  {

        Manager FlightSelected =  tableView.getSelectionModel().getSelectedItem();
        FlightSelected.setEmail((String) edittedCell.getNewValue());

        if(edittedCell.getNewValue().toString().matches("^(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])$")){

            this.Confirm.setDisable(false);
            Label.setText("");
            for(Manager flight : changes){
                if(flight.getId()==tableView.getSelectionModel().getSelectedItem().getId()){
                    flight.setEmail((edittedCell.getNewValue().toString()));
                }
            }
        }
        else{
            this.Confirm.setDisable(true);
            Label.setText("Enter Valid Email");
        }

    }//*

    public void changeSalaryCellEvent(TableColumn.CellEditEvent edittedCell){

        Manager FlightSelected =  tableView.getSelectionModel().getSelectedItem();
        FlightSelected.setSalary((Double) edittedCell.getNewValue());

        if(edittedCell.getNewValue().toString().matches("(\\d+\\.\\d+)")){

            this.Confirm.setDisable(false);
            Label.setText("");
            for(Manager flight : changes){
                if(flight.getId()==tableView.getSelectionModel().getSelectedItem().getId()){
                    flight.setSalary(((Double) edittedCell.getNewValue()));
                }
            }
        }
        else{
            this.Confirm.setDisable(true);
            Label.setText("Enter Valid Salary");
        }


    }




    private void deleteButtonPushed() throws SQLException, ClassNotFoundException {


        ObservableList<Manager> selectedRows, allFlights;
        allFlights = tableView.getItems();

        selectedRows = tableView.getSelectionModel().getSelectedItems();



        databaseHandler.DeleteManager(tableView.getSelectionModel().getSelectedItem().getId());
        for (Manager flight : selectedRows)
            allFlights.remove(flight);



    }

    private ObservableList<Manager> getMangers() throws SQLException, ClassNotFoundException {

        ObservableList<Manager> flights = FXCollections.observableArrayList();
        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection ;
        connection = databaseHandler.getConnection();

        for(Manager flight : databaseHandler.ReadManagers()){
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
