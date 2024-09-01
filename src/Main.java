import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {
        Deck deck = new Deck();
        Hand playerHand = new Hand();
        Hand dealerHand = new Hand();

        // Initial welcome message and chip input
        System.out.println("Welcome to Blackjack");
        TimeUnit.SECONDS.sleep(1);

        int chips = getChips();
        if (chips <= 0) {
            System.out.println("You don't have any more chips, GAME OVER");
            return;
        }

        while (true) {
            if (chips == 0) {
                System.out.println("You don't have any more chips, GAME OVER");
                break;
            }

            int bet = getBet(chips);
            chips -= bet;

            System.out.println("Dealing cards...");
            TimeUnit.SECONDS.sleep(1);

            // Deal initial cards
            dealerHand.addToHand(deck.dealCard());
            dealerHand.addToHand(deck.dealCard());
            playerHand.addToHand(deck.dealCard());
            playerHand.addToHand(deck.dealCard());

            System.out.println("Dealer's cards: ");
            dealerHand.printFirstCard();
            TimeUnit.SECONDS.sleep(1);

            System.out.println("Your cards: ");
            playerHand.printCards();
            System.out.println("The value of your cards is " + playerHand.handValue());
            TimeUnit.SECONDS.sleep(1);

            if (playerHand.handValue() == 21) {
                System.out.println("Blackjack! You win!");
                chips += bet * 2;
                System.out.println("The value of your chips is now $" + chips);
                playerHand.clear();
                dealerHand.clear();
                continue;
            }

            // Check for split option
            boolean splitAvailable = playerHand.canSplit();
            if (splitAvailable) {
                System.out.println("You have the option to split your hand. Do you want to split? (yes/no)");
                String splitResponse = scanner.nextLine().toLowerCase(Locale.ROOT).trim();
                if (splitResponse.equals("yes")) {
                    // Split the hand
                    Hand secondHand = playerHand.split();
                    System.out.println("Your first hand: ");
                    playerHand.printCards();
                    System.out.println("The value of your first hand is " + playerHand.handValue());
                    System.out.println("Your second hand: ");
                    secondHand.printCards();
                    System.out.println("The value of your second hand is " + secondHand.handValue());

                    // Handle both hands separately
                    handleHand(deck, playerHand, bet, chips);
                    handleHand(deck, secondHand, bet, chips);
                    dealerTurn(deck, dealerHand);
                    determineWinner(playerHand, dealerHand, bet, chips);
                    determineWinner(secondHand, dealerHand, bet, chips);
                    playerHand.clear();
                    dealerHand.clear();
                    secondHand.clear();
                    continue;
                }
            }

            // Player's turn for the main hand
            boolean playerBusted = handleHand(deck, playerHand, bet, chips);
            if (playerBusted) {
                chips -= bet;  // Player loses the bet
                System.out.println("The value of your chips is now $" + chips);
                playerHand.clear();
                dealerHand.clear();
                continue;
            }

            // Dealer's turn
            dealerTurn(deck, dealerHand);

            // Determine the winner
            determineWinner(playerHand, dealerHand, bet, chips);
            playerHand.clear();
            dealerHand.clear();
        }
    }

    private static boolean handleHand(Deck deck, Hand hand, int bet, int chips) throws InterruptedException {
        while (true) {
            System.out.println("What would you like to do? (Hit, Stand, Double)");
            String move = scanner.nextLine().toLowerCase(Locale.ROOT).trim();

            switch (move) {
                case "hit":
                    hand.addToHand(deck.dealCard());
                    System.out.print("Your cards: ");
                    hand.printCards();
                    System.out.println("The value of your cards is: " + hand.handValue());
                    if (hand.handValue() > 21) {
                        System.out.println("BUST! dealer wins.");
                        return true;  // Hand busts
                    }
                    break;
                case "stand":
                    return false;  // End player's turn
                case "double":
                    if (bet * 2 > chips) {
                        System.out.println("You don't have enough chips to double.");
                    } else if (hand.size() == 2) {
                        bet *= 2;
                        System.out.println("Your bet is now $" + bet);
                        hand.addToHand(deck.dealCard());
                        System.out.print("Your cards: ");
                        hand.printCards();
                        System.out.println("The value of your cards is: " + hand.handValue());
                        if (hand.handValue() > 21) {
                            System.out.println("BUST! dealer wins.");
                            return true;  // Hand busts
                        }
                        return false;  // End player's turn after doubling
                    } else {
                        System.out.println("You can only double on your first move.");
                    }
                    break;
                default:
                    System.out.println("Invalid move. Please enter 'hit', 'stand', or 'double'.");
            }
        }
    }

    private static int getChips() {
        while (true) {
            System.out.println("Enter the value of all your chips:");
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric value.");
            }
        }
    }

    private static int getBet(int chips) {
        while (true) {
            System.out.println("Place your bet:");
            try {
                int bet = Integer.parseInt(scanner.nextLine());
                if (bet > chips) {
                    System.out.println("Can't place a bet worth more than all of your chips.");
                } else {
                    return bet;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric value.");
            }
        }
    }

    private static void dealerTurn(Deck deck, Hand dealerHand) throws InterruptedException {
        while (dealerHand.handValue() < 17) {
            dealerHand.addToHand(deck.dealCard());
            System.out.print("Dealer hits. ");
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.print("Dealer stands. ");
        System.out.println();
        System.out.print("Dealer cards: ");
        dealerHand.printCards();
        System.out.println();
    }

    private static void determineWinner(Hand playerHand, Hand dealerHand, int bet, int chips) {
        int playerValue = playerHand.handValue();
        int dealerValue = dealerHand.handValue();

        if (playerValue > 21) {
            System.out.println("You bust! Dealer wins.");
        } else if (dealerValue > 21) {
            System.out.println("Dealer busts! You win!");
            chips += bet * 2;
        } else if (playerValue > dealerValue) {
            System.out.println("You win!");
            chips += bet * 2;
        } else if (playerValue < dealerValue) {
            System.out.println("Dealer wins!");
        } else {
            System.out.println("It's a tie!");
            chips += bet;
        }
        System.out.println("The value of your chips is now $" + chips);
    }
}