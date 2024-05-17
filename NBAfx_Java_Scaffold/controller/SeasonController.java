package controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Season;

public class SeasonController extends Controller<Season>{
    @FXML
    private Button roundButton;
    @FXML
    private Button currentButton;
    @FXML
    private Button gameButton;
    @FXML
    private Button resultButon;
    @FXML
    private Button closeButton;

    public Season getSeason(){
        return this.model;
    }

    @FXML
    public void handleRound(ActionEvent event){ viewRound();}
    @FXML
    public void handleCurrent(ActionEvent event){ current();}
    @FXML
    public void handleGame(ActionEvent event){ playGames(); }
    @FXML
    public void handleResult(ActionEvent event){ showResult(); }
    @FXML
    public void handleClose(ActionEvent event){ stage.close(); }

    @FXML public void initialize(){
        getSeason().playGame();
    }

    public void viewRound(){
        try {
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/nba.png"));
            
            ViewLoader.showStage(getSeason(), "/view/SeasonRoundView.fxml", "Season Rounds", stage);
        } catch (IOException ex) {
            Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void current(){
        try {
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/nba.png"));
            
            ViewLoader.showStage(getSeason(), "/view/CurrentRoundTeams.fxml", "Tournament", stage);
        } catch (IOException ex) {
            Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void playGames(){
        String response = getSeason().play(getSeason().round());

        LinkedList<String> dummy = new LinkedList<>();
        dummy.add(response);

        if (response.contains("No Games to play!\nPlease add games to this round.")){
            //throw error

            try {
                Stage stage = new Stage();
                stage.setX(ViewLoader.X + 601);
                stage.setY(ViewLoader.Y);
                stage.getIcons().add(new Image("/view/nba.png"));
                
                ViewLoader.showStage(dummy, "/view/error.fxml", "All games played", stage);
            } catch (IOException ex) {
                Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        

        } else if (response.contains("All games finished! You can check results now!\n")){
            getSeason().playGame();
            //say games are play

            try {
                Stage stage = new Stage();
                stage.setX(ViewLoader.X + 601);
                stage.setY(ViewLoader.Y);
                stage.getIcons().add(new Image("/view/nba.png"));
                
                ViewLoader.showStage(dummy, "/view/error.fxml", "All games played", stage);
            } catch (IOException ex) {
                Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, ex);
            }


        } else {
            //say end

            try {
                Stage stage = new Stage();
                stage.setX(ViewLoader.X + 601);
                stage.setY(ViewLoader.Y);
                stage.getIcons().add(new Image("/view/nba.png"));
                
                ViewLoader.showStage(dummy, "/view/error.fxml", "All games played", stage);
            } catch (IOException ex) {
                Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void showResult(){
        try {
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/nba.png"));
            
            ViewLoader.showStage(getSeason(), "/view/RecordView.fxml", "Season Record", stage);
        } catch (IOException ex) {
            Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
}