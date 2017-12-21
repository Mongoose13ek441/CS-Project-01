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

    public ServerThread(Socket socket) {
        super("ServerThread");
        this.socket = socket;
    }
    
    public void run() {
        int playerID;
        try (
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            String inputLine, outputLine;
            Globe globe = new Globe();
            playerID  = globe.assignID();
            while ((inputLine = in.readLine()) != null) {
                outputLine = globe.processInput(inputLine, playerID);
            if (outputLine.equals("sync")){
                System.out.println("Syncing data. Outputting now.");
                out.println(globe.message);
                }
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}