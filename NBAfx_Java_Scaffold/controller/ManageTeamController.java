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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Player;
import model.Team;

public class ManageTeamController extends Controller<Team>  {
    @FXML private Button addButton;
    @FXML private Button updateButton;
    @FXML private Button deleteButton;
    @FXML private Button closeButton;
    @FXML private TextField teamNameTF;
    
    @FXML private TableView<Player> playersTable;

    @FXML private TableColumn<Player, String> playerNamesCol;
    @FXML private TableColumn<Player, String> playerCreCol;
    @FXML private TableColumn<Player, String> playerAgeCol;
    @FXML private TableColumn<Player, String> playerNoCol;

    public Team getTeam(){
        return this.model;
    }

    @FXML
    private void initialize(){ 
        playerNamesCol.setCellValueFactory(data -> data.getValue().nameProperty());
        playerCreCol.setCellValueFactory(data -> data.getValue().getPlayerCreditProperty().asString("%.2f"));
        playerAgeCol.setCellValueFactory(data -> data.getValue().getPlayerAgeProperty().asString());
        playerNoCol.setCellValueFactory(data -> data.getValue().getPlayerNoProperty().asString());    
        
        teamNameTF.setText(getTeam().getName());

        playersTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        playersTable.setItems(getTeam().getCurrentPlayers());

        playersTable.getSelectionModel().clearSelection();

        playersTable.getSelectionModel().selectedIndexProperty().addListener(
        (observable, oldTeam, newTeam) -> {
            if (playersTable.getItems().isEmpty()) {
                addButton.setDisable(false);
                updateButton.setDisable(true);
                deleteButton.setDisable(true);
            } else if(playersTable.getSelectionModel().isEmpty()){
                addButton.setDisable(false);
                updateButton.setDisable(true);
                deleteButton.setDisable(true);
            } else {
                addButton.setDisable(true);
                updateButton.setDisable(false);
                deleteButton.setDisable(false);
            }
        }
        );
    }

    @FXML public void handleAdd(ActionEvent event){ addPlayer(); }
    @FXML public void handleUpdate(ActionEvent event){ updatePlayer(); }
    @FXML public void handleDelete(ActionEvent event){ deletePlayer(); }
    @FXML public void handleClose(ActionEvent event){ closeAndSave(); }

    private void closeAndSave(){
        Validator error =  new Validator();

        if (error.validate("^[A-Z][a-zA-Z ]+$",teamNameTF.getText())){
            getTeam().setName(teamNameTF.getText());
            stage.close();
        } else {
            error.generateErrors(teamNameTF.getText());
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

    private void addPlayer(){

        try {
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/edit.png"));
            
            ViewLoader.showStage(getTeam().getPlayers(), "/view/PlayerUpdateView.fxml", "Adding new player", stage);
        } catch (IOException ex) {
            Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updatePlayer(){    
        Player selectedPlayer = playersTable.getSelectionModel().getSelectedItem();
        try {
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/edit.png"));
            
            ViewLoader.showStage(getTeam().getPlayers(), "/view/PlayerUpdateView.fxml", "Updating Player: " + selectedPlayer.getName(), stage);

        } catch (IOException ex) {
            Logger.getLogger(AssociationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void deletePlayer(){
        getTeam().getPlayers().removePlayer(playersTable.getSelectionModel().getSelectedItem());
    }
}
