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
    private int numPlayers = 1;
   
    public int assignID(){
        int temp = numPlayers;
        numPlayers+=1;
        return temp;
    }
    
    public String processInput(String Input, int ID) {
        String Output = null;
        if (ID == currentTurn){
            //updates message
            message = Input;
            //updates current player
            if(currentTurn == numPlayers){
                currentTurn = 1;
            }
            else{
                currentTurn ++;
            }
            //broadcasts message
            return "sync";
        }
        else return "notCurrentTurn";
    }
}