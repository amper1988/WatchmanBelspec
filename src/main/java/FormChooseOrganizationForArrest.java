/**
 * Created by amper1988 on 07.03.2017.
 */

import interfaces.ChangerListener;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class FormChooseOrganizationForArrest implements ChangerListener {
    private int identifier;
    private String whoArrested;
    private String arrestReason;
    private ControllerFormCarDetails controllerFormCarDetails;
    private ChangerListener listener;

    public void start(Stage primaryStage) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("form_choose_organization_for_arrest.fxml"));
        try {
            Parent root = (Parent)loader.load();
            ControllerFormChooseOrganizationForArrest controllerFormChooseOrganizationForArrest = loader.getController();
            Stage stage;
            if(primaryStage == null){
                stage = new Stage();
            }else{
                stage = primaryStage;
            }
            stage.setTitle("Электронный учет. Арест автомобиля. Выбор огранизации хранения после ареста.");
            stage.setScene(new Scene(root));
            controllerFormChooseOrganizationForArrest.setData(this.identifier, this.whoArrested, this.arrestReason, this.controllerFormCarDetails, this);
            stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public FormChooseOrganizationForArrest(int identifier, String whoArrested, String arrestReason, ControllerFormCarDetails controllerFormCarDetails, ChangerListener listener){
        this.identifier = identifier;
        this.whoArrested = whoArrested;
        this.arrestReason = arrestReason;
        this.controllerFormCarDetails = controllerFormCarDetails;
        this.listener = listener;
    }

    @Override
    public void onChangeData() {
        if(listener!=null){
            listener.onChangeData();
        }
    }
}
