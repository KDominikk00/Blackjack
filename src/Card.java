public class Card {
    // Constants for card suits
    public static final String HEARTS = "Hearts";
    public static final String DIAMONDS = "Diamonds";
    public static final String CLUBS = "Clubs";
    public static final String SPADES = "Spades";

    private int value;
    private String suit;

    /**
     * Constructor to initialize a card with a suit and value.
     *
     * @param suit  The suit of the card (e.g., Hearts, Diamonds).
     * @param value The value of the card (1-13).
     */
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

    /**
     * Returns a string representation of the card's display value.
     *
     * @return The display value of the card.
     */
    public String getDisplayValue() {
        switch (value) {
            case 1: return "ACE";
            case 11: return "JACK";
            case 12: return "QUEEN";
            case 13: return "KING";
            default: return String.valueOf(value);
        }
    }

    /**
     * Returns the Blackjack value of the card.
     *
     * @return The Blackjack value (11 for Ace by default, otherwise 10 for face cards, or the card value itself).
     */
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