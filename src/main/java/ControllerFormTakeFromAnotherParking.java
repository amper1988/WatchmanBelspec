import interfaces.ChangerListener;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import model.DataChangeObserver;
import retrofit.Api;
import retrofit.model.OrganizationItem;
import retrofit.model.ServerAnswer;
import retrofit.model.create_take_from_another_parking.request.CreateTakeFromAnotherParkingRequestEnvelope;
import retrofit.model.create_take_from_another_parking.response.CreateTakeFromAnotherParkingResponseEnvelope;
import retrofit.model.get_organizations_with_employers.request.GetOrganizationsWithEmployersRequestEnvelope;
import retrofit.model.get_organizations_with_employers.response.GetOrganizationsWithEmployersResponseEnvelope;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utils.Converter;
import utils.Encode;
import utils.UserManager;
import utils.Utils;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class ControllerFormTakeFromAnotherParking implements Initializable {
    @FXML
    private ComboBox<String> cmbEvacuationOrganization;
    @FXML
    private ComboBox<String> cmbWrecker;
    @FXML
    private Button btnTakeToParking;

    private ChangerListener listener;
    private int identifier;
    private GetOrganizationsWithEmployersResponseEnvelope organizationResponse;
    private int takeFromAnotherParkingCount;
    private int getDataFromServerCount;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbWrecker.setVisible(false);
        getDataFromServer();
        initializeComboBoxsEvent();
        btnTakeToParking.setOnAction(actionEvent -> {
            if (confirmData()) {
                createTakeFromAnotherParking();
            } else {
                Utils.showAlertMessage("Данные не заполнены", "Проверьте введенные данные.");
            }

        });
    }

    private void createTakeFromAnotherParking() {
        Api.createRetrofitService().executeCreateTakeFromAnotherParking(Encode.getBasicAuthTemplate(UserManager.getInstanse().getmLogin(), UserManager.getInstanse().getmPassword()),
                new CreateTakeFromAnotherParkingRequestEnvelope(identifier, cmbWrecker.getSelectionModel().getSelectedItem())).enqueue(new Callback<CreateTakeFromAnotherParkingResponseEnvelope>() {
            @Override
            public void onResponse(Call<CreateTakeFromAnotherParkingResponseEnvelope> call, final Response<CreateTakeFromAnotherParkingResponseEnvelope> response) {
                takeFromAnotherParkingCount = 0;
                if (response.code() == 200) {
                    final ServerAnswer serverAnswer = response.body().getServerAnswer();
                    if (serverAnswer.getCode() == 1) {
                        Platform.runLater(() -> {
                            Utils.showAlertMessage("Запрос произведен успешно.", serverAnswer.getDescription());
                            listener.onChangeData();
                            DataChangeObserver.getInstance().dataChangeNotify();
                            ((Stage) btnTakeToParking.getScene().getWindow()).close();
                        });
                    } else {
                        Platform.runLater(() -> Utils.showAlertMessage("Ошбика ответа сервера " + serverAnswer.getCode(), serverAnswer.getDescription()));
                    }
                } else {
                    Platform.runLater(() -> Utils.showAlertMessage("Ошбика сервера " + response.code(), Converter.convertResponseToSting(response.errorBody())));
                }
            }

            @Override
            public void onFailure(Call<CreateTakeFromAnotherParkingResponseEnvelope> call, final Throwable t) {
                if (takeFromAnotherParkingCount++ < Main.COUNT_RETRY) {
                    createTakeFromAnotherParking();
                } else {
                    takeFromAnotherParkingCount = 0;
                    Platform.runLater(() -> Utils.showAlertMessage("Ошбика отправления запроса", t.getMessage()));
                }

            }
        });
    }

    private void getDataFromServer() {
        Api.createRetrofitService().executeGetOrganizationsWithEmployers(Encode.getBasicAuthTemplate(UserManager.getInstanse().getmLogin(), UserManager.getInstanse().getmPassword()),
                new GetOrganizationsWithEmployersRequestEnvelope()).enqueue(new Callback<GetOrganizationsWithEmployersResponseEnvelope>() {
            @Override
            public void onResponse(Call<GetOrganizationsWithEmployersResponseEnvelope> call, final Response<GetOrganizationsWithEmployersResponseEnvelope> response) {
                getDataFromServerCount = 0;
                if (response.code() == 200) {
                    organizationResponse = response.body();
                    cmbEvacuationOrganization.setItems(FXCollections.observableArrayList(organizationResponse.getOrganizationListAsString()));
                } else {
                    Platform.runLater(() -> Utils.showAlertMessage("Ошбика сервера " + response.code(), Converter.convertResponseToSting(response.errorBody())));
                }
            }

            @Override
            public void onFailure(Call<GetOrganizationsWithEmployersResponseEnvelope> call, final Throwable t) {
                if (getDataFromServerCount++ < Main.COUNT_RETRY) {
                    getDataFromServer();
                } else {
                    getDataFromServerCount = 0;
                    Platform.runLater(() -> Utils.showAlertMessage("Ошибка отправления запроса.", t.getMessage()));
                }

            }
        });
    }

    public void setData(int identifier, ChangerListener listener) {
        this.identifier = identifier;
        this.listener = listener;
    }

    private void initializeComboBoxsEvent() {
        cmbEvacuationOrganization.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            cmbWrecker.setVisible(true);
            List<OrganizationItem> organizationItems = organizationResponse.getOrganizationItemList();
            if (organizationItems != null) {
                for (OrganizationItem item : organizationItems) {
                    if (item.getName().equals(newValue)) {
                        cmbWrecker.setItems(FXCollections.observableArrayList(item.getWreckerListAsString()));
                    }
                }
            }
        });

        cmbEvacuationOrganization.getEditor().setOnKeyTyped(event -> {
            String searchString = (cmbEvacuationOrganization.getEditor().getText() + Utils.returnSymbol(event.getCharacter())).toLowerCase();
            final List<String> foundList = new ArrayList<>();
            List<OrganizationItem> organizationItems = organizationResponse.getOrganizationItemList();
            if (organizationItems != null) {
                for (OrganizationItem item : organizationItems) {
                    if (item.getName().toLowerCase().contains(searchString)) {
                        foundList.add(item.getName());
                    }
                }

            } else {
                foundList.add("");
            }
            Platform.runLater(() -> {
                cmbEvacuationOrganization.getItems().clear();
                cmbEvacuationOrganization.setItems(FXCollections.observableArrayList(foundList));
                cmbEvacuationOrganization.show();
            });
            if (searchString.equals("") || searchString.equals(" ")) {
                if (cmbWrecker.getItems() != null) {
                    cmbWrecker.getItems().clear();
                }
                cmbWrecker.setVisible(false);
            }
        });

        cmbEvacuationOrganization.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                if (cmbEvacuationOrganization.getSelectionModel().getSelectedItem() == null && !cmbEvacuationOrganization.getEditor().getText().equals("")) {
                    if (cmbWrecker.getItems() != null) {
                        cmbWrecker.getItems().clear();
                    }
                    cmbWrecker.setVisible(false);
                    Platform.runLater(() -> {
                        Utils.showAlertMessage("Выберите значение эвакуирующей организации", "Эвакуирующая организация не выбана.");
                        cmbEvacuationOrganization.requestFocus();
                    });
                }
            }
        });
    }

    private boolean confirmData() {
        if (cmbEvacuationOrganization.getSelectionModel().getSelectedItem().isEmpty())
            return false;
        if (cmbWrecker.getSelectionModel().getSelectedItem().isEmpty())
            return false;
        return true;
    }
}
