package Controller;

import Model.Country;
import Model.NeighborRelation;
import Model.Player;
import View.BoardView;
import View.CountryView;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class BoardController {

    BoardView boardView;

    String turn = "Player One's Turn";
    String phase = "Set Soldiers";
    String lastPhase;

    Map<String, Country> allCountries = new HashMap<>();
    Map<String, CountryView> allCountryViews = new HashMap<>();
    Map<String, String[]> countryNeighbors = new HashMap<>();

    private final String boardChoice;
    private Player playerOne;
    private Player playerTwo;
    private Player currentPlayer;
    public FightController fightController;
    private SendArmyController sendArmyController;


    public BoardController(String boardChoice) {
        this.boardChoice = boardChoice;
        this.playerOne = new Player("Player One");
        this.playerTwo = new Player("Player Two");
        this.currentPlayer = playerOne;
    }

    public void createBoardView() {
        boardView = new BoardView(this.boardChoice, allCountries, this, allCountryViews);
        boardView.setVisible(true);
        boardView.setPlayerTurnLabel(turn);
        boardView.setCurrentPhaseLabel(phase);

        setCountryNeighbors(boardChoice);

        this.fightController = new FightController(this, boardView);
        this.sendArmyController = new SendArmyController(this, boardView);

    }
    
    public void setCountryNeighbors(String boardChoice) {
        switch (boardChoice) {
            case "board1" -> NeighborRelation.addCountryNeighbors1(countryNeighbors);
            case "board2" -> NeighborRelation.addCountryNeighbors2(countryNeighbors);
            case "board3" -> NeighborRelation.addCountryNeighbors3(countryNeighbors);
        }
    }

    // Highlights a country's neighbors, which can be attacked
    public void showHideNeighbors(String countryName, boolean show) {
        String[] allNeighbors = countryNeighbors.get(countryName);

        for (String neighbor : allNeighbors) {
            allCountryViews.get(neighbor).setHighlight(show);
        }
    }

    public String getPhase() {
        return phase;
    }
    public void setPhase(String newPhase) {
        phase = newPhase;
    }
    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }
    public void setCurrentPlayer(Player newCurrentPlayer) {
        this.currentPlayer = newCurrentPlayer;
    }
    public Player getPlayerOne() {
        return this.playerOne;
    }
    public Player getPlayerTwo() {
        return this.playerTwo;
    }

    // Checks if all available countries have been chosen at beginning of game
    public boolean allCountriesFilled() {
        boolean allFilled = true;
        for (Country c : allCountries.values()) {
            if (c.getOwner() == null) {
                allFilled = false;
                break;
            }
        }
        return allFilled;
    }

    public boolean checkIfNeighbor(String countryName, String potentialNeighbor) {
        boolean neighborCheck = false;
        String[] neighbors = countryNeighbors.get(countryName);
        for (String neighbor : neighbors) {
            if(neighbor.equals(potentialNeighbor)) {
                neighborCheck = true;
                break;
            }
        }
        return neighborCheck;
    }

    public void placeSoldiers(Country country, CountryView view) {
        if(turn.equals("Player One's Turn") && (country.getSoldiersInside() == 0 || allCountriesFilled())){
            country.setOwner(this.playerOne);
            this.playerOne.removeSoldiers(1);
            country.addSoldiersInside(1);
            view.setSoldierLabel("Soldiers: " + country.getSoldiersInside());
            view.setBackgroundColor(Color.YELLOW);
            turn = turn.equals("Player One's Turn") ? "Player Two's Turn" : "Player One's Turn";
            boardView.setPlayerTurnLabel(turn);
            this.currentPlayer = this.currentPlayer == this.playerOne ? this.playerTwo : this.playerOne;

        } else if (turn.equals("Player Two's Turn") && (country.getSoldiersInside() == 0 || allCountriesFilled())) {
            country.setOwner(this.playerTwo);
            this.playerTwo.removeSoldiers(1);
            country.addSoldiersInside(1);
            view.setSoldierLabel("Soldiers: " + country.getSoldiersInside());
            view.setBackgroundColor(Color.PINK);
            turn = turn.equals("Player One's Turn") ? "Player Two's Turn" : "Player One's Turn";
            boardView.setPlayerTurnLabel(turn);
            this.currentPlayer = this.currentPlayer == this.playerOne ? this.playerTwo : this.playerOne;

        }

        // Switches to next phase when the players have no more soldiers to place
        if(this.playerTwo.getSoldiers() == 0) {
            phase = "Attack Phase";
            boardView.setCurrentPhaseLabel(phase);
            boardView.endTurnButton.setEnabled(true);
        }
    }

    // Setting / Unsetting an attacking and defending country for the Attack Phase
    public void attackPhase(Country country, CountryView view) {
        if(this.fightController.getAttackingCountry() == null && country.getOwner() == this.currentPlayer && country.getSoldiersInside() > 1) {
            this.fightController.setAttackingCountry(country);
            this.fightController.setAttackingCountryView(view);
            view.setBackgroundColor(Color.CYAN);
        } else if (this.fightController.getAttackingCountry() != null && this.fightController.getAttackingCountry().getName().equals(country.getName())) {
            this.fightController.setAttackingCountry(null);
            this.fightController.setAttackingCountryView(null);
            if (this.currentPlayer == this.playerOne) {
                view.setBackgroundColor(Color.YELLOW);
            } else {
                view.setBackgroundColor(Color.PINK);
            }
            boardView.attackButton.setEnabled(false);
        } else if (this.fightController.getAttackingCountry() != null &&
                this.fightController.getDefendingCountry() == null &&
                checkIfNeighbor(this.fightController.getAttackingCountry().getName(), country.getName()) &&
                this.fightController.getAttackingCountry().getOwner() != country.getOwner()) {
            this.fightController.setDefendingCountry(country);
            this.fightController.setDefendingCountryView(view);
            view.setBackgroundColor(Color.RED);
            boardView.attackButton.setEnabled(true);
        } else if (this.fightController.getDefendingCountry() != null && this.fightController.getDefendingCountry().getName().equals(country.getName())) {
            this.fightController.setDefendingCountry(null);
            this.fightController.setDefendingCountryView(null);
            if (this.currentPlayer == this.playerOne) {
                view.setBackgroundColor(Color.PINK);
            } else {
                view.setBackgroundColor(Color.YELLOW);
            }
            boardView.attackButton.setEnabled(false);
        }
    }

    public void playerOneSetCardsPhase() {
        this.playerOne.cardsToSoldiers();
        boardView.setPlayerOneCardsButtonText("Player One Cards: " + this.playerOne.getCards());
        lastPhase = phase;
        phase = "Player One: Set Soldiers";
        boardView.setCurrentPhaseLabel("Player One: Set " + this.playerOne.getSoldiers() + " Soldier(s)");
    }

    public void playerTwoSetCardsPhase() {
        this.playerTwo.cardsToSoldiers();
        boardView.setPlayerTwoCardsButtonText("Player Two Cards: " + this.playerTwo.getCards());
        lastPhase = phase;
        phase = "Player Two: Set Soldiers";
        boardView.setCurrentPhaseLabel("Player Two: Set " + this.playerOne.getSoldiers() + " Soldier(s)");
    }

    public void playerOneSetCardTroops(Country country, CountryView view) {
        this.playerOne.removeSoldiers(1);
        country.addSoldiersInside(1);
        view.setSoldierLabel("Soldiers: " + country.getSoldiersInside());
        if(this.playerOne.getSoldiers() == 0) {
            phase = lastPhase;
            boardView.setCurrentPhaseLabel(phase);
        } else {
            boardView.setCurrentPhaseLabel("Player One: Set " + this.playerOne.getSoldiers() + " Soldier(s)");
        }
    }

    public void playerTwoSetCardTroops(Country country, CountryView view) {
        this.playerTwo.removeSoldiers(1);
        country.addSoldiersInside(1);
        view.setSoldierLabel("Soldiers: " + country.getSoldiersInside());
        if(this.playerTwo.getSoldiers() == 0) {
            phase = lastPhase;
            boardView.setCurrentPhaseLabel(phase);
        } else {
            boardView.setCurrentPhaseLabel("Player Two: Set " + this.playerTwo.getSoldiers() + " Soldier(s)");
        }
    }

    // Setting / Unsetting a sending and receiving country and opening the Send Armies Window
    public void fortificationPhase(Country country, CountryView view) {
        if(this.sendArmyController.getSendingCountry() == null && country.getSoldiersInside() > 1) {
            this.sendArmyController.setSendingCountry(country);
            this.sendArmyController.setSendingCountryView(view);
            view.setBackgroundColor(Color.MAGENTA);
        } else if (this.sendArmyController.getSendingCountry() != null && this.sendArmyController.getSendingCountry().getName().equals(country.getName())) {
            this.sendArmyController.setSendingCountry(null);
            this.sendArmyController.setSendingCountryView(null);
            if (this.currentPlayer == this.playerOne) {
                view.setBackgroundColor(Color.YELLOW);
            } else {
                view.setBackgroundColor(Color.PINK);
            }
        } else if (this.sendArmyController.getReceivingCountry() == null &&
                this.sendArmyController.getSendingCountry() != null &&
                checkIfNeighbor(this.sendArmyController.getSendingCountry().getName(), country.getName()) &&
                this.sendArmyController.getSendingCountry().getOwner() == country.getOwner()) {
            this.sendArmyController.setReceivingCountry(country);
            this.sendArmyController.setReceivingCountryView(view);
            this.sendArmyController.createSendArmyView();
        }
    }

    // Sets new Soldiers at beginning of turn and switches to other player, when first one is done
    public void setNewTroops(Country country, CountryView view) {
        if(country.getOwner() == this.currentPlayer) {
            country.addSoldiersInside(1);
            this.currentPlayer.removeSoldiers(1);
            view.setSoldierLabel("Soldiers: " + country.getSoldiersInside());
            boardView.setCurrentPhaseLabel(phase + " " + this.currentPlayer.getName() + " " + this.currentPlayer.getSoldiers() + " Soldier(s)");

            if(this.currentPlayer == this.playerOne && this.playerOne.getSoldiers() == 0 && this.playerTwo.getSoldiers() != 0) {
                this.currentPlayer = this.playerTwo;
                turn = this.currentPlayer.getName() + "'s Turn";
                boardView.setPlayerTurnLabel(turn);
                boardView.setCurrentPhaseLabel(phase + " " + this.currentPlayer.getName() + " " + this.currentPlayer.getSoldiers() + " Soldier(s)");
            }
            else if(this.currentPlayer == this.playerTwo && this.playerTwo.getSoldiers() == 0 && this.playerOne.getSoldiers() != 0) {
                this.currentPlayer = this.playerOne;
                turn = this.currentPlayer.getName() + "'s Turn";
                boardView.setPlayerTurnLabel(turn);
                boardView.setCurrentPhaseLabel(phase + " " + this.currentPlayer.getName() + " " + this.currentPlayer.getSoldiers() + " Soldier(s)");
            }
            else if(this.playerOne.getSoldiers() == 0 && this.playerTwo.getSoldiers() == 0) {
                this.currentPlayer = this.currentPlayer == this.playerOne ? this.playerTwo : this.playerOne;
                turn = this.currentPlayer.getName() + "'s Turn";
                boardView.setPlayerTurnLabel(turn);
                phase = "Attack Phase";
                boardView.setCurrentPhaseLabel(phase);
            }
        }
    }

    public void endPhase() {
        if(phase.equals("Attack Phase")) {
            phase = "Fortification Phase";
            boardView.setCurrentPhaseLabel("Fortifications: " + this.sendArmyController.getFortifications() + " Left");
            boardView.setEndTurnButtonText("End Turn");
        }
        else if(phase.equals("Fortification Phase")) {
            endTurn();
        }
    }

    public void checkIfTooManyCards() {
        // If player has more than 5 cards, he is forced to use them
        if (this.currentPlayer.getCards() > 5) {
            if (this.currentPlayer == this.playerOne) {
                this.playerOne.cardsToSoldiers();
                boardView.setPlayerOneCardsButtonText("Player One Cards: " + this.playerOne.getCards());
                phase = "Player One: Set Soldiers";
                boardView.setCurrentPhaseLabel("Player One: Set " + this.playerOne.getSoldiers() + " Soldier(s)");
            }
            if (this.currentPlayer == this.playerTwo) {
                this.playerTwo.cardsToSoldiers();
                boardView.setPlayerTwoCardsButtonText("Player Two Cards: " + this.playerTwo.getCards());
                phase = "Player Two: Set Soldiers";
                boardView.setCurrentPhaseLabel("Player Two: Set " + this.playerTwo.getSoldiers() + " Soldier(s)");
            }
        }
    }

    public int checkOwningContinents(Player player) {
        int continentsOwned = 0;
        int contA = 0;
        int contB = 0;
        int contC = 0;
        int contD = 0;

        for (Country c : allCountries.values()) {
            if(c.getOwner() == player) {
                if(c.getContinent().equals("A")) {
                    contA++;
                    if(contA == 6) continentsOwned++;
                }
                if(c.getContinent().equals("B")) {
                    contB++;
                    if(contB == 6) continentsOwned++;
                }
                if(c.getContinent().equals("C")) {
                    contC++;
                    if(contC == 6) continentsOwned++;
                }
                if(c.getContinent().equals("D")) {
                    contD++;
                    if(contD == 6) continentsOwned++;
                }
            }
        }
        return continentsOwned;
    }

    // Calculates the new Troops at the beginning of a turn, depending on owned countries and continents
    public void getNewTroops(Player player) {
        int newTroops = 0;
        int countriesOwned = 0;
        int continentsOwned = checkOwningContinents(player);

        for (Country c : allCountries.values()) {
            if(c.getOwner() == player) countriesOwned++;
        }
        if((countriesOwned / 3) < 3) {
            newTroops += 3;
        } else {
            newTroops += (int)Math.floor(countriesOwned / 3);
        }
        newTroops += continentsOwned * 3;

        player.addSoldiers(newTroops);
    }

    public void endTurn() {
        this.currentPlayer = this.currentPlayer == this.playerOne ? this.playerTwo : this.playerOne;
        turn = this.currentPlayer.getName() + "'s Turn";
        boardView.setPlayerTurnLabel(turn);
        phase = "New Troops Phase";
        getNewTroops(this.playerOne);
        getNewTroops(this.playerTwo);
        boardView.setCurrentPhaseLabel(phase + " " + this.currentPlayer.getName() + " " + this.currentPlayer.getSoldiers() + " Soldier(s)");
        this.sendArmyController.setFortifications(3);
    }

    public void checkWin() {
        boolean win = true;
        for(Country country : allCountries.values()) {
            if (country.getOwner() != currentPlayer) {
                win = false;
                break;
            }
        }
        if(win) {
            JOptionPane.showMessageDialog(boardView, currentPlayer.getName() + " has won the game!");
        }
    }


}
