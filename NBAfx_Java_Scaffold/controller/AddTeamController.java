package controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Team;
import model.Teams;

public class AddTeamController extends Controller<Teams>{
    @FXML private TextField inputTF;
    @FXML private Button addButton;

    private Teams getTeams(){
        return this.model;
    }

    @FXML
    public void handleAdd(ActionEvent event){ addTeam(inputTF.getText());}

    private void addTeam(String team){
        Validator error =  new Validator();
        if (!error.validate("^[A-Z][a-zA-Z ]+$", team) || error.isEmpty(team)) {
            error.generateErrors(team);
            
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
        
        else if(teamExist()) {
            error.addError(inputTF.getText() + " Team already exist");
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
        
        else {
            getTeams().addTeam(new Team(team));
            stage.close();
        }
    }

    private boolean teamExist(){
        for (Team team : getTeams().currentTeams){
            if (team.getName().contains(inputTF.getText())){
                return true;
            }   
        }
        return false;
    }
}
