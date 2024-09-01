import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards;
    private static final String[] SUITS = {Card.HEARTS, Card.DIAMONDS, Card.CLUBS, Card.SPADES};

    /**
     * Constructor to initialize the deck and shuffle it.
     */
    public Deck() {
        this.cards = new ArrayList<>();
        fillDeck();
    }

    /**
     * Fills the deck with 52 cards, one of each value for each suit, and shuffles it.
     */
    private void fillDeck() {
        cards.clear();  // Clear any existing cards
        for (String suit : SUITS) {
            for (int value = 1; value <= 13; value++) {
                cards.add(new Card(suit, value));
            }
        }
        Collections.shuffle(cards);
    }

    /**
     * Deals a card from the deck. Refills and shuffles the deck if empty.
     *
     * @return The dealt card.
     */
    public Card dealCard() {
        if (cards.isEmpty()) {
            System.out.println("Deck is empty. Refill and shuffle the deck.");
            fillDeck();  // Refill and shuffle the deck if empty
        }
        return cards.remove(cards.size() - 1);
    }

    /**
     * Prints all cards remaining in the deck.
     */
    public void printCards() {
        for (Card card : cards) {
            System.out.print(card + ", ");
        }
        System.out.println();
    }
}