package controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Player;
import model.Players;

public class PlayerUpdateController extends Controller<Players>{
    @FXML private TextField playerNameTF;
    @FXML private TextField playerCreTF;
    @FXML private TextField playerAgeTF;
    @FXML private TextField playerNoTF;
    
    @FXML private Button addButton;
    @FXML private Button updateButton;
    @FXML private Button closeButton;

    private String typeOfWindow;
    private String nameOfPlayerUpdate;

    @FXML private Players getPlayers(){
        return this.model;
    }

    @FXML private void initialize(){
        Platform.runLater(()->runAfterIni());
    }

    private void runAfterIni(){
        if (stage.getTitle().contains("Adding new player")){
            playerNameTF.setText("");
            playerCreTF.setText(Double.toString(-1));
            playerAgeTF.setText(Integer.toString(-1));
            playerNoTF.setText(Integer.toString(-1));
            updateButton.setDisable(true);
            typeOfWindow = "add";
        } else {
            typeOfWindow = "update";
            
            int colonIndex = (stage.getTitle()).indexOf(':');
            nameOfPlayerUpdate = (stage.getTitle()).substring(colonIndex + 2).trim();
        
            addButton.setDisable(true);
            
            Player player = getPlayers().getPlayer(nameOfPlayerUpdate);

            playerNameTF.setText(player.getName());
            playerCreTF.setText(Double.toString(player.getCredit()));
            playerAgeTF.setText(Integer.toString(player.getAge()));
            playerNoTF.setText(Integer.toString(player.getNo()));
            addButton.setDisable(true);
        }
    }
    
    @FXML public void handleAdd(ActionEvent event){ addPlayer();}
    @FXML public void handleUpdate(ActionEvent event){ updatePlayer(); stage.close();}
    @FXML public void handleClose(ActionEvent event){ stage.close(); }

    private void addPlayer(){
        Validator error = new Validator();
        if (error.isValid(playerNameTF.getText(), playerCreTF.getText(), playerAgeTF.getText(), playerNoTF.getText())){
            Player toBeAdded = new Player(playerNameTF.getText(), Double.parseDouble(playerCreTF.getText()), Integer.parseInt( playerAgeTF.getText()), Integer.parseInt( playerNoTF.getText()));
            getPlayers().addPlayer(toBeAdded);
            stage.close();
        } else {
            error.generateErrors(playerNameTF.getText(), playerCreTF.getText(), playerAgeTF.getText(), playerNoTF.getText());
            try {
                Stage stage = new Stage();
                stage.setX(ViewLoader.X + 601);
                stage.setY(ViewLoader.Y);
                stage.getIcons().add(new Image("/view/error.png"));
            
                ViewLoader.showStage(error.errors(), "/view/error.fxml", "Error!", stage);
            } catch (IOException ex) {
                Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, ex);
            } 
            error.clear();
        }
    }


    private void updatePlayer(){
        Validator error =  new Validator();

        if (error.isValid(playerNameTF.getText(), playerCreTF.getText(), playerAgeTF.getText(), playerNoTF.getText())){
            getPlayers().getPlayer(nameOfPlayerUpdate).update(playerNameTF.getText(), Double.parseDouble(playerCreTF.getText()), Integer.parseInt( playerAgeTF.getText()), Integer.parseInt( playerNoTF.getText()));
        } else {
            error.generateErrors(playerNameTF.getText(), playerCreTF.getText(), playerAgeTF.getText(), playerNoTF.getText());
            try {
                Stage stage = new Stage();
                stage.setX(ViewLoader.X + 601);
                stage.setY(ViewLoader.Y);
                stage.getIcons().add(new Image("/view/error.png"));
            
                ViewLoader.showStage(error.errors(), "/view/error.fxml", "Error!", stage);
            } catch (IOException ex) {
                Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, ex);
            } 
            error.clear();
        }
    }
}
