package Controller;

import Model.Card;
import Model.Player;
import View.BoardView;
import View.CardWindowView;

import java.util.List;

public class CardWindowController {

    private final BoardController boardController;
    private final BoardView boardView;

    public CardWindowController(BoardController boardController, BoardView boardView) {
        this.boardController = boardController;
        this.boardView = boardView;
    }

    public void createCardWindowView() {
        new CardWindowView(boardView, this).createCardWindow();
    }

    public boolean checkThreeCardCombination(String type) {
        Player currentPlayer = boardController.getCurrentPlayer();
        List<Card> allCards = currentPlayer.getCards();
        int count = 0;
        for (Card card : allCards) {
            if (card.getCardType().equals(type)) {
                count++;
            }
        }
        return count >= 3;
    }

    public boolean checkOneOfEachCard() {
        Player currentPlayer = boardController.getCurrentPlayer();
        List<Card> allCards = currentPlayer.getCards();
        int infantry = 0;
        int cavalry = 0;
        int artillery = 0;
        for (Card card : allCards) {
            switch (card.getCardType()) {
                case "Infantry": infantry++; break;
                case "Cavalry": cavalry++; break;
                case "Artillery": artillery++; break;
            }
        }
        return (infantry > 0 && cavalry > 0 && artillery > 0);
    }

    public void playerSetCardsPhase(String cardType) {
        Player currentPlayer = boardController.getCurrentPlayer();
        if(cardType.equals("oneOfEachCard")) {
            currentPlayer.removeOneOfEachCards();
        }
        else {
            currentPlayer.removeThreeSameCards(cardType);
        }
        currentPlayer.cardsToSoldiers(cardType);
        boardController.lastPhase = boardController.getPhase();
        boardController.setPhase(currentPlayer.getName() + ": Set Soldiers");
        boardView.setCurrentPhaseLabel(currentPlayer.getName() + ": Set " + currentPlayer.getSoldiers() + " Soldier(s)");
        if(currentPlayer == boardController.getPlayerOne()) {
            boardView.setPlayerOneCardsButtonText(currentPlayer.getName() + " Cards: " + currentPlayer.getAllCardsSize());
        }
        if(currentPlayer == boardController.getPlayerTwo()) {
            boardView.setPlayerTwoCardsButtonText(currentPlayer.getName() + " Cards: " + currentPlayer.getAllCardsSize());
        }
        if(currentPlayer == boardController.getPlayerThree()) {
            boardView.setPlayerThreeCardsButtonText(currentPlayer.getName() + " Cards: " + currentPlayer.getAllCardsSize());
        }
        if(currentPlayer == boardController.getPlayerFour()) {
            boardView.setPlayerFourCardsButtonText(currentPlayer.getName() + " Cards: " + currentPlayer.getAllCardsSize());
        }
    }

/*    // Start the extra phase for each player after they clicked their card button with 3 or more cards
    public void playerOneSetCardsPhase() {
        this.playerOne.cardsToSoldiers();
        boardView.setPlayerOneCardsButtonText(this.playerOne.getName() + " Cards: " + this.playerOne.getAllCardsSize());
        lastPhase = getPhase();
        setPhase(this.playerOne.getName() + ": Set Soldiers");
        boardView.setCurrentPhaseLabel(this.playerOne.getName() + ": Set " + this.playerOne.getSoldiers() + " Soldier(s)");
    }

    public void playerTwoSetCardsPhase() {
        this.playerTwo.cardsToSoldiers();
        boardView.setPlayerTwoCardsButtonText(this.playerTwo.getName() + " Cards: " + this.playerTwo.getAllCardsSize());
        lastPhase = getPhase();
        setPhase(this.playerTwo.getName() + ": Set Soldiers");
        boardView.setCurrentPhaseLabel(this.playerTwo.getName() + ": Set " + this.playerTwo.getSoldiers() + " Soldier(s)");
    }

    public void playerThreeSetCardsPhase() {
        this.playerThree.cardsToSoldiers();
        boardView.setPlayerThreeCardsButtonText(this.playerThree.getName() + " Cards: " + this.playerThree.getAllCardsSize());
        lastPhase = getPhase();
        setPhase(this.playerThree.getName() + ": Set Soldiers");
        boardView.setCurrentPhaseLabel(this.playerThree.getName() + ": Set " + this.playerThree.getSoldiers() + " Soldier(s)");
    }

    public void playerFourSetCardsPhase() {
        this.playerFour.cardsToSoldiers();
        boardView.setPlayerFourCardsButtonText(this.playerFour.getName() + " Cards: " + this.playerFour.getAllCardsSize());
        lastPhase = getPhase();
        setPhase(this.playerFour.getName() + ": Set Soldiers");
        boardView.setCurrentPhaseLabel(this.playerFour.getName() + ": Set " + this.playerFour.getSoldiers() + " Soldier(s)");
    }*/
}
