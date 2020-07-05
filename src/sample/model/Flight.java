package sample.model;
import sample.Main;
import sample.model.ShowAble;

import java.util.ArrayList;
import java.util.logging.Level;

public class Flight  implements ShowAble {

   public enum FlightStatus{
        flying,
        done,
        undone
    }


    private int Id;
    private Airplane airplane ;
    private Ticket ticket;
    private String Origin;
    private String Destination;
    private String FlightsDate;
    private String FlightsTime;
    private int Sold_Tickets_Number;
    private ArrayList<Passenger> PassengersList = new ArrayList<>();
    private double FlightDuration ;
    private FlightStatus flightStatus ;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
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

    public void setPassengersList(ArrayList<Passenger> passengersList) {
        PassengersList = passengersList;
    }

    public void show(){}

    public void Show(Flight flight){

        System.out.println("id:              "+flight.getId());
        System.out.println("Airplane         "+flight.getAirplane());
        System.out.println("Ticket           "+flight.getTicket());
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
