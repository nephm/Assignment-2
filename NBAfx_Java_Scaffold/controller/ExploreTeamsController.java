package controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Teams;

public class ExploreTeamsController extends Controller<Teams> {
    @FXML
    private Button exploreTeamsButton;
    @FXML
    private Button viewPlayersButton;
    @FXML
    private Button exitButton;

    public void handleExploreTeams(ActionEvent event) throws Exception { exploreTeams(); }

    @FXML
    public void handleViewPlayers(ActionEvent event) throws Exception { viewPlayers(); }

    @FXML
    public void handleExit(ActionEvent event) throws Exception { stage.close(); }

    @FXML
    public Teams getTeams(){
        return this.model;
    }

    @FXML
    public void exploreTeams() throws Exception {
        try {
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/nba.png"));
            
            ViewLoader.showStage(getTeams(), "/view/TeamsTable.fxml", "Teams View", stage);
        } catch (IOException ex) {
            Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    public void viewPlayers() throws Exception {
      try {
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/nba.png"));
            ViewLoader.showStage(getTeams(), "/view/PlayersView.fxml", "Players", stage);
        } catch (IOException ex) {
            Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

