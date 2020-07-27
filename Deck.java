package Game;

import java.util.ArrayList;
import java.util.Random;

public class Deck {

    private ArrayList<Card> cards;  // Stores all of the cards which are Card objects

    public Deck(){
        this.cards = new ArrayList<Card>();
    }

    public void createFullDeck(){
        // Generate cards
        for(Suit cardSuit : Suit.values()){
            for (Value cardValue : Value.values()) {

                // add new card to the deck
                this.cards.add(new Card(cardSuit, cardValue));

            }
        }

    }

    // Shuffles the deck of cards

    public void shuffle(){
        ArrayList<Card>tmpDeck = new ArrayList<Card>();
        Random random = new Random();
        int randomCardIndex = 0;
        int originalSize = this.cards.size();
        for (int i = 0; i < originalSize; i++) {
            // Generate Random index

            randomCardIndex = random.nextInt((this.cards.size() - 1) + 1);
            tmpDeck.add(this.cards.get(randomCardIndex));

            // remove from original deck

            this.cards.remove(randomCardIndex);


        }

            this.cards = tmpDeck;
    }

    public String toString(){
        String cardListOutput = "";
        for ( Card aCard : this.cards) {
            cardListOutput += "\n" + aCard.toString();



        }
        return cardListOutput;
    }

    public void removeCard(int i){
        this.cards.remove(i);
    }

    public Card getCard(int i){
        return this.cards.get(i);
    }

    public void addCard(Card addCard){
        this.cards.add(addCard);
    }

    // Draws from the deck

    public void draw(Deck comingFrom){
        this.cards.add(comingFrom.getCard(0));
        comingFrom.removeCard(0);
    }

    public int deckSize(){
       return this.cards.size();
    }

    public void moveAllToDeck(Deck moveTo){
        int thisDeckSize = this.cards.size();

        // puts cards into moveTo Deck

        for (int i = 0; i < thisDeckSize; i++) {
            moveTo.addCard(this.getCard(i));
            
        }

        for (int i = 0; i < thisDeckSize; i++) {
            this.removeCard(0);
        }


    }

    //return total value of cards in deck
    public int cardsValue(){
        int totalValue = 0;
        int aces = 0;

        for (Card aCard : this.cards){
            switch (aCard.getValue()){

                case TWO: totalValue += 2; break;
                case THREE: totalValue += 3; break;
                case FOUR: totalValue += 4; break;
                case FIVE: totalValue += 5; break;
                case SIX: totalValue += 6; break;
                case SEVEN: totalValue += 7; break;
                case EIGHT: totalValue += 8; break;
                case NINE: totalValue += 9; break;
                case TEN:
                case KING:
                case QUEEN:
                case JACK:
                    totalValue += 10; break;
                case ACE: aces += 1; break;
            }
        }

        for (int i = 0; i < aces; i++) {

            if(totalValue > 10){
                totalValue += 1;
            }
            else {
                totalValue += 11;
            }

        }

        return totalValue;

    }
}
