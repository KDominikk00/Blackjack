import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Deck deck = new Deck();
        Hand playerHand = new Hand();
        Hand dealerHand = new Hand();
        Scanner scanner = new Scanner(System.in);

        // Initial welcome message and chip input
        System.out.println("Welcome to Blackjack");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Enter the value of all your chips");
        int chips = Integer.parseInt(scanner.nextLine());
        TimeUnit.SECONDS.sleep(1);

        // main program logic loop
        while (true) {

            if (chips == 0) {
                System.out.println("You don't have any more chips, GAME OVER");
                return;
            }

            System.out.println("Shuffling cards...");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("Place your bet");
            int bet = Integer.parseInt(scanner.nextLine());
            if (bet > chips) {
                System.out.println("Can't place a bet worth more than all of your chips");
                return;
            }
            chips -= bet;
            System.out.println("You placed a bet of $" + bet);
            TimeUnit.SECONDS.sleep(1);
            System.out.println();

            System.out.println("Dealing first card:");
            System.out.println();
            TimeUnit.SECONDS.sleep(1);

            dealerHand.addToHand(deck.dealCard());
            System.out.print("Dealer's cards: ");
            dealerHand.printCards();
            TimeUnit.SECONDS.sleep(1);

            System.out.println();
            System.out.println();

            playerHand.addToHand(deck.dealCard());
            System.out.print("Your cards: ");
            playerHand.printCards();
            System.out.println();
            System.out.println("The value of your cards is " + playerHand.handValue());
            System.out.println();
            TimeUnit.SECONDS.sleep(1);

            System.out.println("Dealing second card: ");
            System.out.println();
            TimeUnit.SECONDS.sleep(1);

            dealerHand.addToHand(deck.dealCard());
            System.out.print("Dealer's cards: ");
            dealerHand.printFirstCard();
            TimeUnit.SECONDS.sleep(1);

            System.out.println();
            System.out.println();

            playerHand.addToHand(deck.dealCard());
            System.out.print("Your cards: ");
            playerHand.printCards();
            System.out.println();
            System.out.println("The value of your cards is: " + playerHand.handValue());
            System.out.println();
            TimeUnit.SECONDS.sleep(1);

            if (playerHand.handValue() == 21) {
                System.out.println("Blackjack! You win!");
                chips += bet * 2;
                System.out.println("The value of your chips is now " + "$" + chips);
                playerHand.clear();
                dealerHand.clear();
                continue;
            }

            // Player move
            while (true) {
                System.out.println("What would you like to do? (Hit, Stand, Double)");
                String move = scanner.nextLine().toLowerCase(Locale.ROOT).trim();

                if (move.equals("hit")) {
                    playerHand.addToHand(deck.dealCard());
                    System.out.print("Your cards: ");
                    playerHand.printCards();
                    System.out.println();
                    System.out.println("The value of your cards is: " + playerHand.handValue());
                    System.out.println();
                    if (playerHand.handValue() > 21) {
                        System.out.println("BUST! dealer wins.");
                        System.out.println("The value of your chips is now " + "$" + chips);
                        playerHand.clear();
                        dealerHand.clear();
                        System.out.println();
                        break;
                    }
                }

                if (move.equals("stand")) {
                    while (dealerHand.handValue() < 17) {
                        dealerHand.addToHand(deck.dealCard());
                        System.out.print("Dealer hits. ");
                    }

                    if (dealerHand.handValue() > 21) {
                        System.out.println("Dealer BUSTS! You win!");
                        chips += bet * 2;
                        System.out.println("The value of your chips is now " + "$" + chips);
                        playerHand.clear();
                        dealerHand.clear();
                        break;
                    }
                    System.out.print("Dealer stands. ");
                    System.out.println();
                    System.out.print("Dealer cards: ");
                    dealerHand.printCards();
                    System.out.println();
                    determineWinner(playerHand, dealerHand, bet, chips);
                    playerHand.clear();
                    dealerHand.clear();
                    break;
                }

                if (move.equals("double")) {
                    if (bet * 2 > chips) {
                        System.out.println("You don't have enough chips to double");
                    } else if (playerHand.size() == 2) {
                        bet *= 2;
                        System.out.println("Your bet is now $" + bet);
                        TimeUnit.SECONDS.sleep(1);
                        playerHand.addToHand(deck.dealCard());
                        System.out.print("Your cards: ");
                        playerHand.printCards();
                        System.out.println();
                        System.out.println("The value of your cards is: " + playerHand.handValue());
                        System.out.println();
                        TimeUnit.SECONDS.sleep(1);
                        if (playerHand.handValue() > 21) {
                            System.out.println("BUST! dealer wins.");
                            System.out.println("The value of your chips is now " + "$" + chips);
                        } else {
                            while (dealerHand.handValue() < 17) {
                                dealerHand.addToHand(deck.dealCard());
                                System.out.print("Dealer hits. ");
                                TimeUnit.SECONDS.sleep(1);
                            }
                            if (dealerHand.handValue() > 21) {
                                System.out.println("Dealer BUSTS! You win!");
                                chips += bet * 2;
                                System.out.println("The value of your chips is now " + "$" + chips);
                            } else {
                                System.out.println("Dealer stands. ");
                                TimeUnit.SECONDS.sleep(1);
                                System.out.print("Dealer cards: ");
                                dealerHand.printCards();
                                System.out.println();
                                determineWinner(playerHand, dealerHand, bet, chips);
                            }
                        }
                        playerHand.clear();
                        dealerHand.clear();
                        break;
                    }else {
                        System.out.println("You can only double on your first move");
                        return;
                    }
                }
            }
        }
    }

    // Winner determined based on hand comparison
    private static void determineWinner(Hand playerHand, Hand dealerHand, int bet, int chips) {
        int playerValue = playerHand.handValue();
        int dealerValue = dealerHand.handValue();

        if (playerValue > dealerValue) {
            System.out.println("You win!");
            chips += bet * 2;
        } else if (playerValue < dealerValue) {
            System.out.println("Dealer wins!");
        } else {
            System.out.println("It's a tie!");
            chips += bet;
        }
        System.out.println("The value of your chips is now " + "$" + chips);
    }
}