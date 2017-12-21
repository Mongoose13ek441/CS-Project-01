/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Migui Martinez
 */
public class ClientIO {

public static void main(String[] args) {
    //Connects to client and sets up I/O
    int port = 7;
    String host = "localhost";
    try (
            Socket comsocket = new Socket(host, port);
            PrintWriter out = new PrintWriter(comsocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(comsocket.getInputStream()));
        ) {
        BufferedReader stdIn =new BufferedReader(new InputStreamReader(System.in));
        String userInput, serverOutput;
        System.out.println("Connection established!");
        //input and receive data.
        while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                /*Prints the response.
                Ideally, it should print the same response, a call to a "sync function"
                when a response is sent to all sockets
                but it currently only reaches the one who sent it.
                Check first if the response reaches everyone before applying the sync.*/
                System.out.println("Output: "+in.readLine());
            }
    }   catch (Exception e) {
        e.printStackTrace();
    }
}    
}
