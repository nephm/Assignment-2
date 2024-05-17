package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Game;
import model.Season;
import model.Team;

public class TeamsRoundController extends Controller<Season> {
    @FXML ListView<Team> listOfTeamsLV;
    @FXML TableView<Game> tableOfGamesTV;
    @FXML TableColumn<Game, String> gameCol;
    @FXML TableColumn<Game, String> teamOneCol;
    @FXML TableColumn<Game, String> teamTwoCol;
    @FXML Button addButton;
    @FXML Button arrangeButton;
    @FXML Label numOfRoundLB;

    private Season getSeason(){
        return this.model;
    }

    @FXML public void initialize(){
        numOfRoundLB.setText("Round: " + getSeason().round());
        listOfTeamsLV.setItems(getSeason().getCurrentTeams());
        listOfTeamsLV.setPlaceholder(new Label("All teams added to round."));
        listOfTeamsLV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listOfTeamsLV.getSelectionModel().selectedIndexProperty().addListener(
        (observable, oldTeam, newTeam) -> {
            if (listOfTeamsLV.getItems().isEmpty()) {
                addButton.setDisable(true);
                arrangeButton.setDisable(false);
            } else if(listOfTeamsLV.getSelectionModel().isEmpty()){
                addButton.setDisable(true);
            } else if(listOfTeamsLV.getSelectionModel().getSelectedIndices().size()==1){
                arrangeButton.setDisable(true);
                addButton.setDisable(true);
            } else {
                arrangeButton.setDisable(true);
                addButton.setDisable(false);
            }
        }
        );

        tableOfGamesTV.setItems(getSeason().getCurrentSchedule());
        tableOfGamesTV.setPlaceholder(new Label("No teams added to round"));
        tableOfGamesTV.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        gameCol.setCellValueFactory(data -> data.getValue().termProperty().asString());
        teamOneCol.setCellValueFactory(data -> data.getValue().team1());
        teamTwoCol.setCellValueFactory(data -> data.getValue().team2());
    }
    @FXML public void handleAdd(ActionEvent event){
        getSeason().addTeams(listOfTeamsLV.getSelectionModel().getSelectedItems());
    }
    @FXML public void handleArrange(ActionEvent event){ stage.close();}
}