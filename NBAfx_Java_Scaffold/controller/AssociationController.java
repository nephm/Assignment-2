package controller;

import au.edu.uts.ap.javafx.ViewLoader;
import au.edu.uts.ap.javafx.Controller;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.*;
import javafx.scene.image.Image;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.Association;

public class AssociationController extends Controller<Association> {

    @FXML
    private Button exploreTeamsButton;

    @FXML
    private Button newSeasonButton;

    @FXML
    private Button exitButton;

    @FXML
    private GridPane buttonGrid;
    
    @FXML
    public Association getAssociation(){
        return this.model;       
    }
    
    @FXML
    public void handleViewTeams(ActionEvent event) throws Exception { exploreTeams(); }

    @FXML
    public void handleArrangeSeason(ActionEvent event) throws Exception { newSeason(); }

    @FXML
    public void handleExit(ActionEvent event) throws Exception { exit(); }

    public void exploreTeams() {
        try {
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/nba.png"));
            
            ViewLoader.showStage(getAssociation().getTeams(), "/view/ExploreTeamsView.fxml", "Explore Teams", stage);
        } catch (IOException ex) {
            Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void newSeason() {
      try {
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/nba.png"));
            ViewLoader.showStage(getAssociation().getSeason(), "/view/SeasonView.fxml", "Arrange a new season", stage);
        } catch (IOException ex) {
            Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void exit() {
        Platform.exit();
    }
}
