public class Card {
    private int value;
    private String suit;

    public Card(String suit, int value) {
        this.suit = suit;
        this.value = value;
    }

    public String getSuit() {
        return this.suit;
    }

    public int getValue() {
        return this.value;
    }

    public String getDisplayValue() {
        switch (value) {
            case 1:
                return "ACE";
            case 11:
                return "JACK";
            case 12:
                return "QUEEN";
            case 13:
                return "KING";
            default:
                return String.valueOf(value);
        }
    }

    public int getBlackjackValue() {
        if (value == 1) {
            return 11;  // Default to ACE being 11, adjust later if needed
        }
        return Math.min(value, 10);  // Face cards are worth 10
    }

    @Override
    public String toString() {
        return suit + " " + getDisplayValue();
    }
}