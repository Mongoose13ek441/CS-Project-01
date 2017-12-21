/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cards;

/**
 *
 * @author Edge
 */
public enum CardColor {
    RED,
    BLACK;
    
    /**
     * Returns the opposite color of the current card's color
     * @return the opposite color to the card's color
     */
    CardColor oppositeColor() {
        if (this == RED) {
            return BLACK;
        }
        return RED;
    }
}
