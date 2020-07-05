import sample.model.Passenger;
import sample.model.Ticket;

public class Test {
    public static void main(String[] args) {

        Passenger passenger =  new Passenger();
        passenger.setName("mahdi");
        passenger.setLastName("Delavaran");
        passenger.setId(9873113);
        passenger.setEmail("mahdi.delavaran.sh.ss@gmail.com");
        passenger.setPassword("mahdi9873113");
        passenger.setUserName("Mahdi_del");
        passenger.setPhoneNumber("09166425420");
        passenger.setCredit(150000.00);

        passenger.Show(passenger);



    }
}
