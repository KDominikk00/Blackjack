![Blackjack](https://i.imgur.com/610hxEW.png)

Welcome to the Blackjack Game! This is a simple, text-based Java implementation of the classic casino card game Blackjack, designed to be played in your terminal or command prompt. 

## Overview

Blackjack, also known as 21, is a popular card game where the goal is to beat the dealer by getting a hand value as close to 21 as possible without going over. The game offers several options, including hitting, standing, doubling down, and splitting, to make your gameplay experience as engaging as possible.

## Features

- **Play Against the Dealer:** Test your skills against a virtual dealer.
- **Hit, Stand, Double Down, and Split:** Utilize all major Blackjack actions.
- **Blackjack Payout:** Special payout for getting a Blackjack (an Ace and a 10-value card) on your initial deal.
- **Deck Management:** The deck is automatically shuffled and refilled when depleted.

## How to Play

### Starting the Game

1. **Run the Game:**
   - Compile and run the `Main.java` file using your Java IDE or command line.
   
2. **Enter Your Chip Value:**
   - Upon starting, you will be prompted to enter the total value of your chips.

### Playing a Round

1. **Place Your Bet:**
   - Enter your bet amount. Ensure you don’t bet more than your available chips.

2. **Initial Deal:**
   - You will receive two cards, and the dealer will receive two cards (one face up and one face down).

3. **Game Actions:**
   - **Hit:** Draw an additional card.
   - **Stand:** End your turn with your current hand.
   - **Double Down:** Double your bet, receive one additional card, and then stand. (Only available on your initial move.)
   - **Split:** If you have two cards of the same value, you can split them into two separate hands, each with its own bet. You will play each hand separately.

4. **Dealer's Turn:**
   - After you complete your turn, the dealer will reveal their face-down card and draw additional cards until their hand value is 17 or higher.

5. **Determine the Winner:**
   - If you beat the dealer without busting (going over 21), you win. If the dealer busts, you win. If both have the same value, it’s a tie. If you bust, the dealer wins.

### Example Interaction
Welcome to Blackjack
Enter the value of all your chips: 100 
Place your bet: 20 

Dealing cards... 

Dealer's cards: Hearts 7, ***** 
Your cards: Diamonds 10, Hearts 6 
The value of your cards is 16

What would you like to do? (Hit, Stand, Double, Split) 

### Notes

- **Card Values:** Number cards are worth their face value, face cards (Jack, Queen, King) are worth 10, and Aces can be worth 1 or 11, depending on which value is more favorable.
- **Handling Aces:** If you have an Ace and a 10-value card as your initial hand, you have a Blackjack, which pays out 3:2.

## Installation

1. **Clone the Repository:**

   ```bash
   git clone https://github.com/KDominikk00/Blackjack
   ```

2. **Navigate to Project Directory**

   ```bash
   cd Blackjack/src
   ```

3. **Compile the Java Files**
   ```bash
   javac *.java
   ```

4. **Run the Game**
   ```bash
   j+ava Main
