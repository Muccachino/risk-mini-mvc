package Model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player {
    private final String name;
    private int soldiers;
    private List<Card> cards;
    private final Color playerColor;

    public Player(String name, Color playerColor) {
        this.name = name;
        this.soldiers = 0;
        this.cards = new ArrayList<>();
        this.playerColor = playerColor;
    }

    public String getName() {
        return this.name;
    }

    public Color getPlayerColor() {
        return this.playerColor;
    }

    public int getSoldiers() {
        return this.soldiers;
    }

    public void addSoldiers(int soldiers) {
        this.soldiers += soldiers;
    }

    public void removeSoldiers(int soldiers) {
        this.soldiers -= soldiers;
    }

    public int getAllCardsSize() {
        return this.cards.size();
    }

    public List<Card> getCards() {
        return this.cards;
    }

    public void addCards() {
        int randomCardIndex = new Random().nextInt(3);
        this.cards.add(new Card(Card.allCardTypes[randomCardIndex]));
    }

    // Funktionen hinzugefügt, welche sich auf die zusätzlichen Kartentypen beziehen
    public void removeThreeSameCards(String type) {
        int count = 0;

        while(count < 3) {
            for (Card card : this.cards) {
                if (card.getCardType().equals(type)) {
                    this.cards.remove(card);
                    break;
                }
            }
            count++;
        }
    }

    public void removeOneOfEachCards() {
        Card infantryRemoved = null;
        Card cavalryRemoved = null;
        Card artilleryRemoved = null;

        while (infantryRemoved == null && cavalryRemoved == null && artilleryRemoved == null) {
            for (Card card : this.cards) {
                if (card.getCardType().equals("Infantry") && infantryRemoved == null) {
                    infantryRemoved = card;
                }
                if (card.getCardType().equals("Cavalry") && cavalryRemoved == null) {
                    cavalryRemoved = card;
                }
                if (card.getCardType().equals("Artillery") && artilleryRemoved == null) {
                    artilleryRemoved = card;
                }
            }
        }
        this.cards.remove(infantryRemoved);
        this.cards.remove(cavalryRemoved);
        this.cards.remove(artilleryRemoved);
    }

    public void cardsToSoldiers(String type) {
        switch (type) {
            case "Infantry":
                removeThreeSameCards("Infantry");
                addSoldiers(4);
                break;
            case "Cavalry":
                removeThreeSameCards("Cavalry");
                addSoldiers(5);
                break;
            case "Artillery":
                removeThreeSameCards("Artillery");
                addSoldiers(6);
                break;
            case "oneOfEachCard":
                removeOneOfEachCards();
                addSoldiers(5);
                break;
        }
    }
}
