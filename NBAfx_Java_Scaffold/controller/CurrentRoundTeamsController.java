package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Game;
import model.Season;

public class CurrentRoundTeamsController extends Controller<Season> {
    @FXML TableView<Game> gamesTV;
    @FXML TableColumn<Game, String> firstCol;
    @FXML TableColumn<Game, String> secondCol;
    @FXML TableColumn<String, String> vsCol;
    @FXML Button closeButton;
    @FXML Label numOfRoundLB;

    private Season getSeason(){
        return this.model;
    }

    @FXML public void initialize(){
        numOfRoundLB.setText("Round: " + getSeason().round());
        
        gamesTV.setItems(getSeason().getCurrentSchedule());
        gamesTV.setPlaceholder(new Label("No teams to show"));
        gamesTV.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        firstCol.setCellValueFactory(data -> data.getValue().team1());
        secondCol.setCellValueFactory(data -> data.getValue().team2());
        vsCol.setCellValueFactory(cellData -> new SimpleStringProperty("vs"));
    }
    
    @FXML public void handleClose(ActionEvent event){ stage.close();}
}
