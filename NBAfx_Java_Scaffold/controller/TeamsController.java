package controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Team;
import model.Teams;

public class TeamsController extends Controller<Teams> {
    @FXML
    private Button addButton;
    @FXML
    private Button manageButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button closeButton;

    @FXML private TableColumn<Team, String> teamsNamesCol;
    @FXML private TableColumn<Team, String> numOfPlayersCol;
    @FXML private TableColumn<Team, String> avePlayerCreCol;
    @FXML private TableColumn<Team, String> aveAgeCol;
    @FXML public TableView<Team> teamsTable;
    
    @FXML
    public Teams getTeams(){
        return this.model;
    }

    @FXML
    private void initialize(){
        teamsNamesCol.setCellValueFactory(data -> data.getValue().nameProperty());
        numOfPlayersCol.setCellValueFactory(data -> data.getValue().countPlayerProperty().asString());
        avePlayerCreCol.setCellValueFactory(data -> data.getValue().countAvgCreditProperty().asString("%.2f"));
        aveAgeCol.setCellValueFactory(data -> data.getValue().countAvgAgeProperty().asString("%.2f"));
        
        teamsTable.setItems(getTeams().currentTeams);     
        
        teamsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        teamsTable.setItems(getTeams().currentTeams);

        teamsTable.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldTeam, newTeam) -> {
        if (newTeam == null) {
            manageButton.setDisable(true);
            deleteButton.setDisable(true);
            addButton.setDisable(false);
        } else {
            manageButton.setDisable(false);
            deleteButton.setDisable(false);
            addButton.setDisable(true);
        }
    }
);
    }

    @FXML
    public void handleAdd(ActionEvent event){ 
        try {
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/nba.png"));
            
            ViewLoader.showStage(getTeams(), "/view/AddTeam.fxml", "Adding new team", stage);
        } catch (IOException ex) {
            Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    public void handleManage(ActionEvent event){
        Team selectedTeam = teamsTable.getSelectionModel().getSelectedItem();
        try {
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/edit.png"));
            
            ViewLoader.showStage(getTeams().getTeam(selectedTeam.getName()), "/view/ManageTeamView.fxml", "Managing Team: " + selectedTeam.getName(), stage);
        } catch (IOException ex) {
            Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    public void handleDelete(ActionEvent event){
        Team selectedTeam = teamsTable.getSelectionModel().getSelectedItem();
        getTeams().remove(selectedTeam);        
    }
    
    @FXML
    public void handleClose(ActionEvent event){ stage.close(); }


}

