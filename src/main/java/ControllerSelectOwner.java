import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.AutoCompleteBoxWithDep;
import retrofit.Api;
import retrofit.RetrofitService;
import retrofit.model.change_parking.request.ChangeParkingRequestEnvelope;
import retrofit.model.change_parking.response.ChangeParkingResponseEnvelope;
import retrofit.model.get_parking.request.GetParkingRequestEnvelope;
import retrofit.model.get_parking.response.GetParkingResponseEnvelope;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utils.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerSelectOwner implements Initializable{
    @FXML
    private ComboBox<String> chbParking;
    @FXML
    private Button btnNext;
    @FXML
    private Text txtResult;
    @FXML
    private BorderPane brdpFull;

    private Stage primaryStage;
    private FormLoading formLoading;
    private int getParkingCount = 0;
    private int sendDataCount = 0;


    private RetrofitService retrofitService;

    private void getParking(){
        try{
            blockUI(true, "Получение списка стоянок");
            retrofitService = Api.createRetrofitService();
            retrofitService.executeGetParkingList(Encode.getBasicAuthTemplate(UserManager.getInstanse().getmLogin(), UserManager.getInstanse().getmPassword()),
                    new GetParkingRequestEnvelope(UserManager.getInstanse().getOrganization())).enqueue(new Callback<GetParkingResponseEnvelope>() {
                @Override
                public void onResponse(Call<GetParkingResponseEnvelope> call, Response<GetParkingResponseEnvelope> response) {
                    //unblock UI, because get response from server;
                    blockUI(false, "Список стоянок получен");
                    getParkingCount = 0;
                    if(response.code() == 200){
                        //response is good;
                        GetParkingResponseEnvelope responseEnvelope = response.body();
                        Platform.runLater(() -> {

                            new AutoCompleteBoxWithDep(chbParking, null , responseEnvelope.getBody().getParkingList());
                            chbParking.requestFocus();
                        });
                    }else{
                        //response is bad;
                        txtResult.setText(Converter.convertResponseToSting(response.errorBody()));
                    }
                }

                @Override
                public void onFailure(Call<GetParkingResponseEnvelope> call, Throwable t) {
                    //unblock UI because request is fail.
                    if(getParkingCount++ < Main.COUNT_RETRY){
                        getParking();
                    }else{
                        getParkingCount = 0;
                        blockUI(false, "Списко стоянок не получен.");
                        txtResult.setText(t.getMessage());
                    }
                }
            });
        }catch (Exception e){
            blockUI(false, "Общая ошибка");
            txtResult.setText(e.getMessage());
        }
    }
    @SuppressWarnings("unchecked")
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //block UI before going to server;
        blockUI(true, "Получение списка стоянок.");
        //go to server for get information about available parking for user;
        getParking();
        chbParking.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            if(event.getCode() == KeyCode.ENTER)
                btnNext.requestFocus();
        });
        //on event send to server information about user and his new parking;
        btnNext.setOnAction(event -> sendData());
        btnNext.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            if(event.getCode() == KeyCode.ENTER)
                btnNext.fire();
        });
    }

    private void sendData(){
        //block UI before going to server;
        blockUI(true, "Изменение данных о сотруднике.");
        //save information about parking in UserManager;
        if(chbParking.getSelectionModel().isEmpty())
            Platform.runLater(()-> Utils.showAlertMessage("Пустые данные", "Вероятно вы забыли выбрать стоянку"));
        else
            UserManager.getInstanse().setParking(chbParking.getSelectionModel().getSelectedItem());
            //go to server and change parking for user to selected item;
            retrofitService.executeChangeParkingOperation(Encode.getBasicAuthTemplate(UserManager.getInstanse().getmLogin(), UserManager.getInstanse().getmPassword()),
                    new ChangeParkingRequestEnvelope(UserManager.getInstanse().getmParking())).enqueue(new Callback<ChangeParkingResponseEnvelope>() {
                @Override
                public void onResponse(Call<ChangeParkingResponseEnvelope> call, final Response<ChangeParkingResponseEnvelope> response) {
                    //unblock UI after taking response

                    sendDataCount =0;
                    if(response.code() == 200){
                        //response is good;
                        if(response.body().getServerAnswer().getCode() == 1){
                            //response without errors from server;
                            Platform.runLater(() -> {
                                try{
                                    //show watchman's form;
                                    FormWatchman frm = new FormWatchman();
                                    frm.start(primaryStage);
                                }catch (IOException e ){
                                    e.printStackTrace();
                                }
                            });
                        } else {
                            //response with errors from server;
                            blockUI(false, "Ошибка при попытке изменить данные сотрудника.");
                            Platform.runLater(() -> Utils.showAlertMessage("Error code: " + response.body().getServerAnswer().getCode(), response.body().getServerAnswer().getDescription()));
                        }
                    }else{
                        //response is bad;
                        blockUI(false, "Ошибка при попытке изменить данные сотрудника");
                        Platform.runLater(() -> Utils.showAlertMessage("Ошибка при попытке записать стоянку.", "Код ошибки: " + response.code()+
                                "\n Описание ошибки: " + Converter.convertResponseToSting(response.errorBody())));
                    }
                }
                @Override
                public void onFailure(Call<ChangeParkingResponseEnvelope> call, final Throwable t) {
                    if(sendDataCount++ < Main.COUNT_RETRY){
                        sendData();
                    }else{
                        //unblock UI because request is fail;
                        sendDataCount = 0;
                        blockUI(false, "Ошибка при поытке изменить данные сотрудника.");
                        Platform.runLater(() -> {
                            if(t.getMessage().contains("null"))
                                //contains null => argument 'parking' null;
                                Utils.showAlertMessage("Fail reqest", "Вероятно вы забыли выбрать стоянку");
                            else
                                //request is fail;
                                Utils.showAlertMessage("Fail request.", "Error: "+ t.getMessage());
                        });
                    }
                }
            });
    }
    /**
     * Block or unblock UI components depends on "block" argument
     * @param block  boolean (set "true" to block, set "false" to unblock)
     */
    private void blockUI(boolean block, String info){
         if(block){
            Platform.runLater(()->{
                if(formLoading == null){
                    formLoading = new FormLoading();
                }
                try {
                    formLoading.start(primaryStage, info);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }else{
            Platform.runLater(() -> {
                if(formLoading!= null){
                    try {
                        formLoading.stop(info);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                formLoading = null;
            });

        }
        brdpFull.setVisible(!block);
        chbParking.setDisable(block);
        btnNext.setDisable(block);
    }

    public void setStage(Stage stage){
        this.primaryStage = stage;
    }
}
