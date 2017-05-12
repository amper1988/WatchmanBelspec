/**
 * Created by amper1988 on 03.03.2017.
 */

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class FormReleaseCar {
    private int identifier = -1;
    private ControllerFormCarDetails controllerFormCarDetails = null;


    public void start(Stage primaryStage) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("form_release_car.fxml"));
        try {
            Parent root = (Parent) loader.load();
            ControllerFormReleaseCar controllerFormReleaseCar = loader.getController();
            Stage stage;
            if (primaryStage == null) {
                stage = new Stage();

            } else {
                stage = primaryStage;
            }
            stage.setTitle("Электронный учет. Выдача транспортного средства. Ввод данных квитанций.");
            stage.setScene(new Scene(root));
            if (this.identifier != -1) {
                controllerFormReleaseCar.setIdentifier(identifier, controllerFormCarDetails);
            }
            stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public FormReleaseCar(int identifier, ControllerFormCarDetails controllerFormCarDetails) {
        super();
        this.identifier = identifier;
        this.controllerFormCarDetails = controllerFormCarDetails;
    }
}
