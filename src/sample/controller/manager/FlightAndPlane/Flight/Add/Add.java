package sample.controller.manager.FlightAndPlane.Flight.Add;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import sample.Database.DatabaseHandler;
import sample.controller.LoginMenuController;
import sample.model.Airplane;
import sample.model.Flight;
import sample.model.Ticket;

public class Add {

    @FXML
    private JFXTextField PriceTXF;

    @FXML
    private Label PriceLBL;

    @FXML
    private JFXTextField PenaltyTXF;

    @FXML
    private Label PenaltyLBL;

    @FXML
    private JFXButton HomeBTN;

    @FXML
    private JFXTextField PlaneIdTXF;

    @FXML
    private JFXButton ConfirmBTN;

    @FXML
    private JFXTextField DestinationTXF;

    @FXML
    private JFXTextField OriginTXF;

    @FXML
    private JFXTextField DateTXF;

    @FXML
    private JFXTextField HourTXF;

    @FXML
    private Label EmailLBL;

    @FXML
    private Label HourLBL;

    @FXML
    private Label DestinationLBL;

    @FXML
    private Label DateLBL;

    @FXML
    private Label PlaneIdLBL;

    @FXML
    private Label label;

    @FXML
    private Label OriginLBL;

    @FXML
    private JFXTextField S_T_NTXF;

    @FXML
    private Label S_T_NLBL;

    @FXML
    private JFXTextField DurationTXF;

    @FXML
    private Label DurationLBL;

    @FXML
    private JFXTextField StatusTXF;

    @FXML
    private Label StatusLBL;

