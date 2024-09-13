package Controller;

import Model.Country;
import Config.NeighborRelations;
import Model.Player;
import View.BoardView;
import View.CountryView;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class BoardController {

    BoardView boardView;

    String turn;
    private String phase = "Set Soldiers";
    String lastPhase;

    Map<String, Country> allCountries = new HashMap<>();
    Map<String, CountryView> allCountryViews = new HashMap<>();
    Map<String, String[]> countryNeighbors = new HashMap<>();

    public final String boardChoice;
    public final int numOfPlayers;
    public final boolean missionsEnabled;
    private Player playerOne;
    private Player playerTwo;
    private Player playerThree;
    private Player playerFour;
    private Player currentPlayer;
    int currentPlayerIndex = 0;

    public Player[] allPlayers;

    public FightController fightController;
    public SendArmyController sendArmyController;
    public CardWindowController cardWindowController;

    // TODO: enable neutral territories;
    public BoardController(String boardChoice, int numOfPlayers, String playerOneName, String playerTwoName, String playerThreeName, String playerFourName,
                           Color playerOneColor, Color playerTwoColor, Color playerThreeColor, Color playerFourColor, boolean missionsEnabled) {
        this.boardChoice = boardChoice;
        this.numOfPlayers = numOfPlayers;
        this.missionsEnabled = missionsEnabled;

        createPlayers(numOfPlayers, playerOneName, playerTwoName, playerThreeName, playerFourName, playerOneColor, playerTwoColor, playerThreeColor, playerFourColor);
    }

    public void createPlayers(int numOfPlayers, String playerOneName, String playerTwoName, String playerThreeName, String playerFourName,
                              Color playerOneColor, Color playerTwoColor, Color playerThreeColor, Color playerFourColor) {
        this.playerOne = new Player(playerOneName, playerOneColor);
        this.playerTwo = new Player(playerTwoName, playerTwoColor);
        this.playerThree = new Player(playerThreeName, playerThreeColor);
        this.playerFour = new Player(playerFourName, playerFourColor);
        this.currentPlayer = playerOne;
        switch (numOfPlayers) {
            case 2: allPlayers = new Player[] {this.playerOne, this.playerTwo}; break;
            case 3: allPlayers = new Player[] {this.playerOne, this.playerTwo, this.playerThree}; break;
            case 4: allPlayers = new Player[] {this.playerOne, this.playerTwo, this.playerThree, this.playerFour}; break;
        }

        addInitialSoldiers();
    }

    public void addInitialSoldiers() {
        int initialSoldiers = switch (numOfPlayers) {
            case 2 -> 20;
            case 3 -> 25;
            case 4 -> 30;
            default -> 0;
        };
        for (Player player : allPlayers) {
            player.addSoldiers(initialSoldiers);
        }
    }

    public void createBoardView() {
        System.out.println(this.playerOne.getPlayerColor());
        System.out.println(this.playerTwo.getPlayerColor());
        boardView = new BoardView(this.boardChoice, allCountries, this, allCountryViews);
        boardView.setVisible(true);
        boardView.setCurrentPhaseLabel(getPhase() + ": " + currentPlayer.getSoldiers() + " Soldier(s) left");
        turn = this.playerOne.getName() + "'s Turn";
        boardView.setPlayerTurnLabel(turn);

        setCountryNeighbors(boardChoice);

        this.fightController = new FightController(this, boardView);
        this.sendArmyController = new SendArmyController(this, boardView);
        this.cardWindowController = new CardWindowController(this, boardView);
    }

    public String getPhase() {
        return phase;
    }
    public void setPhase(String newPhase) {
        phase = newPhase;
    }
    public Player getCurrentPlayer() {
        return allPlayers[currentPlayerIndex];
    }
    public void setCurrentPlayer(){
        currentPlayerIndex = (currentPlayerIndex + 1) % allPlayers.length;
        currentPlayer = allPlayers[currentPlayerIndex];
    }
    public Player getPlayerOne() {
        return this.playerOne;
    }
    public Player getPlayerTwo() {
        return this.playerTwo;
    }
    public Player getPlayerThree() {
        return this.playerThree;
    }
    public Player getPlayerFour() {
        return this.playerFour;
    }

    // Depending on the board, sets the all other countries which a country can attack or send soldiers to
    public void setCountryNeighbors(String boardChoice) {
        switch (boardChoice) {
            case "board1" -> NeighborRelations.addCountryNeighbors1(countryNeighbors);
            case "board2" -> NeighborRelations.addCountryNeighbors2(countryNeighbors);
            case "board3" -> NeighborRelations.addCountryNeighbors3(countryNeighbors);
            case "board4" -> NeighborRelations.addCountryNeighbors4(countryNeighbors);
        }
    }

    // Highlights all neighbors of a country
    public void showHideNeighbors(String countryName, boolean show) {
        String[] allNeighbors = countryNeighbors.get(countryName);

        for (String neighbor : allNeighbors) {
            allCountryViews.get(neighbor).setHighlight(show);
        }
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

    // Logic for the first phase, where both players choose and fill their starting countries
    public void placeSoldiers(Country country, CountryView view) {
        if(turn.equals(this.currentPlayer.getName() + "'s Turn") && (country.getSoldiersInside() == 0 || allCountriesFilled())) {
            country.setOwner(this.currentPlayer);
            this.currentPlayer.removeSoldiers(1);
            country.addSoldiersInside(1);
            view.setSoldierLabel("Soldiers: " + country.getSoldiersInside());
            view.setBackgroundColor(this.currentPlayer.getPlayerColor());
            view.setSoldierIcons(country.getSoldiersInside());
            setCurrentPlayer();
            turn = this.currentPlayer.getName() + "'s Turn";
            boardView.setPlayerTurnLabel(turn);
            boardView.setCurrentPhaseLabel(getPhase() + ": " + currentPlayer.getSoldiers() + " Soldier(s) left");
        }

        if(checkIfArmiesPlaced()) {
            setPhase("Attack Phase");
            boardView.setCurrentPhaseLabel(getPhase());
            boardView.endTurnButton.setEnabled(true);
        }
    }

    // Setting or Unsetting an attacking and defending country for the Attack Phase
    public void attackPhase(Country country, CountryView view) {
        if(this.fightController.getAttackingCountry() == null && country.getOwner() == this.currentPlayer) {
            this.fightController.setAttackingCountry(country);
            this.fightController.setAttackingCountryView(view);
            view.setBackgroundColor(Color.CYAN);
        } else if (this.fightController.getAttackingCountry() != null && this.fightController.getAttackingCountry().getName().equals(country.getName())) {
            this.fightController.setAttackingCountry(null);
            this.fightController.setAttackingCountryView(null);
            view.setBackgroundColor(this.currentPlayer.getPlayerColor());
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
            if(this.fightController.defendingCountry.getOwner() != null) {
                view.setBackgroundColor(this.fightController.defendingCountry.getOwner().getPlayerColor());
            } else  {
                view.setBackgroundColor(null);
            }
            this.fightController.setDefendingCountry(null);
            this.fightController.setDefendingCountryView(null);
            boardView.attackButton.setEnabled(false);
        }
    }

    public void playerSetCardTroops(Country country, CountryView view, Player player) {
        player.removeSoldiers(1);
        country.addSoldiersInside(1);
        view.setSoldierLabel("Soldiers: " + country.getSoldiersInside());
        view.setSoldierIcons(country.getSoldiersInside());
        if(player.getSoldiers() == 0) {
            setPhase(lastPhase);
            boardView.setCurrentPhaseLabel(getPhase());
        } else {
            boardView.setCurrentPhaseLabel(player.getName() + ": Set " + player.getSoldiers() + " Soldier(s)");
        }
    }

    // Setting / Unsetting a sending and receiving country and opening the Send Armies Window
    public void fortificationPhase(Country country, CountryView view) {
        if(this.sendArmyController.getSendingCountry() == null &&
                country.getSoldiersInside() > 1 &&
                country.getOwner() == this.currentPlayer) {
            this.sendArmyController.setSendingCountry(country);
            this.sendArmyController.setSendingCountryView(view);
            view.setBackgroundColor(Color.MAGENTA);
        } else if (this.sendArmyController.getSendingCountry() != null && this.sendArmyController.getSendingCountry().getName().equals(country.getName())) {
            this.sendArmyController.setSendingCountry(null);
            this.sendArmyController.setSendingCountryView(null);
            view.setBackgroundColor(this.currentPlayer.getPlayerColor());
/*            if (this.currentPlayer == this.playerOne) {
                view.setBackgroundColor(this.playerOne.getPlayerColor());
            } else {
                view.setBackgroundColor(this.playerTwo.getPlayerColor());
            }*/
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
            view.setSoldierIcons(country.getSoldiersInside());
            boardView.setCurrentPhaseLabel(getPhase() + ": " + this.currentPlayer.getName() + " " + this.currentPlayer.getSoldiers() + " Soldier(s)");

            if(checkIfArmiesPlaced()) {
                setPhase("Attack Phase");
                boardView.setCurrentPhaseLabel(getPhase());
            }
        }
    }

    // Resets chosen countries and changes their color back to their owners color.
    public void resetAttackerDefenderSenderReceiver() {
        if(fightController.getAttackingCountry() != null) {
            allCountryViews.get(fightController.getAttackingCountry().getName()).setBackgroundColor(fightController.getAttackingCountry().getOwner().getPlayerColor());
            fightController.setAttackingCountry(null);
        }
        if(fightController.getDefendingCountry() != null) {
            allCountryViews.get(fightController.getDefendingCountry().getName()).setBackgroundColor(fightController.getDefendingCountry().getOwner().getPlayerColor());
            fightController.setDefendingCountry(null);
        }
        if(sendArmyController.getSendingCountry() != null) {
            allCountryViews.get(sendArmyController.getSendingCountry().getName()).setBackgroundColor(sendArmyController.getSendingCountry().getOwner().getPlayerColor());
            sendArmyController.setSendingCountry(null);
        }
        if(sendArmyController.getReceivingCountry() != null) {
            allCountryViews.get(sendArmyController.getReceivingCountry().getName()).setBackgroundColor(sendArmyController.getReceivingCountry().getOwner().getPlayerColor());
            sendArmyController.setReceivingCountry(null);
        }
    }

    public void endPhase() {
        resetAttackerDefenderSenderReceiver();
        if(getPhase().equals("Attack Phase")) {
            setPhase("Fortification Phase");
            boardView.setCurrentPhaseLabel("Fortifications: " + this.sendArmyController.getFortifications() + " Left");
            boardView.setEndTurnButtonText("End Turn");
        }
        else if(getPhase().equals("Fortification Phase")) {
            endTurn();
        }
    }

    public boolean checkIfArmiesPlaced() {
        boolean allPlaced = true;
        for (Player player : allPlayers) {
            if (player.getSoldiers() != 0) {
                allPlaced = false;
                break;
            }
        }
        return allPlaced;
    }

    public void checkIfTooManyCards() {
        // If player has more than 5 cards, he is forced to use them
        if (this.currentPlayer.getAllCardsSize() > 5) {
            JOptionPane.showMessageDialog(boardView, "You can't have more than five cards!");
            cardWindowController.createCardWindowView();
        }
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

    public void endTurn() {
        setCurrentPlayer();
        turn = this.currentPlayer.getName() + "'s Turn";
        boardView.setPlayerTurnLabel(turn);
        setPhase("New Troops Phase");
        getNewTroops(this.currentPlayer);
        boardView.setCurrentPhaseLabel(getPhase() + " " + this.currentPlayer.getName() + " " + this.currentPlayer.getSoldiers() + " Soldier(s)");
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
