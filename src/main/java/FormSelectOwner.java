import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import utils.ScreenSize;

import java.io.IOException;

public class FormSelectOwner{

    public void start(Stage primaryStage) throws IOException{
        Stage stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("form_select_parking.fxml"));
        BorderPane root = loader.load();
        root.setId("with_background");
        ControllerSelectOwner controllerSelectOwner = loader.getController();
        if(primaryStage !=null){
            stage = primaryStage;
        }else{
            stage = new Stage();
        }
        stage.setTitle("Электронный учет. Форма выбора стоянки");
        Scene scene = new Scene(root);
        scene.getStylesheets().addAll(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        ScreenSize.getInstance().maximizedStage(stage);
        controllerSelectOwner.setStage(stage);
        stage.show();

    }
}
