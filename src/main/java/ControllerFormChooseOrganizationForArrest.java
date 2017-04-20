import interfaces.ChangerListener;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import model.DataChangeObserver;
import retrofit.Api;
import retrofit.model.ServerAnswer;
import retrofit.model.create_arrest_with_organization_change.request.CreateArrestWithOrganizationChangeRequestEnvelope;
import retrofit.model.create_arrest_with_organization_change.response.CreateArrestWithOrganizationChangeResponseEnvelope;
import retrofit.model.get_organization_for_arrest.request.GetOrganizationForArrestRequestEnvelope;
import retrofit.model.get_organization_for_arrest.response.GetOrganizationForArrestResponseEnvelope;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utils.Converter;
import utils.Encode;
import utils.UserManager;
import utils.Utils;

import java.net.URL;
import java.util.ResourceBundle;


public class ControllerFormChooseOrganizationForArrest implements Initializable {
    @FXML
    private ComboBox<String> cmbOrganization;
    @FXML
    private Button btnArrest;

    private int identifier;
    private String whoArrested;
    private String arrestReason;
    private ControllerFormCarDetails controllerFormCarDetails = null;
    private ChangerListener listener;
    private int getOrganizationForArrestCount = 0;
    private int createArrestCount = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getOrganizationForArrest();

        btnArrest.setOnAction(actionEvent -> {
            if(confirmData()){
                createArrest();
            }else{
                Utils.showAlertMessage("Значение не заполнено", "Выберите организацию");
            }
        });
    }

    private void createArrest() {
        Api.createRetrofitService().executeCreateArrestWithOrganizationChange(Encode.getBasicAuthTemplate(UserManager.getInstanse().getmLogin(), UserManager.getInstanse().getmPassword()),
                new CreateArrestWithOrganizationChangeRequestEnvelope(identifier, whoArrested, arrestReason, cmbOrganization.getSelectionModel().getSelectedItem()))
                .enqueue(new Callback<CreateArrestWithOrganizationChangeResponseEnvelope>() {
                    @Override
                    public void onResponse(Call<CreateArrestWithOrganizationChangeResponseEnvelope> call, final Response<CreateArrestWithOrganizationChangeResponseEnvelope> response) {
                        createArrestCount = 0;
                        if(response.code() == 200){
                            final ServerAnswer serverAnswer = response.body().getServerAnswer();
                            if(serverAnswer.getCode() == 1){
                                Platform.runLater(() -> {
                                    Utils.showAlertMessage("Успешно арестовано.", serverAnswer.getDescription());
                                    DataChangeObserver.getInstance().dataChangeNotify();
                                    controllerFormCarDetails.getActualData();
                                    if(listener != null){
                                        listener.onChangeData();
                                        close();
                                    }
                                });
                            }else{
                                Platform.runLater(() -> Utils.showAlertMessage("Ошибка ответа сервера "+ serverAnswer.getCode(), serverAnswer.getDescription()));
                            }
                        }else{
                            Platform.runLater(() -> Utils.showAlertMessage("Ошибка сервера "+ response.code(), Converter.convertResponseToSting(response.errorBody())));
                        }
                    }

                    @Override
                    public void onFailure(Call<CreateArrestWithOrganizationChangeResponseEnvelope> call, final Throwable t) {
                        if(createArrestCount++ < Main.COUNT_RETRY){
                            createArrest();
                        }else{
                            createArrestCount = 0;
                            Platform.runLater(() -> Utils.showAlertMessage("Ошибка отправления запроса", t.getMessage()));
                        }
                    }
                });
    }

    private void getOrganizationForArrest() {
        Api.createRetrofitService().executeGetOrganizationForArrest(Encode.getBasicAuthTemplate(UserManager.getInstanse().getmLogin(), UserManager.getInstanse().getmPassword()),
                new GetOrganizationForArrestRequestEnvelope()).enqueue(new Callback<GetOrganizationForArrestResponseEnvelope>() {
            @Override
            public void onResponse(Call<GetOrganizationForArrestResponseEnvelope> call, final Response<GetOrganizationForArrestResponseEnvelope> response) {
                getOrganizationForArrestCount = 0;
                if(response.code() == 200){
                    cmbOrganization.setItems(FXCollections.observableArrayList(response.body().getOrganizationListAsString()));
                }else{
                    Platform.runLater(() -> Utils.showAlertMessage("Ошбика сервера "+ response.code(), Converter.convertResponseToSting(response.errorBody())));
                }
            }

            @Override
            public void onFailure(Call<GetOrganizationForArrestResponseEnvelope> call, final Throwable t) {
                if(getOrganizationForArrestCount++ < Main.COUNT_RETRY){
                    getOrganizationForArrest();
                }else{
                    getOrganizationForArrestCount = 0;
                    Platform.runLater(() -> Utils.showAlertMessage("Ошбика отправления запроса", t.getMessage()));
                }

            }
        });
    }

    public void setData(int identifier, String whoArrested, String arrestReason, ControllerFormCarDetails controllerFormCarDetails, ChangerListener listener){
        this.identifier = identifier;
        this.whoArrested = whoArrested;
        this.arrestReason = arrestReason;
        this.controllerFormCarDetails = controllerFormCarDetails;
        this.listener = listener;
    }

    private boolean confirmData(){
        if(cmbOrganization.getSelectionModel().getSelectedItem().isEmpty()){
            return false;
        }
        return true;
    }

    public void close(){
        ((Stage)btnArrest.getScene().getWindow()).close();
    }
}
