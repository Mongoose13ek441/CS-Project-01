/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gofishfixed;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Migui Martinez
 */
public class SelectModeController implements Initializable {
    @FXML
    private Stage stage;
    @FXML
    private Parent root;
    @FXML
    private Label loadingLabel;
    @FXML
    private TextField serverInput;
    @FXML
    private TextField portInput;
    @FXML
    private TextField playerNum;
    @FXML
    private Button hostGame;
    @FXML
    private Button joinGame;
    @FXML
    private Button startGame;   
    @FXML
    private Button startOnlineGame;
    
    //functions for start
    @FXML
    private void startHotSeat(ActionEvent event){
        //Starts a local game
        //Gets number of Players and starts a local game
        System.out.println("Getting number of players.");
        try{
            game = new GFGame(Integer.parseInt(playerNum.getText()));
            game.start();
            System.out.println(game.getPlayers().size());
            this.startGameWindow();
        }catch(NumberFormatException n){
            loadingLabel.setText("Please enter a number.");
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
        }catch(IOException ie){
            System.out.println("Error. You fool. Starting a new game window has failed.");
        }
    }
    @FXML
    private void joinGame(ActionEvent event) {
        //Attempt to connect to server.
        System.out.println("Joining Game. Initialize CommThead.");
        header.setText("Joining Game");
        System.out.println(serverInput.getText());
        System.out.println(portInput.getText());
        //Connect to server
        
        /*try{
            Socket client = new Socket(serverInput.getText(), Integer.parseInt(portInput.getText()));
            if (client.isConnected()){
                self = new CommThread(client);
            }
        }catch(IOException e){
            //System.out.println("IOException in attempting to join server. Please try again.");
        }
        if(self.is_connected())
            header.setText("Connected!");
        */
        
        //Initialize game in client mode - does not use local data.
        this.startGameWindow();
    }
    
    @FXML
    private void hostGame(ActionEvent event) {
        /*if(!is_hosting){
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
        */
        }
    @FXML
    private void startOnline(ActionEvent event){
        try{
            //Spawn a new window for playing the game. Set to use local data.
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
    }
    
    
    
    
    
    
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
