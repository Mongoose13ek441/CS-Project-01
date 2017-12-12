/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cards;

/**
 * Represents a playing card.
 * @author Edge
 */
public class PlayingCard {
    private final CardSuit suit;
    private final CardFace face;
    
    /**
     * Create a new playing card.
     * @param face the face of the new card
     * @param suit the face of the new suit
     */
    public PlayingCard(CardFace face, CardSuit suit) {
        this.suit = suit;
        this.face = face;
    }
    
    /**
     * Returns the face of the card.
     * @return the face of the card
     */
    public CardFace getFace() {
        return face;
    }
    
    /**
     * Returns the suit of the card.
     * @return the suit of the card
     */
    public CardSuit getSuit() {
        return suit;
    }
    
    @Override
    public String toString() {
        return Character.toString(face.getCode())+Character.toString(suit.getCode());
    }
}
