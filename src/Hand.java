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
        System.out.println(hand.get(0) + ", *****");
    }

    public void printCards() {
        for (Card card : hand) {
            System.out.print(card + ", ");
        }
    }
}