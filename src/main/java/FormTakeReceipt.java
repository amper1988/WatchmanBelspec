import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utils.ScreenSize;

import java.io.IOException;

public class FormTakeReceipt {
    private int identifier = -1;
    private ControllerFormCarDetails controllerFormCarDetails = null;
    private String carId;

    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("form_create_receipt.fxml"));
        AnchorPane root = loader.load();
        root.setId("with_background");
        ControllerFormTakeReceipt controller = loader.getController();
        Stage stage;
        if (primaryStage != null) {
            stage = primaryStage;
        } else {
            stage = new Stage();
        }
        stage.setTitle("Электронный учет. Выствление счета.");
        Scene scene = new Scene(root);
        scene.getStylesheets().addAll(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        ScreenSize.getInstance().maximizedStage(stage);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        if (identifier != -1) {
            controller.setIdentifier(identifier, stage, controllerFormCarDetails, carId);
        }

        stage.show();

    }

    public void setIdentifier(int identifier, ControllerFormCarDetails controllerFormCarDetails, String carId) {
        this.identifier = identifier;
        this.controllerFormCarDetails = controllerFormCarDetails;
        this.carId = carId;
    }

    public FormTakeReceipt(int identifier, ControllerFormCarDetails controllerFormCarDetails, String carId) {
        this.identifier = identifier;
        this.controllerFormCarDetails = controllerFormCarDetails;
        this.carId = carId;
    }

}
