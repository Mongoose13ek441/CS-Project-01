/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gofishextreme;

import java.net.*;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 *
 * @author Joaquin
 */
public class BossThread extends Thread{
    private ServerSocket server;
    private ArrayList<CommThread> clients = new ArrayList();
    private CommThread self;
    private final static int PORT = 3000;
    private boolean is_accepting = true;
    private boolean is_server = true;
    public BossThread(){
        super("Server Boss Thread");
    }
    
    @Override
    public void run(){
        //no handling code.
        
        if (this.is_server){
            this.acceptPlayers();
            //stop accepting players when app thread changes value of is_accepting
            //accept data
        }else{
            ;
        }
    }
    
    private void acceptPlayers(){
        try{
            server = new ServerSocket(PORT, 0);
            //accept new players
            System.out.print("Server Socket bound to: ");
            System.out.println(server.getLocalPort());
            
            System.out.println("Attempting to accept");
            while(this.get_accept_status()){
                Socket client = server.accept();
                if(client != null){
                    System.out.println("Client Acquired!");
                    clients.add(new CommThread(client));
                    clients.get(clients.size()-1).start();
                }
            }
            System.out.println("Done Accepting Players.");
            
        }catch(IOException i){
            System.out.println("Seppuku.");
        }
        
        
    }
    
    public ArrayList get_gamedata(){
        ArrayList<String> out = new ArrayList();
        for (CommThread comm : this.clients){
            out.add(comm.get_data());
        }
        return out;
    }
    
    public int get_client_count(){
        return this.clients.size();
    }
    
    public synchronized boolean get_accept_status(){
        return this.is_accepting;
    }
    
    public synchronized void set_accept_status(boolean status){
        this.is_accepting = status;
    }
    
    public void sendData(){
    ;
}
}
