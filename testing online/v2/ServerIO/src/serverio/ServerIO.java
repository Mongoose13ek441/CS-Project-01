/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;

/**
 *
 * @author Migui Martinez
 */
/*
public class clientThread implements Runnable {

    public void run() {
        System.out.println("Hello from a thread!");
    }

    public static void main(String args[]) {
        (new Thread(new clientThread())).start();
    }
*/

public class ServerIO {

    /**
     * @param args the command line arguments
     */
    
    //Gets necessary data
    
    public static void main(String[] args) {
    String test = "This is a message";
    ServerSocket serverSocket = null;
    try{
        BufferedReader in;
        //Gets needed data
        System.out.println("Input port");
        in = new BufferedReader(new InputStreamReader(System.in));
        int port = Integer.parseInt(in.readLine());
        
        
        System.out.println("Input number of clients");
        in = new BufferedReader(new InputStreamReader(System.in));
        int numClient = Integer.parseInt(in.readLine());
        //sets up the connection
        for (int i = 0; i < numClient; i++){
            serverSocket = new ServerSocket(port);
            new ServerThread(serverSocket.accept()).start();
            System.out.println("Connection established!");
        }
    }
    catch(Exception e){
        e.printStackTrace();
    }
    
}
}
