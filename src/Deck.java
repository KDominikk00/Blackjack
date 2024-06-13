import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards;

    public Deck() {
        this.cards = new ArrayList<>();
        fillDeck();
    }

    public void fillDeck() {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};

        for (String suit : suits) {
            for (int value = 1; value <= 13; value++) {
                cards.add(new Card(suit, value));
            }
        }
        Collections.shuffle(cards);
    }

    public Card dealCard() {
        Card card = cards.remove(cards.size() - 1);
        return card;
    }

    public void printCards() {
        for (Card card : cards) {
            System.out.println(card);
        }
    }
}