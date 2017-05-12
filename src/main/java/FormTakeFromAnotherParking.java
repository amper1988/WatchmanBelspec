/**
 * Created by amper1988 on 09.03.2017.
 */

import interfaces.ChangerListener;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class FormTakeFromAnotherParking {
    private int identifier;
    private ChangerListener listener;

    public void start(Stage primaryStage) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("form_take_from_another_parking.fxml"));
        try {
            Parent root = (Parent) loader.load();
            ControllerFormTakeFromAnotherParking controllerFormTakeFromAnotherParking = loader.getController();
            Stage stage;
            if (primaryStage == null) {
                stage = new Stage();
            } else {
                stage = primaryStage;
            }
            stage.setTitle("Электронный учет. Принятие перемещаемого транспортного средства.");
            stage.setScene(new Scene(root));
            controllerFormTakeFromAnotherParking.setData(identifier, listener);
            stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public FormTakeFromAnotherParking(int identifier, ChangerListener listener) {
        this.identifier = identifier;
        this.listener = listener;
    }
}
