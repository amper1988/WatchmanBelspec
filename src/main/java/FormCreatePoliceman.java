import interfaces.ChangerListener;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class FormCreatePoliceman {
    private ChangerListener listener;
    private String policeDepartment;

    public void start(Stage primaryStage){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("form_create_policeman.fxml"));
        try {
            Parent root = (Parent)loader.load();
            ControllerFormCreatePoliceman controllerFormCreatePoliceman = loader.getController();
            Stage stage;
            if(primaryStage == null){
                stage = new Stage();
            }else{
                stage = primaryStage;
            }
            stage.setTitle("Электронный учет. Добавление сотрудника ГАИ");
            stage.setScene(new Scene(root));
            controllerFormCreatePoliceman.setData(listener, policeDepartment);
            stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public FormCreatePoliceman(ChangerListener listener, String policeDepartment){
        this.listener = listener;
        this.policeDepartment = policeDepartment;
    }


}
