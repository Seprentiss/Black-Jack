package Game;

import java.util.Scanner;

public class BlackJack {

    public  static  void main(String[] args){
        System.out.println("Welcome to BlackJack!");

        //Create playing deck

        Deck playingDeck = new Deck();

        playingDeck.createFullDeck();

        playingDeck.shuffle();

        // Create a deck for the player

        Deck playerCards = new Deck();

        Deck dealerCards = new Deck();

        double playerMoney = 100.00;

        Scanner userInput = new Scanner(System.in);


        // Game loop

        while(playerMoney > 0){
            // play on
            //Take players bet

            System.out.println("You have $" + playerMoney + ", how much would you like to bet?");

            double playerBet = userInput.nextDouble();
            boolean endRound = false;
            if (playerBet > playerMoney) {
                System.out.println("You cannot bet more than you have");
                break;
            }
            // Start Dealing

            System.out.println("Dealing...");

            // Player gets two cards
            playerCards.draw(playingDeck);
            playerCards.draw(playingDeck);

            // Dealer gets two cards
            dealerCards.draw(playingDeck);
            dealerCards.draw(playingDeck);


            while (true){
                System.out.println("Your hand: "+ playerCards.toString());
                System.out.println("Your deck is value is: " + playerCards.cardsValue());

                // Display dealer deck

                System.out.println("Dealer hand: " + dealerCards.getCard(0).toString()+ " and [Hidden]");

                // What does the player do?

                System.out.println("Would you like to (1) Hit or (2) stand");
                int response = userInput.nextInt();
                if(response == 1){
                    playerCards.draw(playingDeck);
                    System.out.println("You drew a: " + playerCards.getCard(playerCards.deckSize()-1).toString());
               // Bust if over 21
                    if(playerCards.cardsValue() > 21){
                        System.out.println("Bust. Hand value at:" + playerCards.cardsValue());
                        playerMoney -= playerBet;

                        endRound = true;

                        break;
                    }

                }

                if(response == 2){
                    break;
                }

            }// end of while

            //Reveal dealers cards

            System.out.println("Dealer's Cards:" + dealerCards.toString());
            // check if dealer has more points than the player

            if(dealerCards.cardsValue() > playerCards.cardsValue() && endRound == false){
                System.out.println("Dealer beats you!");
                playerMoney -= playerBet;
                endRound = true;
            }

            // Dealer draws at 16, stand at 17

            while(dealerCards.cardsValue() < 17 && endRound ==false){
                dealerCards.draw(playingDeck);
                System.out.println("Dealer drew a:" + dealerCards.getCard(dealerCards.deckSize()-1).toString());
            }

            // Display total vaule for dealer

            System.out.println("Dealer's hand has a value of:" + dealerCards.cardsValue());
            // determine if dealer busted

            if(dealerCards.cardsValue() > 21 && endRound == false){
                System.out.println("Dealer busted. You win!");
                playerMoney +=playerBet;
                endRound = true;
            }

            if(playerCards.cardsValue() == dealerCards.cardsValue() && endRound == false){
                System.out.println("Push");
                endRound = true;
            }

            if( dealerCards.cardsValue() < 21 && dealerCards.cardsValue() < playerCards.cardsValue() && endRound == false){
                System.out.println("Player wins!");
                playerMoney += playerBet;
                endRound = true;
            }
            else if(endRound == false){
                System.out.println("You lose the hand");
                playerMoney -= playerBet;
                endRound = true;
            }



            playerCards.moveAllToDeck(playingDeck);
            dealerCards.moveAllToDeck(playingDeck);

            System.out.println("End of hand!");
        }

        System.out.println("Game over you are out of money!");
    }
}
