import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utils.ScreenSize;

import java.io.IOException;

public class FormCarListFromBase {

    private String param;
    private FormCarListFromBase app;

    public void start(Stage primaryStage) throws IOException {
        app = this;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("form_car_list_from_base.fxml"));
        AnchorPane root = loader.load();
        root.setId("with_background");
        ControllerFormCarListFromBase controllerFormCarListFromBase = loader.getController();
        Stage stage;
        if(primaryStage != null)
            stage = primaryStage;
        else
            stage = new Stage();
        stage.setTitle("Электронный учет. Найденные значения в базе");
        Scene scene = new Scene(root);
        scene.getStylesheets().addAll(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        controllerFormCarListFromBase.setIdentifier(param, stage);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        stage.show();

    }

    public FormCarListFromBase(String param){
        this.param = param;
    }
}
