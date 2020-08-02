package sample.controller.employee.FlightAndPlane.Plane.View;

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
import javafx.util.converter.IntegerStringConverter;
import sample.Database.DatabaseHandler;
import sample.controller.LoginMenuController;
import sample.model.Airplane;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

public class View {

    //table :
    @FXML private TableView<Airplane> tableView;

    @FXML private TableColumn<Airplane , Integer> IdColumn;

    @FXML private TableColumn<Airplane , Integer> CapacityColumn;

    @FXML private TableColumn<Airplane , String> FlightListColumn;



    @FXML
    private JFXButton HomeBTN;

    @FXML
    private JFXButton FlightList;

    @FXML
    private Label ErrorLBL;

    @FXML
    private JFXButton Delete;

    @FXML
    private JFXButton Confirm;

    HashMap<Integer , Integer> changes = new HashMap<>();


    public static int PlaneId;

LoginMenuController loginMenuController = new LoginMenuController();
    @FXML
    void initialize() throws SQLException, ClassNotFoundException {


        FlightList.setOnAction(event -> {
            if(tableView.getSelectionModel().isEmpty()){
                ErrorLBL.setText("Select Row");
            }
            else {
                PlaneId = tableView.getSelectionModel().getSelectedItem().getId();
                loginMenuController.ChangeWindow(HomeBTN , "/sample/view/employee/FlightAndPlane/Plane/View/FlightList.fxml" , "Flight List");
            }
        });

        HomeBTN.setOnAction(event -> {
            loginMenuController.ChangeWindow(HomeBTN , "/sample/view/employee/FlightAndPlane/Panel.fxml", "Flight And Plane Management");
        });

        Confirm.setOnAction(event -> {

            this.Confirm.setDisable(false);

            if(ErrorLBL.getText().equalsIgnoreCase("")){
            DatabaseHandler databaseHandler = new DatabaseHandler();
            Connection connection ;
            try {
                connection = databaseHandler.getConnection();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            for(int i : changes.keySet())
                databaseHandler.UpdatePlane(i , changes.get(i));

            }
            else{
                ErrorLBL.setText("Capacity should be number and between 0 to 400 ");
            }


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

        //set up the columns in table :
        IdColumn.setCellValueFactory(new PropertyValueFactory<Airplane , Integer>("id"));
        CapacityColumn.setCellValueFactory(new PropertyValueFactory<Airplane , Integer>("number_of_seats"));
        FlightListColumn.setCellValueFactory(new PropertyValueFactory<Airplane , String>("FlightListString"));

        //load dummy data :
        tableView.setItems(getPlanes());

        tableView.setEditable(true);
        //TextFieldTableCell.<BMIRecord, Number>forTableColumn(new NumberStringConverter())
        //age.setCellValueFactory(new Callback<CellDataFeatures<Person, Number>, ObservableValue<Number>>() {


        CapacityColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        tableView.getSelectionModel().setCellSelectionEnabled(true);
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }

    //edit :
    public void changeCapacityCellEvent(TableColumn.CellEditEvent edittedCell) throws SQLException, ClassNotFoundException {

        Airplane personSelected =  tableView.getSelectionModel().getSelectedItem();
        personSelected.setNumber_of_seats((Integer) edittedCell.getNewValue());

        if(edittedCell.getNewValue().toString().matches("[0-9]+") && (Integer) edittedCell.getNewValue() >  0 && (Integer) edittedCell.getNewValue() < 400){
            this.Confirm.setDisable(false);
            changes.put(tableView.getSelectionModel().getSelectedItem().getIdplane() , tableView.getSelectionModel().getSelectedItem().getNumber_of_seats() );
            ErrorLBL.setText("");
        }

        else{
            this.Confirm.setDisable(true);
            ErrorLBL.setText("Capacity Should be number and bigger than 0");
        }
    }

    private void deleteButtonPushed() throws SQLException, ClassNotFoundException {

        if(tableView.getItems().size()==0){
            this.Delete.setDisable(true);
        }

        else {
            ObservableList<Airplane> selectedRows, allPlanes;
            allPlanes = tableView.getItems();

            selectedRows = tableView.getSelectionModel().getSelectedItems();

            DatabaseHandler databaseHandler = new DatabaseHandler();
            Connection connection ;
            connection = databaseHandler.getConnection();

            databaseHandler.DeletePlane(tableView.getSelectionModel().getSelectedItem().getIdplane());
for (Airplane airplane : selectedRows)
    allPlanes.remove(airplane);

        }
    }

    private ObservableList<Airplane> getPlanes() throws SQLException, ClassNotFoundException {

        ObservableList<Airplane> Planes = FXCollections.observableArrayList();
        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection ;
        connection = databaseHandler.getConnection();

        for(Airplane airplane : databaseHandler.ReadPlanes()){
          //  airplane.SetFlightListString();
            Planes.add(airplane);

        }
        if(Planes.size()==0){
            this.Delete.setDisable(true);

        }
        else{
            this.Delete.setDisable(false);

        }

        return Planes;
        }

    }


