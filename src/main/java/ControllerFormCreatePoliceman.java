import interfaces.ChangerListener;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import retrofit.Api;
import retrofit.model.ServerAnswer;
import retrofit.model.create_policeman.request.CreatePolicemanRequestEnvelope;
import retrofit.model.create_policeman.response.CreatePolicemanResponseEnvelope;
import retrofit.model.get_police_department.request.GetPoliceDepartmentRequestEnvelope;
import retrofit.model.get_police_department.response.GetPoliceDepartmentResponseEnvelope;
import retrofit.model.get_positions.request.GetPositionsRequestEnvelope;
import retrofit.model.get_positions.response.GetPositionsResponseEnvelope;
import retrofit.model.get_ranks.request.GetRanksRequestEnvelope;
import retrofit.model.get_ranks.response.GetRanksResponseEnvelope;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utils.Converter;
import utils.Encode;
import utils.UserManager;
import utils.Utils;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerFormCreatePoliceman implements Initializable {
    @FXML
    private ComboBox<String> cmbPoliceDepartment;
    @FXML
    private ComboBox<String> cmbRank;
    @FXML
    private ComboBox<String> cmbPosition;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtCode;
    @FXML
    private Button btnRegister;

    private ChangerListener listener;
    private String policeDepartment;
    private int getPositionsCount = 0;
    private int getRanksCount = 0;
    private int getPoliceDepartmentsCount = 0;
    private int createPolicemanCount;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnRegister.setOnAction(actionEvent -> {
            if (confirmData()) {
                createPoliceman();
            } else {
                Platform.runLater(() -> Utils.showAlertMessage("Данные не заполнены", "Проверьте введенные данные"));
            }
        });
    }

    private void createPoliceman() {
        Api.createRetrofitService().executeCreatePoliceman(Encode.getBasicAuthTemplate(UserManager.getInstanse().getmLogin(), UserManager.getInstanse().getmPassword()),
                new CreatePolicemanRequestEnvelope(cmbPoliceDepartment.getSelectionModel().getSelectedItem(),
                        cmbRank.getSelectionModel().getSelectedItem(),
                        cmbPosition.getSelectionModel().getSelectedItem(),
                        txtName.getText(),
                        txtCode.getText()))
                .enqueue(new Callback<CreatePolicemanResponseEnvelope>() {
                    @Override
                    public void onResponse(Call<CreatePolicemanResponseEnvelope> call, final Response<CreatePolicemanResponseEnvelope> response) {
                        createPolicemanCount = 0;
                        if (response.code() == 200) {
                            final ServerAnswer serverAnswer = response.body().getServerAnswer();
                            if (serverAnswer.getCode() == 1) {
                                Platform.runLater(() -> {
                                    Utils.showAlertMessage("Запрос прошел успешно", serverAnswer.getDescription());
                                    if (listener != null) {
                                        listener.onChangeData();
                                    }
                                    ((Stage) btnRegister.getScene().getWindow()).close();
                                });
                            } else {
                                Platform.runLater(() -> Utils.showAlertMessage("Ошбика ответа сервера " + serverAnswer.getCode(), serverAnswer.getDescription()));
                            }
                        } else {
                            Platform.runLater(() -> Utils.showAlertMessage("Ошбика сервера " + response.code(), Converter.convertResponseToSting(response.errorBody())));
                        }
                    }

                    @Override
                    public void onFailure(Call<CreatePolicemanResponseEnvelope> call, final Throwable t) {
                        if (createPolicemanCount++ < Main.COUNT_RETRY) {
                            createPoliceman();
                        } else {
                            createPolicemanCount = 0;
                            Platform.runLater(() -> Utils.showAlertMessage("Ошибка отправления запроса", t.getMessage()));
                        }

                    }
                });
    }

    public void setData(ChangerListener listener, String policeDepartment) {
        this.listener = listener;
        this.policeDepartment = policeDepartment;
        getDataFromServer();
    }

    private boolean confirmData() {
        if (cmbPoliceDepartment.getSelectionModel().getSelectedItem() == null)
            return false;
        if (cmbPosition.getSelectionModel().getSelectedItem() == null)
            return false;
        if (cmbRank.getSelectionModel().getSelectedItem() == null)
            return false;
        if (txtCode.getText().isEmpty())
            return false;
        if (txtName.getText().isEmpty())
            return false;
        return true;
    }

    private void getDataFromServer() {
        getPoliceDepartments();
        getRanks();
        getPositions();
    }

    private void getPositions() {
        Api.createRetrofitService().executeGetPositions(Encode.getBasicAuthTemplate(UserManager.getInstanse().getmLogin(), UserManager.getInstanse().getmPassword()),
                new GetPositionsRequestEnvelope()).enqueue(new Callback<GetPositionsResponseEnvelope>() {
            @Override
            public void onResponse(Call<GetPositionsResponseEnvelope> call, final Response<GetPositionsResponseEnvelope> response) {
                getPositionsCount = 0;
                if (response.code() == 200) {
                    cmbPosition.setItems(FXCollections.observableArrayList(response.body().getPositionsAsString()));
                } else {
                    Platform.runLater(() -> Utils.showAlertMessage("Ошибка сервера " + response.code(), Converter.convertResponseToSting(response.errorBody())));
                }
            }

            @Override
            public void onFailure(Call<GetPositionsResponseEnvelope> call, final Throwable t) {
                if (getPositionsCount++ < Main.COUNT_RETRY) {
                    getPositions();
                } else {
                    getPositionsCount = 0;
                    Platform.runLater(() -> Utils.showAlertMessage("Ошибка отправления запроса", t.getMessage()));
                }
            }
        });
    }

    private void getRanks() {
        Api.createRetrofitService().executeGetRanks(Encode.getBasicAuthTemplate(UserManager.getInstanse().getmLogin(), UserManager.getInstanse().getmPassword()),
                new GetRanksRequestEnvelope()).enqueue(new Callback<GetRanksResponseEnvelope>() {
            @Override
            public void onResponse(Call<GetRanksResponseEnvelope> call, final Response<GetRanksResponseEnvelope> response) {
                getRanksCount = 0;
                if (response.code() == 200) {
                    cmbRank.setItems(FXCollections.observableArrayList(response.body().getRankItemsAsString()));
                } else {
                    Platform.runLater(() -> Utils.showAlertMessage("Ошибка сервера " + response.code(), Converter.convertResponseToSting(response.errorBody())));
                }
            }

            @Override
            public void onFailure(Call<GetRanksResponseEnvelope> call, final Throwable t) {
                if (getRanksCount++ < Main.COUNT_RETRY) {
                    getRanks();
                } else {
                    getRanksCount = 0;
                    Platform.runLater(() -> Utils.showAlertMessage("Ошибка отправления запроса", t.getMessage()));
                }

            }
        });
    }

    private void getPoliceDepartments() {
        Api.createRetrofitService().executeGetPoliceDepartment(Encode.getBasicAuthTemplate(UserManager.getInstanse().getmLogin(), UserManager.getInstanse().getmPassword()),
                new GetPoliceDepartmentRequestEnvelope()).enqueue(new Callback<GetPoliceDepartmentResponseEnvelope>() {
            @Override
            public void onResponse(Call<GetPoliceDepartmentResponseEnvelope> call, final Response<GetPoliceDepartmentResponseEnvelope> response) {
                getPoliceDepartmentsCount = 0;
                if (response.code() == 200) {
                    Platform.runLater(() -> {
                        cmbPoliceDepartment.setItems(FXCollections.observableArrayList(response.body().getPoliceDepartmentAsStirng()));
                        cmbPoliceDepartment.getSelectionModel().select(policeDepartment);
                    });
                } else {
                    Platform.runLater(() -> Utils.showAlertMessage("Ошибка сервера " + response.code(), Converter.convertResponseToSting(response.errorBody())));
                }
            }

            @Override
            public void onFailure(Call<GetPoliceDepartmentResponseEnvelope> call, final Throwable t) {
                if (getPoliceDepartmentsCount++ < Main.COUNT_RETRY) {
                    getPoliceDepartments();
                } else {
                    getPoliceDepartmentsCount = 0;
                    Platform.runLater(() -> Utils.showAlertMessage("Ошибка отправления запроса", t.getMessage()));
                }

            }
        });
    }
}
