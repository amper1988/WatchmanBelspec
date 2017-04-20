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

public class FormReleaseByDebtAct{
    private int identifier;
    private ChangerListener listener;

    public void start(Stage primaryStage) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("form_release_by_debt_act.fxml"));
        try {
            Parent root = (Parent)loader.load();
            ControllerFormReleaseByDebtAct controllerFormReleaseByDebtAct = loader.getController();
            Stage stage;
            if(primaryStage == null){
                stage = new Stage();
            }else{
                stage = primaryStage;
            }
            stage.setTitle("Электронный учет. Выдача по долговому акту.");
            stage.setScene(new Scene(root));
            controllerFormReleaseByDebtAct.setData(identifier, listener);
            stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public FormReleaseByDebtAct(int identifier, ChangerListener listener){
        this.identifier = identifier;
        this.listener = listener;
    }
}
