package sample.controller.manager.Message;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import sample.Database.DatabaseHandler;
import sample.controller.LoginMenuController;
import sample.model.Employee;
import sample.model.Passenger;

import java.sql.Connection;
import java.sql.SQLException;

public class ViewPassenger {
    @FXML
    private TableView<Passenger> tableView;

    @FXML private TableColumn<Passenger , Integer> IdColumn;

    @FXML
    private JFXButton HomeBTN;

    @FXML
    private JFXTextArea Message;

    @FXML
    private Label label;

    @FXML
    private JFXButton show;

    @FXML
    private JFXButton Delete;


    LoginMenuController loginMenuController = new LoginMenuController();
    DatabaseHandler databaseHandler = new DatabaseHandler();
    Connection connection = databaseHandler.getConnection();

    public ViewPassenger() throws SQLException, ClassNotFoundException {
    }

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        this.Delete.setDisable(true);
        Message.setText("");
        label.setText("");

        HomeBTN.setOnAction(event -> {
            loginMenuController.ChangeWindow(HomeBTN , "/sample/view/manager/Message/Panel.fxml" , "Message Management ");
        });

        show.setOnAction(event -> {
            Message.setText("");
            label.setText("");

            if(!tableView.getSelectionModel().isEmpty()){
                this.Delete.setDisable(false);
                try {

                    for(Passenger passenger : databaseHandler.ReadPassengers()){
                        if (passenger.getId() == tableView.getSelectionModel().getSelectedItem().getId()){

                            if(passenger.getMessage().equalsIgnoreCase("") || passenger.getMessage().equalsIgnoreCase(" ") ){
                                Message.setText("No Message Yet");
                                this.Delete.setDisable(true);
                            }
                            else{
                                this.Delete.setDisable(false);
                                Message.setText(passenger.getMessage());
                            }

                        }
                    }

                }

                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            else{
                this.Delete.setDisable(true);
                label.setText("Select a row ");
            }


        });

        Delete.setOnAction(event -> {
            Message.setText("");
            label.setText("");

            if(!tableView.getSelectionModel().isEmpty()){

                databaseHandler.DeletePassengerMessage(tableView.getSelectionModel().getSelectedItem().getId());
                Message.setText("Message Removed successfully");
            }

            else{
                label.setText("Select a row ");
            }
        });

        IdColumn.setCellValueFactory(new PropertyValueFactory<Passenger , Integer>("id"));
        tableView.setItems(getPassenger());
        tableView.getSelectionModel().setCellSelectionEnabled(true);
    }


    private ObservableList<Passenger> getPassenger() throws SQLException, ClassNotFoundException {
        ObservableList<Passenger> passengers = FXCollections.observableArrayList();

        for(Passenger passenger : databaseHandler.ReadPassengers()){
            //  airplane.SetFlightListString();
            passengers.add(passenger);

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
