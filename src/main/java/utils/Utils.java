package utils;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Utils {
    public static void showAlertMessage(String title, String message){

        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
        dialog.setTitle(title);
        dialog.setHeaderText(message);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.showAndWait();
    }

    public static String returnSymbol(String ch){
        if(ch.equals("\n") || ch.equals("\t") || ch.equals("\b") || ch.equals("\r") || ch.hashCode() == 127 || ch.hashCode() == 0){
            return "";
        }
        return ch;
    }
}
