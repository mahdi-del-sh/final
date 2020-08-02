package sample.controller.employee.FlightAndPlane.Flight.View;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import sample.Database.DatabaseHandler;

import java.sql.Connection;
import java.sql.SQLException;

public class TicketView {




    @FXML
    private JFXTextField Price;

    @FXML
    private JFXTextField Penalty;

    @FXML
    private JFXTextField ID;

    @FXML
    private JFXButton Set;

    @FXML
    private JFXButton Show;

    @FXML
    private Label label;

    @FXML
    private JFXButton Confirm;

    DatabaseHandler databaseHandler = new DatabaseHandler();
    Connection connection  = databaseHandler.getConnection();

    public TicketView() throws SQLException, ClassNotFoundException {
    }


    @FXML
    void initialize() {




        Set.setOnAction(event -> {
            Penalty.setText("");
            Price.setText("");
            label.setText("");

            DatabaseHandler databaseHandler = new DatabaseHandler();
            Connection connection ;
            try {
                connection = databaseHandler.getConnection();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if(checkTicketId()){
                    this.Confirm.setDisable(false);
                }

                else{
                    label.setText("Id not Found");
                    this.Confirm.setDisable(true);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        });

        Confirm.setOnAction(event -> {
            if(Price.getText().matches("(\\d+\\.\\d+)") && Penalty.getText().matches("(\\d+\\.\\d+)")){
                try {

                    databaseHandler.UpdateTicket(Integer.parseInt(ID.getText()) , Double.parseDouble(Price.getText()) , Double.parseDouble( Penalty.getText()));
                    label.setText("Changed Successfully!");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            else{
                label.setText("EnterValid Price");
            }
        });

    }

    public boolean checkTicketId() throws SQLException {
        boolean flag  = false;

        for(int i = 0  ; i  <  databaseHandler.ReadTicket().size() ; i++){
            if(databaseHandler.ReadTicket().get(i).getId() == Integer.parseInt(ID.getText())){
                flag = true ;
                Price.setText(String.valueOf(databaseHandler.ReadTicket().get(i).getTicketPrice()));
                Penalty.setText(String.valueOf(databaseHandler.ReadTicket().get(i).getPenalty()));

            }
        }
        return flag;
    }
}
