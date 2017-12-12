/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gofishextreme;

import java.net.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
/**
 *
 * @author Joaquin
 */
public class CommThread extends Thread{
    private Socket client;
    //private ServerSocket server;
    private String data = "";
    //for joining a game
    public CommThread(Socket acceptedSocket) {
        
        super("Communication Thread");
        this.client = acceptedSocket;
        
    }
    
    @Override
    public void run() {
        try{
            //create objects for reading and writing
            PrintWriter out = new PrintWriter(this.client.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
            do{ 
                data = in.readLine();
                if(data != null){
                    
                }
            }while(client.isConnected());
        }
        catch (IOException e){
            System.out.println("Socket I/O Error.");
        }
    }
    
    public String get_data(){
        if(data != null)
            return data;
        else
            return "No Data.";
    }
    
    public boolean is_connected(){
        return client.isConnected();
    }
}
