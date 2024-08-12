package Controller;

import Model.Country;
import View.BoardView;
import View.CountryView;
import View.SendArmyView;

import java.util.HashMap;
import java.util.Map;

public class SendArmyController {
    Country sendingCountry;
    CountryView sendingCountryView;

    Country receivingCountry;
    CountryView receivingCountryView;

    int fortifications = 3;

    private BoardController boardController;
    private BoardView boardView;

    public SendArmyController(BoardController boardController, BoardView boardView) {
        this.boardController = boardController;
        this.boardView = boardView;
    }

    public Country getSendingCountry() {
        return sendingCountry;
    }
    public void setSendingCountry(Country country) {
        sendingCountry = country;
    }
    public Country getReceivingCountry() {
        return receivingCountry;
    }
    public void setReceivingCountry(Country country) {
        receivingCountry = country;
    }
    public void setSendingCountryView(CountryView countryView) {
        sendingCountryView = countryView;
    }
    public void setReceivingCountryView(CountryView countryView) {
        receivingCountryView = countryView;
    }
    public int getFortifications() {
        return fortifications;
    }
    public void setFortifications(int num) {
        fortifications = num;
    }

    public void createSendArmyView() {
        new SendArmyView(this, this.boardView).createSendWindow();
    }

    public void sendArmy(int soldiers) {
        sendingCountry.setSoldiersSend(soldiers);
        fortifyCountry();
    }

    // Updates the countries after soldiers have been sent around
/*    public void fortifyCountry() {
        allCountries.get(receivingCountry.getName()).addSoldiersInside(sendingCountry.getSoldiersSend());
        allCountries.get(sendingCountry.getName()).removeSoldiersInside(sendingCountry.getSoldiersSend());
        allCountries.get(sendingCountry.getName()).resetSoldiersSend();

        allCountries.get(sendingCountry.getName()).updateCountryPanel();
        allCountries.get(receivingCountry.getName()).updateCountryPanel();

        // After the set amount of sending, the players turn end automatically
        fortifications--;
        if(fortifications == 0) {
            boardController.endTurn();
        } else {
            boardView.setCurrentPhaseLabel("Fortifications: " + fortifications + " Left");
        }
        sendingCountry = null;
        receivingCountry = null;
    }*/

    public void fortifyCountry() {
        receivingCountry.addSoldiersInside(sendingCountry.getSoldiersSend());
        sendingCountry.removeSoldiersInside(sendingCountry.getSoldiersSend());
        sendingCountry.resetSoldiersSend();


        System.out.println(sendingCountry.getName());
        System.out.println(sendingCountryView.country.getName());
        System.out.println(receivingCountry.getName());
        System.out.println(receivingCountryView.country.getName());

        sendingCountryView.updateCountryPanel();
        receivingCountryView.updateCountryPanel();

        // After the set amount of sending, the players turn end automatically
        fortifications--;
        if(fortifications == 0) {
            boardController.endTurn();
        } else {
            boardView.setCurrentPhaseLabel("Fortifications: " + fortifications + " Left");
        }
        sendingCountry = null;
        receivingCountry = null;
    }
}
