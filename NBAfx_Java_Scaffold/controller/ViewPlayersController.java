package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Player;
import model.Team;
import model.Teams;

public class ViewPlayersController extends Controller<Teams> {
    @FXML private TableView<Player> allPlayersTV;

    @FXML private TableColumn<Player, String> teamCol;
    @FXML private TableColumn<Player, String> nameCol;
    @FXML private TableColumn<Player, String> creditCol;
    @FXML private TableColumn<Player, String> ageCol;
    @FXML private TableColumn<Player, String> noCol;
    @FXML private TableColumn<Player, String> levelCol;

    @FXML private TextField levelFilterTF;
    @FXML private TextField nameFilterTF;

    @FXML private TextField fromTF;
    @FXML private TextField toTF;
    
    @FXML private Button closeButton;

    ObservableList<Player> filteredPlayers = FXCollections.observableArrayList();;

    public final Teams getTeams(){
        return this.model;
    }

    @FXML public void initialize(){
        allPlayersTV.setItems(this.model.allPlayersList());
        allPlayersTV.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        allPlayersTV.setPlaceholder(new Label("Players list is not loaded."));

        teamCol.setCellValueFactory(data -> data.getValue().getTeamNameProperty());
        nameCol.setCellValueFactory(data -> data.getValue().nameProperty());
        creditCol.setCellValueFactory(data -> data.getValue().getPlayerCreditProperty().asString("%.2f"));
        ageCol.setCellValueFactory(data -> data.getValue().getPlayerAgeProperty().asString());
        noCol.setCellValueFactory(data -> data.getValue().getPlayerNoProperty().asString());
        levelCol.setCellValueFactory(data -> data.getValue().levelProperty());
        setupFilterListeners();    

        fromTF.setText("0");
        toTF.setText("0");
    }

    private void setupFilterListeners() {
        nameFilterTF.textProperty().addListener((observable, oldValue, newValue) -> filterPlayers());
        levelFilterTF.textProperty().addListener((observable, oldValue, newValue) -> filterPlayers());
        fromTF.textProperty().addListener((observable, oldValue, newValue) -> filterPlayers());
        toTF.textProperty().addListener((observable, oldValue, newValue) -> filterPlayers());
    }

    private void filterPlayers() {
        String nameFilter = nameFilterTF.getText();
        String levelFilter = levelFilterTF.getText();
        int ageFrom = 0;
        int ageTo = 0;

        if (!fromTF.getText().isEmpty()){
            ageFrom = Integer.parseInt(fromTF.getText());
        }

        if (!toTF.getText().isEmpty()){
            ageTo = Integer.parseInt(toTF.getText());
        }

        filteredPlayers.clear();

        for(Team team : getTeams().currentTeams){
            team.filterPlayersList(nameFilter, levelFilter, ageFrom, ageTo);
            
            filteredPlayers.addAll(team.getPlayers().getFilteredPlayersList());
        }
  
        allPlayersTV.setItems(filteredPlayers);
    }

    @FXML public void handleClose(ActionEvent event){
        stage.close();
    }
}

