/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gofishfixed;

import cards.CardFace;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author Joaquin
 */
public class GameWindowController implements Initializable {
    
    CardFace[] values = CardFace.values();
    ArrayList<ImageView> hand;
    //Manipulables - changes local variables
    @FXML
    private ImageView hand1 = new ImageView();
    @FXML
    private ImageView hand2 = new ImageView();
    @FXML
    private ImageView hand3 = new ImageView();
    @FXML
    private ImageView hand4 = new ImageView();
    @FXML
    private ImageView hand5 = new ImageView();
    @FXML
    private ImageView hand6 = new ImageView();
    @FXML
    private ImageView hand7 = new ImageView();
    @FXML
    private Button handCycle;
    @FXML
    private Button playerUp;
    @FXML 
    private Button playerDown;
    @FXML
    private Button ask;
    @FXML
    private Button end;
    //Info - updated by sync()
    @FXML
    private Label face;
    @FXML
    private Label currentPlayer = new Label();
    @FXML
    private Label playerTarget;
    @FXML
    private Label numDeck;
    @FXML
    private Label numCards;
    @FXML
    private Label updateText = new Label();
    
    @FXML
    private Stage stage;
    @FXML
    private Parent root;
    
    //Variables - sent by update()
    
    //Local variables - sent by sync()
    
    
    private int playerTargetNum = 0;
    
   
    
    private boolean hasSwapped = true;
    private boolean hasEnded = false;
    private int handpage = 0;
    private CardFace targetFace = CardFace.QUEEN;
    private int faceNum = 11;
    private static GFGame game = new GFGame(2);
    //private int handfacepicked = 0;
    
    @FXML
    private void update(){
        //sends data to global
    }

    @FXML
    private void updateTargetPlayer(){
        this.playerTarget.setText("P"+Integer.toString(1+this.playerTargetNum));
        //System.out.println(this.playerTargetNum);
        System.out.println("P"+Integer.toString(1+this.playerTargetNum));
        
    }
    
    @FXML
    private void updatePlayer(){
        this.currentPlayer.setText(Integer.toString(1+ this.game.getCurrentPlayer()));
    }
    @FXML
    private void updateTargetCard(){
        this.face.setText(this.targetFace.name());
    }
    
    @FXML
    private void updateHand(){
        for(int i = this.handpage; i < game.getCurrentHand().size(); i++){
            //System.out.println(game.getCurrentHand());
            System.out.println(i);
            if(game.getCurrentHand().get(i) == null)
                break;
            System.out.println(game.getCurrentHand().get(i)+".png");
            Image pic = new Image("file:src\\artAssets\\"+game.getCurrentHand().get(i).trim()+".png");
            hand.get(i%hand.size()).setImage(pic);
            
            //new Image(getClass().getResource)
            //updateText.setText("Test");
        }
    }

    @FXML
    private void cycleHand(ActionEvent e){
        this.handpage = (this.handpage + hand.size());  
        if(this.handpage >= this.game.getCurrentHand().size()){
            this.handpage = 0;
        }
        this.updateHand();
        System.out.println(handpage);
    }
    
    

    
    @FXML
    private void changeTargetDirect(MouseEvent m){
        if (hand.contains(m.getSource()) && hand.indexOf(m.getSource()) < game.getCurrentHand().size()){
            String code = game.getCurrentHand().get(hand.indexOf(m.getSource())).substring(0, 1);
            switch(code){
                    case "A":
                        this.faceNum = 0;
                        break;
                    case "K":
                        this.faceNum = 12;
                        break;
                    case "Q":
                        this.faceNum = 11;
                        break;
                    case "J":
                        this.faceNum = 10;
                        break;
                    case "T":
                        this.faceNum = 9;
                        break;
                    default:
                        this.faceNum = Integer.parseInt(code)-1;
                        break;
                }
            this.targetFace = this.values[this.faceNum];
            this.updateTargetCard();
        }
    }
    
    @FXML
    private void changePlayerTarget(ActionEvent e){
        if (e.getSource() == this.playerUp){
            System.out.println("in up");
            this.playerTargetNum = (this.playerTargetNum + 1)%game.getPlayers().size();
            //System.out.println(this.playerTargetNum);
            if(this.playerTargetNum == this.game.getCurrentPlayer()){
                this.playerTargetNum = (1+ this.playerTargetNum) % this.game.getPlayers().size();
            }
        }else if (e.getSource()==this.playerDown){
            this.playerTargetNum = this.playerTargetNum - 1;
            if(this.playerTargetNum < 0){
                this.playerTargetNum = game.getPlayers().size() - 1;
            }
            if(this.playerTargetNum == this.game.getCurrentPlayer()){
                this.playerTargetNum = (1+ this.playerTargetNum) % this.game.getPlayers().size();
            }
        }
        this.updateTargetPlayer();
    }
    
    @FXML
    private void ask(ActionEvent e){
        if(hasEnded){
            ;
        }else{
            if(this.takeCards()){
                this.updateText.setText("You take some " + this.targetFace.toString() + " from P" + Integer.toString(1+this.playerTargetNum));
                System.out.println("Taking cards!");
            }else{
                this.updateText.setText("No cards! You GO FISH!");
                this.hasEnded = true;
            } 
            this.updateHand();
            this.updatePlayer();
        }
    }
    
    @FXML
    private void endTurn(ActionEvent e){
        if(!this.hasSwapped && this.hasEnded){
            this.hasSwapped = true;
            this.hasEnded = false;
            this.game.nextPlayer();
            this.updateHand();
            this.updatePlayer();
            this.end.setText("End");
        }else if(this.hasEnded){
        for (ImageView view:hand){
            view.setImage(new Image("file:src\\artAssets\\placeholder card.png"));
        }
        this.hasSwapped = false;
        this.end.setText("Start");
        this.updateText.setText("Click start to swap players.");
        }
    }
    
    private boolean takeCards(){
        return this.game.takeCard(targetFace, playerTargetNum);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        hand = new ArrayList<ImageView>();
        hand.add(hand1);
        hand.add(hand2);
        hand.add(hand3);
        hand.add(hand4);
        hand.add(hand5);
        hand.add(hand6);
        hand.add(hand7);
       // this.updateText.setText("Bleh");
        //hand.get(0).setImage(new Image("file:src/artAssets/7H.png"));
    }    
    
}
