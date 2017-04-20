import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import utils.*;

import java.io.IOException;

public class FormGetToParking {
    private int identifier = -1;

    public  void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("form_get_to_parking.fxml"));
        AnchorPane root = loader.load();
        root.setId("with_background");
        ControllerFormGetToParking controllerFormGetToParking = loader.getController();
        Stage stage;
        if(primaryStage!=null){
            stage = primaryStage;
        }else{
            stage = new Stage();
        }
        stage.setTitle("Электронный учет. Принятие на стоянку.");
        Scene scene = new Scene(root);
        scene.getStylesheets().addAll(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        ScreenSize.getInstance().maximizedStage(stage);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));

        stage.show();
        controllerFormGetToParking.setFocusDefault();
        controllerFormGetToParking.setIdentifier(identifier, stage);
    }

    public void setIdentifier(int identifier){
        this.identifier = identifier;
    }


}