    LoginMenuController loginMenuController = new LoginMenuController();
    @FXML
    void initialize() {

        HomeBTN.setOnAction(event -> {
            loginMenuController.ChangeWindow(HomeBTN , "/sample/view/manager/FlightAndPlane/Panel.fxml" , "Flight And Plane Management");
        });

        ConfirmBTN.setOnAction(event -> {

            StatusLBL.setText("");
            DurationLBL.setText("");
            S_T_NLBL.setText("");
            PlaneIdLBL.setText("");
            OriginLBL.setText("");
            HourLBL.setText("");
            DateLBL.setText("");
            DestinationLBL.setText("");
            label.setText("");

            boolean flags [] = new boolean[10];

            if(PlaneIdTXF.getText().equalsIgnoreCase("")){
                PlaneIdLBL.setText("Please Enter Plane Id");
                flags[0] = false;
            }
            else {
                flags[0] = true;
            }

            if(OriginTXF.getText().equalsIgnoreCase("")){
                OriginLBL.setText("Please Enter Origin");
                flags[1] = false;
            }
            else {
                flags[1] = true;
            }

            if(DestinationTXF.getText().equalsIgnoreCase("")){
                DestinationLBL.setText("Please Enter Destination");
                flags[2] = false;
            }
            else {
                flags[2] = true;
            }


            if(DateTXF.getText().equalsIgnoreCase("")){
                DateLBL.setText("Please Enter Date");
                flags[3] = false;
            }
            else {
                flags[3] = true;
            }


            if(HourTXF.getText().equalsIgnoreCase("")){
                HourLBL.setText("Please Enter Hour ");
                flags[4] = false;
            }
            else {
                flags[4] = true;
            }



            if(S_T_NTXF.getText().equalsIgnoreCase("")){
                S_T_NLBL.setText("Please Enter S T N");
                flags[5] = false;
            }
            else {
                flags[5] = true;
            }



            if(DurationTXF.getText().equalsIgnoreCase("")){
                DurationLBL.setText("Please Enter Duration ");
                flags[6] = false;
            }
            else {
                flags[6] = true;
            }



            if(StatusTXF.getText().equalsIgnoreCase("")){
                StatusLBL.setText("Please Enter Status");
                flags[7] = false;
            }
            else {
                flags[7] = true;
            }



            flags[8] = true;


            if(PriceTXF.getText().equalsIgnoreCase("")){
                PriceLBL.setText("Please Enter Price");
                flags[9] = false;
            }
            else {
                flags[9] = true;
            }

            boolean flag = true;
            for(boolean i : flags){
                if(i== false){
                    flag = false;
                }
            }

            if(flag) {

                try {
                    if(CheckRegex()){

                        DatabaseHandler databaseHandler = new DatabaseHandler();
                        Connection connection ;
                        try {
                            connection = databaseHandler.getConnection();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        Flight.FlightStatus flightStatus ;

                        if(StatusTXF.getText().equalsIgnoreCase("flying")){
                        flightStatus = Flight.FlightStatus.flying;
                        }
                        else if(StatusTXF.getText().equalsIgnoreCase("done")){
                            flightStatus = Flight.FlightStatus.done;
                        }
                        else{
                            flightStatus = Flight.FlightStatus.undone;
                        }

                        Ticket ticket = new Ticket();
                       // ticket.setPenalty(Double.parseDouble(PenaltyTXF.getText()));
                        ticket.setTicketPrice(Double.parseDouble(PriceTXF.getText()));
                        try {
                            ticket.setId(databaseHandler.newTicketId());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        double penalty = 0;
                        if(PenaltyTXF.getText().equalsIgnoreCase("")){
                            penalty = (Double.parseDouble(PriceTXF.getText())/5) ;
                        }
                        else {
                            penalty = Double.parseDouble(PenaltyTXF.getText());
                             ticket.setPenalty(Double.parseDouble(PenaltyTXF.getText()));

                        }

                        // ticket.setId(databaseHandler.ReadTicket().size()+1);
                        try {
                            databaseHandler.AddTicket(ticket.getId() , ticket.getTicketPrice() , penalty);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }


                        try {
                            label.setText("Flight Added !");
                            databaseHandler.AddFlight(Integer.parseInt(PlaneIdTXF.getText()) , ticket.getId() , OriginTXF.getText() , DestinationTXF.getText() , DateTXF.getText() , HourTXF.getText() , Integer.parseInt(S_T_NTXF.getText()) , Double.parseDouble(DurationTXF.getText()) , flightStatus );
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }


                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public boolean CheckRegex() throws SQLException, ClassNotFoundException {
        boolean flag = true;

        boolean [] flags = new boolean[10];



        if(PlaneIdTXF.getText().matches("[0-9]+") && Integer.parseInt(PlaneIdTXF.getText() ) >= 50000 && FindPlane(Integer.parseInt(PlaneIdTXF.getText()))){
            flags[0] = true;
        }
        else{
            PlaneIdLBL.setText("Enter Valid Id");
            flags[0] = false;
        }


        if(OriginTXF.getText().matches("^[a-zA-Z]*$")){
            flags[1] = true;
        }
        else {
            OriginLBL.setText("Enter Valid Origin");
            flags[1] = false;
        }

        if(DestinationTXF.getText().matches("^[a-zA-Z]*$")){
            flags[2] = true;
        }
        else{
            DestinationLBL.setText("Enter Valid Destination");
            flags[2] = false;
        }

        if(DateTXF.getText().matches("([1-9]|[12][0-9]|3[01])-(0?[1-9]|1[012])-((13|20)\\d\\d)")){
            flags[3] = true;
        }
        else {
            flags[3] = false;
            DateLBL.setText("Enter Valid Date");
        }

        if(HourTXF.getText().matches("(\\d\\d:\\d\\d)")){
            flags[4] = true;
        }
        else{
            flags[4] = false;
            HourLBL.setText("Please Enter Valid Hour");
        }

        if(S_T_NTXF.getText().matches("(?<=\\s|^)\\d+(?=\\s|$)")&& Integer.parseInt(S_T_NTXF.getText()) > 0 && Integer.parseInt(S_T_NTXF.getText()) < 400){
            flags[5] = true;
        }
        else{
            flags[5] = false;
            S_T_NLBL.setText("Please Enter Valid S_T_N");
        }

        if(DurationTXF.getText().matches("(\\d+\\.\\d+)")){
            flags[6] = true;
        }
        else{
            flags[6] = false;
            DurationLBL.setText("Enter Double number");
        }

        if(StatusTXF.getText().equalsIgnoreCase("done") || StatusTXF.getText().equalsIgnoreCase("undone") || StatusTXF.getText().equalsIgnoreCase("flying") ){
            flags[7] = true;
        }
        else {
            flags[7] = false;
            StatusLBL.setText("Choose done undone flying");
        }

        if(PriceTXF.getText().matches("(\\d+\\.\\d+)")){
            flags[8] = true;
        }
        else{
            flags[8] = false;
            PriceLBL.setText("Price is Double number");
        }

        if(PenaltyTXF.getText().equalsIgnoreCase("")){

            flags[9] = true;

        }
        else {

            if(PenaltyTXF.getText().matches("(\\d+\\.\\d+)")){
                flags[9] = true;
            }
            else{
                flags[9] = false;
                PenaltyLBL.setText("Penalty is Double number");
            }

        }






        for(boolean i : flags)
            if (i==false)flag = false;




        return flag;
    }

    public boolean FindPlane(int id) throws SQLException, ClassNotFoundException {

        boolean flag = false;
        DatabaseHandler databaseHandler = new DatabaseHandler();
        Connection connection ;
        connection = databaseHandler.getConnection();

        for (Airplane airplane : databaseHandler.ReadPlanes()){
            if(airplane.getId()==id){
                flag = true ;
            }
        }
            return flag ;
    }


}
