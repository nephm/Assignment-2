package controller;

import java.util.LinkedList;

import au.edu.uts.ap.javafx.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class ErrorController extends Controller<LinkedList<String>>{
    @FXML private Button closeButton;
    @FXML private Text errorText;

    @FXML public void initialize(){
        setErrors();
    }
 
    
    private LinkedList<String> getErrors(){
        return this.model;
    }

    @FXML public void handleClose(){ stage.close(); };

    private void setErrors(){
        String finalString = "";
        for (String string : getErrors()){
            finalString = finalString + string;
        }
        errorText.setText(finalString);
    }
}
