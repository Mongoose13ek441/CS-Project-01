/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gofishhotseat;
import java.io.*;
import java.util.*;
import cards.*;
/**
 *
 * @author Joaquin
 */
public class GFGame {
    private PlayingCardDeck deck;
    private ArrayList<GFPlayer> players;

    
    
    private int currentPlayer = 0;
    private boolean gameStarted;
    private boolean gameEnded;
    
    public GFGame(){
        ; //fuck you null pointer exception
    }
    
    public GFGame(int playercount) {
        // Initialize a new PlayingCardDeck and shuffle it
        deck = new PlayingCardDeck();
        deck.shuffle();
        // Display the deck on the console.
        System.out.println(deck);
         
        // Initialize an empty ArrayList of GoFishPlayer
        players = new ArrayList<GFPlayer>();
        //fill with players
        for(int i = 0; i < playercount; i++){
            players.add(new GFPlayer("Player "+ Integer.toString(i)));
        }
        
        //deal cards
        for(GFPlayer player: this.players){
            for(int i = 0; i < 5; i++)
            player.addCard(deck.deal());
        }
        gameStarted = false;
        gameEnded = false;
    }
    
    public void addPlayer(String name) {
        GFPlayer gfp = new GFPlayer(name);
        for (int i = 0; i < 5; i++) {
            gfp.addCard(deck.deal());
        }
        players.add(gfp);
    }
    
    public String getStatus() {
        String tempString = "GO FISH GAME STATUS\n";
        for (GFPlayer gfp : players) {
            tempString += gfp + "\n";
        }
        tempString += "Current deck:\n" + deck;
        return tempString;
    }
    
    public void start() {
        gameStarted = true;
    }
    
    public ArrayList<String> getCurrentHand(){
        //format: name, cards in string form
        ArrayList<String> out = new ArrayList<String>();
        //out.add(this.players.get(this.currentPlayer).getName());
        for(PlayingCard card: this.players.get(this.currentPlayer).getHand()){
            out.add(card.toString());
        }
        return out;
    }
    
    public boolean takeCard(CardFace face, int indexOfTarget){
        //System.out.println(this.players.get(indexOfTarget).getHand());
        
        ArrayList<PlayingCard> out = this.players.get(indexOfTarget).takeCards(face);
        //System.out.println(out);
        if(!out.isEmpty()){
            //System.out.println("Old hand: " + this.getCurrentHand());
            this.players.get(currentPlayer).addCards(out);
            //System.out.println("Current new hand: " + this.getCurrentHand());
            return true;
        }else{
            this.players.get(currentPlayer).addCard(this.deck.deal());
            //this.currentPlayer = (this.currentPlayer + 1) % this.players.size(); //cycle to next player
            return false;
        }
         
    }
    
    public ArrayList<GFPlayer> getPlayers() {
        return players;
    }
    
    public int getCurrentPlayer(){
        return this.currentPlayer;
    }
    public void setCurrentPlayer(int i){
        this.currentPlayer = i;
    }
    public void nextPlayer(){
        this.currentPlayer = (this.currentPlayer + 1) % this.players.size();
    }
}
