/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverio;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

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
    public static ArrayList s = new ArrayList();
    public static void main(String[] args) {
    int port = 7;
    try(ServerSocket serverSocket = new ServerSocket(port)){
        //make a thread that listens for changes in other thread variables or for communication in other threads
        
        //sets up the connection
        while(true){
            Socket socket = serverSocket.accept();
            s.add(serverSocket);
            new ServerThread(socket).start();
            System.out.println("Connection established!");
        }
    }
    catch(Exception e){
        e.printStackTrace();
    }
    
}
}
