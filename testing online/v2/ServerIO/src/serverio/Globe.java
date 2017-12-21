/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverio;

/**
 *
 * @author Migui Martinez
 */
public class Globe {
    public static String message = null;
    public static int currentTurn = 1;
    public static int numPlayers = 0;
   
    public int assignID(){
        numPlayers+=1;
        return numPlayers;
    }
}