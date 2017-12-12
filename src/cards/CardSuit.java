/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cards;

/**
 * Represents the suit of a card.
 * @author Edge
 */
public enum CardSuit {
    CLUBS       (CardColor.BLACK,   'C'),
    DIAMONDS    (CardColor.RED,     'D'),
    HEARTS      (CardColor.RED,     'H'),
    SPADES      (CardColor.BLACK,   'S');
    
    private final CardColor color;
    private final char code;
    
    /**
     * Creates a card suit.
     * @param color the color of the suit
     * @param code the one-character code of the suit
     */
    CardSuit(CardColor color, char code) {
        this.color = color;
        this.code = code;
    }
    
    /**
     * Returns the CardColor of the suit.
     * @return the CardColor of the suit
     */
    public CardColor getColor() {
        return color;
    }
    
    /**
     * Checks if this suit is a different color from a given suit.
     * @param otherSuit the other suit being compared to this one
     * @return true if the color of this suit is different from otherSuit
     */
    public boolean isDifferentColor(CardSuit otherSuit) {
        return !this.getColor().equals(otherSuit.getColor());
    }
    
    /**
     * Checks if this suit is the same color as a given suit.
     * @param otherSuit the other suit being compared to this one
     * @return true if the color of this suit is the same as otherSuit
     */
    public boolean isSameColor(CardSuit otherSuit) {
        return this.getColor().equals(otherSuit.getColor());
    }
    
    /**
     * Returns the one-character code for the suit of the card.
     * @return the one-character code for the suit of the card
     */
    public char getCode() {
        return code;
    }
}
