/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gofishhotseat;

import cards.CardFace;
import cards.PlayingCard;
import java.util.ArrayList;

/**
 *
 * @author Joaquin
 */
public class GFPlayer {
    private ArrayList<PlayingCard> hand;
    private String name;
    
    public GFPlayer(String name) {
        this.name = name;
        hand = new ArrayList<PlayingCard>();
    }
    
    /**
     * Add a card to the player's hand.
     * @param card the PlayingCard being added to the hand
     */
    public void addCard(PlayingCard card) {
        hand.add(card);
    }
    
    public void addCards(ArrayList<PlayingCard> cards){
        this.hand.addAll(cards);
    }
    
    public ArrayList<PlayingCard> getHand(){
        return hand;
    }
    
    @Override
    public String toString() {
        String tempString = name + ": ";
        for (PlayingCard card : hand) {
            tempString += card + " ";
        }
        return tempString;
    }
    
    public ArrayList<PlayingCard> takeCards(CardFace face) {
        ArrayList<PlayingCard> removedCards = new ArrayList<PlayingCard>();
        // TO DO: find all the cards in the hand of the player with the same face as the passed face
        for (int i = 0; i < hand.size(); i++){
            if(hand.get(i).getFace().equals(face)){
                removedCards.add(hand.get(i));
                hand.remove(i);
                i--;
            }
        }
        return removedCards;
    }
    
    public String getName(){
        return name;
    }
}
