package serverio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Migui Martinez
 */
public class ServerThread extends Thread{
    private Socket socket = null;
    private int ID;
    public static String message = null;
    public static int currentTurn = 1;
    public static int numPlayers = 0;
    public ServerThread(Socket socket) {
        super("ServerThread");
        this.socket = socket;
        this.ID = assignID();
    }
    //Assigns an ID to check if it is authorized to send input at this turn
    public int assignID(){
        numPlayers+=1;
        return numPlayers;
    }
    
    public void run() {
        //I/O
        try (
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
            //checks if it is the current turn
            if (ID == currentTurn && inputLine != "update"){
            /*should update a "cardAsk" variable, then all processing should happen in another function.
            Use another file for all the functions for processing, and try to pause or block other threads from
            //responding during processing*/
            message = inputLine;
            //updates current player
            if(currentTurn == numPlayers){
                currentTurn = 1;
            }
            else{
                currentTurn ++;
            }
            //modify to give a signal to all threads to update displays based on global varianble contents
            //Global variables in another file have been observed to cause lag. Currently looking into synchronize().
            out.println(message+"-sent");
            }
            
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}