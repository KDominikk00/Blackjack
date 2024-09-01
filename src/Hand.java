import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> hand;

    public Hand() {
        this.hand = new ArrayList<>();
    }

    public void addToHand(Card card) {
        hand.add(card);
    }

    public int handValue() {
        int totalValue = 0;
        int aceCount = 0;

        for (Card card : hand) {
            totalValue += card.getBlackjackValue();
            if (card.getValue() == 1) {
                aceCount++;
            }
        }

        while (totalValue > 21 && aceCount > 0) {
            totalValue -= 10;
            aceCount--;
        }

        return totalValue;
    }

    public Card get(int i) {
        return hand.get(i);
    }

    public int size() {
        return hand.size();
    }

    public void clear() {
        hand.clear();
    }

    public void printFirstCard() {
        if (!hand.isEmpty()) {
            System.out.println(hand.get(0) + ", *****");
        }
    }

    public void printCards() {
        for (Card card : hand) {
            System.out.print(card + ", ");
        }
        System.out.println();
    }

    /**
     * Checks if the hand can be split.
     *
     * @return True if the hand has exactly two cards of the same value, false otherwise.
     */
    public boolean canSplit() {
        return hand.size() == 2 && hand.get(0).getValue() == hand.get(1).getValue();
    }

    /**
     * Splits the hand into two separate hands and returns the new hand.
     *
     * @return The new hand created from the split.
     */
    public Hand split() {
        if (!canSplit()) {
            throw new IllegalStateException("Hand cannot be split.");
        }

        Hand newHand = new Hand();
        newHand.addToHand(hand.remove(1));  // Move the second card to the new hand
        return newHand;
    }
}