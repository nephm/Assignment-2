package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Record;
import model.Season;

public class RecordController extends Controller<Season> {
    @FXML TableView<Record> recordTableTV;
    @FXML TableColumn<Record, String> roundCol;
    @FXML TableColumn<Record, String> gameCol;
    @FXML TableColumn<Record, String> winCol;
    @FXML TableColumn<Record, String> loseCol;
    @FXML Button closeButton;

    private Season getSeason(){
        return this.model;
    }

    @FXML private void initialize(){
        recordTableTV.setItems(getSeason().record());
        recordTableTV.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        roundCol.setCellValueFactory(data -> data.getValue().roundProperty().asString());
        gameCol.setCellValueFactory(data -> data.getValue().gameNumberProperty().asString());
        winCol.setCellValueFactory(data -> data.getValue().winTeamProperty());
        loseCol.setCellValueFactory(data -> data.getValue().loseTeamProperty());
    }

    @FXML public void handleClose(){ stage.close(); }
}