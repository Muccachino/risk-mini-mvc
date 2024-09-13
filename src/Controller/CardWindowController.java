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
}
