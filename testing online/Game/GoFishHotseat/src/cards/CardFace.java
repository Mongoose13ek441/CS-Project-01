/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cards;

/**
 * This enumeration represents the default values of a playing card's face value.
 * By default, all number cards have the same card value as its face. The default
 * card value for ace is 1. Jack is 11, Queen is 12, and King is 13. This may
 * be overridden by the constructor of a specific type of Card.
 * @author Edge
 */
public enum CardFace {
    ACE     ("Ace",     'A',    1),
    TWO     ("Two",     '2',    2),
    THREE   ("Three",   '3',    3),
    FOUR    ("Four",    '4',    4),
    FIVE    ("Five",    '5',    5),
    SIX     ("Six",     '6',    6),
    SEVEN   ("Seven",   '7',    7),
    EIGHT   ("Eight",   '8',    8),
    NINE    ("Nine",    '9',    9),
    TEN     ("Ten",     'T',    10),
    JACK    ("Jack",    'J',    11),
    QUEEN   ("Queen",   'Q',    12),
    KING    ("King",    'K',    13);
    
    private final String name;
    private final char code;
    private final int value;
    
    /**
     * Creates a face value of a card.
     * @param name the name of the face value of the card
     * @param code the one-character code for the face value of the card
     * @param value the default card value of the face value of the card
     */
    CardFace(String name, char code, int value) {
        this.name = name;
        this.code = code;
        this.value = value;
    }
    
    /**
     * Returns the full name of the face of the card as a String.
     * @return the full name of the face of the card
     */
    @Override
    public String toString() {
        return name;
    }
    
    /**
     * Returns the short one-character code of the face of the card.
     * @return the short code of the face of the card
     */
    public char getCode() {
        return code;
    }
    
    /**
     * Returns the default card value of the face of the card.
     * @return the default card value of the face of the card
     */
    public int getValue() {
        return value;
    }
}