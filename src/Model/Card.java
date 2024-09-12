package Model;

public class Card {
    public static final String[] allCardTypes = new String[] {"Infantry", "Cavalry", "Artillery"};

    private final String cardType;

    public Card(String cardType) {
        this.cardType = cardType;
    }

    public String getCardType() {
        return this.cardType;
    }
}
