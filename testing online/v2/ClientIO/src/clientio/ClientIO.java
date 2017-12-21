/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author Migui Martinez
 */
public class ClientIO {

public static void main(String[] args) {
    try {
        BufferedReader in;
        //Gets needed data
        //System.out.println("Input port");
        //in = new BufferedReader(new InputStreamReader(System.in));
        //int port = Integer.parseInt(in.readLine());
        
        int port = 7;
        
        //System.out.println("Input hostname");
        //in = new BufferedReader(new InputStreamReader(System.in));
        //String hostName = in.readLine();
        
        String hostName = "localhost";
        
        //connects and sets up i/o
        Socket comSocket = new Socket(hostName, port);
        PrintWriter out =new PrintWriter(comSocket.getOutputStream(), true);
        in =new BufferedReader(new InputStreamReader(comSocket.getInputStream()));
        BufferedReader stdIn =new BufferedReader(new InputStreamReader(System.in));
        String userInput, serverOutput;
        System.out.println("Connection established!");
        //input and receive data
        while ((userInput = stdIn.readLine()) != null) {
            out.println(userInput);
            serverOutput = in.readLine();
            System.out.println(serverOutput);
        }
    } catch (UnknownHostException e) {
        System.err.println("Don't know about host");
        System.exit(1);
    } catch (IOException e) {
        System.err.println("Couldn't get I/O for the connection to host");
        System.exit(1);
    } catch (Exception e) {
        e.printStackTrace();
    }
}    
}
