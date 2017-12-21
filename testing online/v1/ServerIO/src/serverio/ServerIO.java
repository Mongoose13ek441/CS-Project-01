/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

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
    Scanner scanner = new Scanner(System.in);
    String test = "This is a message";
    ServerSocket serverSocket = null;
    Socket socket = null;
    try{
        //Gets needed data
        System.out.println("Input port");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int port = Integer.parseInt(in.readLine());
        System.out.println("Input hostname");
        in = new BufferedReader(new InputStreamReader(System.in));
        String hostName = in.readLine();
        //sets up the connection
        serverSocket = new ServerSocket(port);
        socket = serverSocket.accept();
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);                   
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
        out.println(test);
        test = inputLine;
    }
    }
    catch(Exception e){
        e.printStackTrace();
    }
    
}
}
