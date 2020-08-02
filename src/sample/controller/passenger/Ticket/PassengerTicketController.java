package sample.controller.passenger.Ticket;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import sample.controller.LoginMenuController;
import sample.controller.passenger.Menu;

public class PassengerTicketController {


    @FXML
    private JFXButton HomeBTN;

    @FXML
    private JFXButton CancelBTN;

    @FXML
    private JFXButton BuyBTN;

    Menu passengerMenuController  = new Menu();

    LoginMenuController loginMenuController = new LoginMenuController();

    @FXML
    void initialize() {

        HomeBTN.setOnAction(event -> {

            passengerMenuController.BackToPassengerMenu(HomeBTN);
        });

        BuyBTN.setOnAction(event -> {
            loginMenuController.ChangeWindow(BuyBTN , "/sample/view/passenger/Ticket/BuyTicket.fxml" , "Ticket Management");
        });

        CancelBTN.setOnAction(event -> {
            loginMenuController.ChangeWindow(CancelBTN , "/sample/view/passenger/Ticket/CancelTicket.fxml" , "Cancel Ticket");
        });

    }
}
