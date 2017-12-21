/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cards;

import java.util.*;

/**
 * Represents a deck of playing cards.
 * @author Edge
 */
public class PlayingCardDeck {
    private ArrayList<PlayingCard> deck;
    
    /**
     * Create a new PlayingCardDeck.
     */
    public PlayingCardDeck() {
        deck = new ArrayList<PlayingCard>();
        for (CardSuit suit : CardSuit.values()) {
            for (CardFace face : CardFace.values()) {
                deck.add(new PlayingCard(face, suit));
            }
        }
    }
    
    /**
     * Shuffle the PlayingCardDeck.
     */
    public void shuffle() {
        Collections.shuffle(deck);
    }
    
    /**
     * Remove a card on the deck and return the card as if dealing it.
     * If the deck is empty, the card dealt is null.
     * @return the top of the deck and remove it from the deck
     */
    public PlayingCard deal() {
        if (deck.isEmpty()) {
            return null;
        }
        return deck.remove(0);
    }
    
    /**
     * Returns the number of PlayingCards in the PlayingCardDeck.
     * @return the number of PlayingCards in the PlayingCardDeck
     */
    public int getSize() {
        return deck.size();
    }
    
    /**
     * Returns a user-friendly printable version of the deck.
     * @return user-friendly printable version of the deck
     */
    @Override
    public String toString() {
        String tempString = "";
        int breaker = 0;
        for (PlayingCard card : deck) {
            tempString += card.toString() + " ";
            breaker++;
            if (breaker >= 13) {
                breaker = 0;
                tempString += "\n";
            }
        }
        return tempString;
    }
}
