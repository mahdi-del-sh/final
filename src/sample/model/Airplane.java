package sample.model;

import sample.model.ShowAble;

import java.util.ArrayList;

public class Airplane implements ShowAble {

    private int idplane ;
    private int id;
    private int  number_of_seats;
    private ArrayList<Flight> FlightLists = new ArrayList<>();
    private String FlightListString = "" ;


    public Airplane() {
        SetFlightListString();
    }

    public void SetFlightListString(){

        for (int i = 0 ; i < FlightLists.size() ; i++){
            FlightListString += FlightLists.get(i).getId() + "  ";
        }
    }

    public String GetFlightListString(){
        return FlightListString;
    }

    public String getFlightListString() {
        return FlightListString;
    }

    public int getFlightListSize(){
        return FlightLists.size();
    }


    public int getIdplane() {
        return idplane;
    }

    public void setIdplane(int idplane) {
        this.idplane = idplane;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber_of_seats() {
        return number_of_seats;
    }

    public void setNumber_of_seats(int number_of_seats) {
        this.number_of_seats = number_of_seats;
    }

    public ArrayList<Flight> getFlightLists() {
        return FlightLists;
    }

    public void addFlightLists(Flight flight) {
        FlightLists.add(flight);
    }

    public void show(){}


    public void Show(Airplane airplane){
        System.out.println("id                      "+airplane.getId());
        System.out.println("number_od_seats         "+airplane.getNumber_of_seats());
        for(Flight flight : FlightLists){
            System.out.println(flight.getOrigin()+" "+flight.getDestination()+" "+flight.getFlightsTime()+" "+flight.getFlightsDate()+" "+flight.getFlightStatus());
        }
    }

}
