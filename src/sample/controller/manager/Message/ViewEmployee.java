package sample.controller.manager.Message;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Database.DatabaseHandler;
import sample.controller.LoginMenuController;
import sample.model.Airplane;
import sample.model.Employee;

public class ViewEmployee {


    @FXML private TableView<Employee> tableView;

    @FXML private TableColumn<Employee , Integer> IdColumn;

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

    public ViewEmployee() throws SQLException, ClassNotFoundException {
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

                    for(Employee employee : databaseHandler.ReadEmployee()){
                        if (employee.getId() == tableView.getSelectionModel().getSelectedItem().getId()){

                            if(employee.getMessage().equalsIgnoreCase("") || employee.getMessage().equalsIgnoreCase(" ") ){
                                Message.setText("No Message Yet");
                                this.Delete.setDisable(true);
                            }
                            else{
                                this.Delete.setDisable(false);
                                Message.setText(employee.getMessage());
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

                databaseHandler.DeleteEmployeeMessage(tableView.getSelectionModel().getSelectedItem().getId());
                Message.setText("Message Removed successfully");
            }

            else{
                label.setText("Select a row ");
            }
        });

        IdColumn.setCellValueFactory(new PropertyValueFactory<Employee , Integer>("id"));
        tableView.setItems(getEmployee());
        tableView.getSelectionModel().setCellSelectionEnabled(true);
    }



    private ObservableList<sample.model.Employee> getEmployee() throws SQLException, ClassNotFoundException {
        ObservableList<Employee> employees = FXCollections.observableArrayList();

        for(Employee employee : databaseHandler.ReadEmployee()){
            //  airplane.SetFlightListString();
            employees.add(employee);

        }
        if(employees.size()==0){
            this.Delete.setDisable(true);

        }
        else{
            this.Delete.setDisable(false);

        }

        return employees;
    }


}
