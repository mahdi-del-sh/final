package sample.model;
import sample.model.ShowAble;
public class Ticket  implements ShowAble {

    private int id;
    private double ticketPrice;
    private double penalty;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public double getPenalty() {
        return penalty;
    }

    public void setPenalty(double penalty) {
        this.penalty = penalty;
    }

    public void show(){
        Ticket ticket = new Ticket();
        System.out.println(ticket.id);
        System.out.println(ticket.penalty);
        System.out.println(ticket.ticketPrice);
    }

    public void Show(Ticket ticket){
        System.out.println(ticket.id);
        System.out.println(ticket.penalty);
        System.out.println(ticket.ticketPrice);
    }


}
