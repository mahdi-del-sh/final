package sample.model;
import sample.Main;
import sample.model.ShowAble;

import java.util.ArrayList;
import java.util.logging.Level;

public class Flight  implements ShowAble {

    public enum FlightStatus{
        flying, //0
        done,   //1
        undone  //2
    }

    private int Id;
    private String Origin;
    private String Destination;
    private String FlightsDate;
    private String FlightsTime;
    private int Sold_Tickets_Number;
    private double FlightDuration ;
    private FlightStatus flightStatus ;
    private String status  = "";
    private String PassengerListString = "" ;
    private int idflight;
    private int airplaneId ;
    private int ticketId;
    private ArrayList<Passenger> PassengersList = new ArrayList<>();

    public String getStatus() {
        return status;
    }

    public void setStatus() {
        if(flightStatus.equals(FlightStatus.flying)){
            status = "flying";
    }

        else if(flightStatus.equals(FlightStatus.done)){
            status = "done";
        }

        else if(flightStatus.equals(FlightStatus.undone)){
            status = "undone";
        }
    }

    public void SetStatus(String s){
        this.status = s;

        if(s.equalsIgnoreCase("flying")){
            flightStatus = FlightStatus.flying;
        }

        else if(s.equalsIgnoreCase("done")){
            flightStatus = FlightStatus.done;
        }

        else if(s.equalsIgnoreCase("undone")){
            flightStatus = FlightStatus.undone;
        }
    }

    public String getPassengerListString() {
        return PassengerListString;
    }

    public void SetPassengerListString(){

        for (int i = 0 ; i < PassengersList.size() ; i++){
            PassengerListString += PassengersList.get(i).getId() + "  ";
        }
    }


    public int getIdflight() {
        return idflight;
    }

    public void setIdflight(int idflight) {
        this.idflight = idflight;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticket) {
        this.ticketId = ticket;
    }

    public int getAirplaneId() {
        return airplaneId;
    }

    public void setAirplaneId(int airplaneId) {
        this.airplaneId = airplaneId;
    }

    public int getId() {
        return Id;
    }

    public String getOrigin() {
        return Origin;
    }

    public void setOrigin(String origin) {
        Origin = origin;
    }

    public String getDestination() {
        return Destination;
    }

    public void setDestination(String destination) {
        Destination = destination;
    }

    public String getFlightsDate() {
        return FlightsDate;
    }

    public void setFlightsDate(String flightsDate) {
        FlightsDate = flightsDate;
    }

    public String getFlightsTime() {
        return FlightsTime;
    }

    public void setFlightsTime(String flightsTime) {
        FlightsTime = flightsTime;
    }

    public int getSold_Tickets_Number() {
        return Sold_Tickets_Number;
    }

    public void setSold_Tickets_Number(int sold_Tickets_Number) {
        Sold_Tickets_Number = sold_Tickets_Number;
    }

    public double getFlightDuration() {
        return FlightDuration;
    }

    public void setFlightDuration(double flightDuration) {
        FlightDuration = flightDuration;
    }

    public FlightStatus getFlightStatus() {
        return flightStatus;
    }

    public void setFlightStatus(FlightStatus flightStatus) {
        this.flightStatus = flightStatus;
    }

    public ArrayList<Passenger> getPassengersList() {
        return PassengersList;
    }

    public void AddPassengersList( Passenger passenger) {
        PassengersList.add(passenger);
    }

    public void show(){}

    public void Show(Flight flight){

        System.out.println("id:              "+flight.getId());
        System.out.println("Airplane         "+flight.getAirplaneId());
        System.out.println("Ticket           "+flight.getTicketId());
        System.out.println("Origin           "+flight.getOrigin());
        System.out.println("Destination      "+flight.getDestination());
        System.out.println("FlightDate       "+flight.getFlightsDate());
        System.out.println("FlightTime       "+flight.getFlightsTime());
        System.out.println("SoldTicketsNumber"+flight.getSold_Tickets_Number());
        for(Passenger passenger : PassengersList){
            System.out.println(passenger.getName()+" "+passenger.getLastName()+" "+passenger.getId());
        }
        System.out.println("FlightDuration   "+flight.getFlightDuration());
        System.out.println("Flight status    "+flight.getFlightStatus());
    }

}
