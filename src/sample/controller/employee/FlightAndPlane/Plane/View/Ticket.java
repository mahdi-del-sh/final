package sample.controller.employee.FlightAndPlane.Plane.View;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import sample.Database.DatabaseHandler;

import java.sql.Connection;
import java.sql.SQLException;

public class Ticket {


    @FXML
    private JFXTextField ID;

    @FXML
    private JFXTextField Price;

    @FXML
    private JFXTextField Penalty;

    @FXML
    private Label label;

    @FXML
    private JFXButton Set;



    DatabaseHandler databaseHandler = new DatabaseHandler();
    Connection connection  = databaseHandler.getConnection();

    public Ticket() throws SQLException, ClassNotFoundException {
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
                }

                else{
                    label.setText("Id not Found");
                }
            } catch (SQLException e) {
                e.printStackTrace();
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
