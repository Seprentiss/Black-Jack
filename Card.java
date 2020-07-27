//Class that creates a card

package Game;

public class Card {

    private Suit suit;
    private Value value;

    public Card(Suit suit, Value value){

        this.value = value;
        this.suit = suit;

    }

    public String toString(){

        return this.value.toString() + " OF " + this.suit.toString() + "S";

    }

    public Value getValue(){
        return this.value;
    }

}
