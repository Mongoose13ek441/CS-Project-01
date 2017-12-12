/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gofishextreme;
import java.util.ArrayList;
import cards.*;
import java.util.Random;
/**
 *
 * @author Joaquin
 */
public class GFGame {
    //fields
    private Random rand = new Random();
    private int players = 0;
    private PlayingCardDeck deck = new PlayingCardDeck();
    private int currentPlayer = 0;
    
    public GFGame(int players){
        this.players = players;
        this.deck.shuffle();
        this.currentPlayer = rand.nextInt(players);
        
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
    
    
}
