/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gofishextreme;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.*;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
/**
 *
 * @author Joaquin
 */
public class FXMLDocumentController implements Initializable {
    //setup window
    @FXML
    private TextField serverInput;
    @FXML
    private TextField portInput;
    @FXML
    private Label header;
    @FXML
    private Stage stage;
    @FXML
    private Parent root;
    @FXML
    private Button hostgame;
    //game window
    @FXML
    private ImageView hand1;
    @FXML
    private ImageView hand2;
    @FXML
    private ImageView hand3;
    @FXML
    private ImageView hand4;
    @FXML
    private ImageView hand5;
    @FXML
    private ImageView hand6;
    @FXML
    private ImageView hand7;
    @FXML
    private Button ask;
    @FXML
    private Button suitUp;
    @FXML
    private Button suitDown;
    @FXML
    private Button faceUp;
    @FXML
    private Button faceDown;
    @FXML
    private Button cycleHand;
    @FXML 
    private Label face;
    @FXML
    private Label suit;
    @FXML
    private Label updateText;
    //process objects
    private GFGame game = new GFGame(1);
    BossThread boss = new BossThread();
    CommThread self = new CommThread(new Socket());
    Boolean is_hosting = false;
    //FXML Functions
    @FXML
    private void joinGame(ActionEvent event) {
        //Attempt to connect to server.
        System.out.println("Joining Game. Initialize CommThead.");
        header.setText("Joining Game");
        System.out.println(serverInput.getText());
        System.out.println(portInput.getText());
        try{
            Socket client = new Socket(serverInput.getText(), Integer.parseInt(portInput.getText()));
            if (client.isConnected()){
                self = new CommThread(client);
            }
        }catch(IOException e){
            System.out.println("IOException in attempting to join server. Please try again.");
        }
        if(self.is_connected())
            header.setText("Connected!");
        //Initialize Game.
        this.startGameWindow();
    }
    
    @FXML
    private void hostGame(ActionEvent event) {
        if(!is_hosting){
            header.setText("Hosting...");
            hostgame.setText("Start Game");
            //This begins the server thread, initially set to accept players.
            this.boss.start();
            System.out.println("Boss started.");
            this.is_hosting = true;
            //This next block of code starts the background task which allows 
            //the header to show how many users are connected at the moment.
            Thread th = new Thread(updateGameroster);
            th.start();
            System.out.println("background task started.");
        }
        else{
            //This stops the server thread from getting more players.
            this.boss.set_accept_status(false);
            this.startGameWindow();
            this.game = new GFGame(this.boss.get_client_count());
        }
    }
    
    @FXML
    private void startGameWindow(){
        try{
            //Spawn a new window for playing the game.
            stage = new Stage();
            root = FXMLLoader.load(getClass().getResource("GameWindow.fxml"));
            stage.setScene(new Scene(root));
            stage.show();
            Thread th = new Thread(usherGame);
            th.start();
        }catch(IOException ie){
            System.out.println("Error. You fool. Starting a new game window has failed.");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    //concurrency objects
    /**
     * This object is used to update the header noting how many users are connected to the server.
     * It uses the Platform.runLater command to tell the Application Thread to alter the header ONLY when the number of users has changed.
     */
    private Task<Void> updateGameroster = new Task<Void>() {
        @Override protected Void call() throws Exception {
            
            int c = 0;
            while(boss.get_accept_status()){
                if(c != boss.get_client_count()){
                    Platform.runLater(new Runnable(){
                        @Override 
                        public void run(){
                            header.setText("Hosting..." + Integer.toString(boss.get_client_count()) + " players connected.");
                        }
                    });
                    c = boss.get_client_count();
                }
            }
             //IDK how lambda works in Java
            System.out.println("Done updating game roster!");
            return null;
        }
    };
    /**
     * This object exists to push changes to the GUI due to the game WITHOUT 
     * player prompting, such as when other players act. 
     * 
     */
    private Task<Void> usherGame = new Task<Void>() {
        @Override protected Void call() throws Exception {
            
             //IDK how lambda works in Java
            
            return null;
        }
    };
}
