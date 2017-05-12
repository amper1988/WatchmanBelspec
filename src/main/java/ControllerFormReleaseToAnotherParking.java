import interfaces.ChangerListener;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.DataChangeObserver;
import retrofit.Api;
import retrofit.model.OrganizationItem;
import retrofit.model.Parking;
import retrofit.model.ServerAnswer;
import retrofit.model.create_release_to_another_parking.request.CreateReleaseToAnotherParkingRequestEnvelope;
import retrofit.model.create_release_to_another_parking.response.CreateReleaseToAnotherParkingResponseEnvelope;
import retrofit.model.get_organizations_with_employers.request.GetOrganizationsWithEmployersRequestEnvelope;
import retrofit.model.get_organizations_with_employers.response.GetOrganizationsWithEmployersResponseEnvelope;
import retrofit.model.get_parkings.request.GetParkingsRequestEnvelope;
import retrofit.model.get_parkings.response.GetParkingsResponseEnvelope;
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

public class ControllerFormReleaseToAnotherParking implements Initializable {
    @FXML
    private TextField txtEvacuationReason;
    @FXML
    private ComboBox<String> cmbWrecker;
    @FXML
    private Button btnSendMove;
    @FXML
    private ComboBox<String> cmbEvacuationOrganization;
    @FXML
    private ComboBox<String> cmbParking;
    @FXML
    private HBox hbWrecker;

    private int identifier;
    private ChangerListener listener;
    private GetOrganizationsWithEmployersResponseEnvelope organizationResponse;
    private GetParkingsResponseEnvelope parkingsResponse;
    private int getParkingsCount;
    private int getOrganizationCount;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getDataFromServer();
        hbWrecker.setVisible(false);
        btnSendMove.setOnAction(actionEvent -> {
            if (confirmData()) {
                Api.createRetrofitService().executeCreateReleaseToAnotherParking(Encode.getBasicAuthTemplate(UserManager.getInstanse().getmLogin(), UserManager.getInstanse().getmPassword()),
                        new CreateReleaseToAnotherParkingRequestEnvelope(identifier, cmbWrecker.getSelectionModel().getSelectedItem(), txtEvacuationReason.getText(), cmbParking.getSelectionModel().getSelectedItem())).enqueue(new Callback<CreateReleaseToAnotherParkingResponseEnvelope>() {
                    @Override
                    public void onResponse(Call<CreateReleaseToAnotherParkingResponseEnvelope> call, final Response<CreateReleaseToAnotherParkingResponseEnvelope> response) {
                        if (response.code() == 200) {
                            final ServerAnswer serverAnswer = response.body().getServerAnswer();

                            if (serverAnswer.getCode() == 1) {

                                Platform.runLater(() -> {
                                    Utils.showAlertMessage("Успешно обработан запрос", serverAnswer.getDescription());
                                    if (listener != null) {
                                        listener.onChangeData();
                                        DataChangeObserver.getInstance().dataChangeNotify();
                                    }
                                    ((Stage) btnSendMove.getScene().getWindow()).close();
                                });
                            } else {
                                Platform.runLater(() -> Utils.showAlertMessage("Ошибка ответа сервера " + serverAnswer.getCode(), serverAnswer.getDescription()));
                            }
                        } else {
                            Platform.runLater(() -> Utils.showAlertMessage("Ошбика сервера " + response.code(), Converter.convertResponseToSting(response.errorBody())));
                        }
                    }

                    @Override
                    public void onFailure(Call<CreateReleaseToAnotherParkingResponseEnvelope> call, final Throwable t) {
                        Platform.runLater(() -> Utils.showAlertMessage("Ошибка отправления запроса.", t.getMessage()));
                    }
                });
            } else {
                Utils.showAlertMessage("Данные не заполнены", "Проверьте введенные данные");
            }
        });
        initialiseComboBoxsEvent();
    }

    public void setData(int identifier, ChangerListener listener) {
        this.identifier = identifier;
        this.listener = listener;

    }

    private void getDataFromServer() {
        getOrganizationWithEmployers();
        getParkings();
    }

    private void getParkings() {
        Api.createRetrofitService().executeGetParkings(Encode.getBasicAuthTemplate(UserManager.getInstanse().getmLogin(), UserManager.getInstanse().getmPassword()),
                new GetParkingsRequestEnvelope()).enqueue(new Callback<GetParkingsResponseEnvelope>() {
            @Override
            public void onResponse(Call<GetParkingsResponseEnvelope> call, final Response<GetParkingsResponseEnvelope> response) {
                getParkingsCount = 0;
                if (response.code() == 200) {
                    parkingsResponse = response.body();
                    cmbParking.setItems(FXCollections.observableArrayList(parkingsResponse.getParkingsAsString()));
                } else {
                    Platform.runLater(() -> Utils.showAlertMessage("Ошбика сервера " + response.code(), Converter.convertResponseToSting(response.errorBody())));
                }
            }

            @Override
            public void onFailure(Call<GetParkingsResponseEnvelope> call, final Throwable t) {
                if (getParkingsCount++ < Main.COUNT_RETRY) {
                    getParkings();
                } else {
                    getParkingsCount = 0;
                    Platform.runLater(() -> Utils.showAlertMessage("Ошибка отправления запроса.", t.getMessage()));
                }
            }
        });
    }

    private void getOrganizationWithEmployers() {
        Api.createRetrofitService().executeGetOrganizationsWithEmployers(Encode.getBasicAuthTemplate(UserManager.getInstanse().getmLogin(), UserManager.getInstanse().getmPassword()),
                new GetOrganizationsWithEmployersRequestEnvelope()).enqueue(new Callback<GetOrganizationsWithEmployersResponseEnvelope>() {
            @Override
            public void onResponse(Call<GetOrganizationsWithEmployersResponseEnvelope> call, final Response<GetOrganizationsWithEmployersResponseEnvelope> response) {
                getOrganizationCount = 0;
                if (response.code() == 200) {
                    organizationResponse = response.body();
                    cmbEvacuationOrganization.setItems(FXCollections.observableArrayList(organizationResponse.getOrganizationListAsString()));
                } else {
                    Platform.runLater(() -> Utils.showAlertMessage("Ошибка сервера " + response.code(), Converter.convertResponseToSting(response.errorBody())));
                }
            }

            @Override
            public void onFailure(Call<GetOrganizationsWithEmployersResponseEnvelope> call, final Throwable t) {
                if (getOrganizationCount++ < Main.COUNT_RETRY) {
                    getOrganizationWithEmployers();
                } else {
                    getOrganizationCount = 0;
                    Platform.runLater(() -> Utils.showAlertMessage("Ошибка при отправлении запроса", t.getMessage()));
                }
            }
        });
    }

    private boolean confirmData() {
        if (txtEvacuationReason.getText().isEmpty()) {
            txtEvacuationReason.requestFocus();
            return false;
        }

        if (cmbEvacuationOrganization.getSelectionModel().getSelectedItem().isEmpty()) {
            cmbEvacuationOrganization.requestFocus();
            return false;
        }

        if (cmbWrecker.getSelectionModel().getSelectedItem().isEmpty()) {
            cmbWrecker.requestFocus();
            return false;
        }

        if (cmbParking.getSelectionModel().getSelectedItem().isEmpty()) {
            cmbParking.requestFocus();
            return false;
        }

        return true;
    }

    private void initialiseComboBoxsEvent() {
        cmbEvacuationOrganization.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            hbWrecker.setVisible(true);
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
                hbWrecker.setVisible(false);
            }
        });

        cmbEvacuationOrganization.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                if (cmbEvacuationOrganization.getSelectionModel().getSelectedItem() == null && !cmbEvacuationOrganization.getEditor().getText().equals("")) {
                    if (cmbWrecker.getItems() != null) {
                        cmbWrecker.getItems().clear();
                    }
                    hbWrecker.setVisible(false);
                    Platform.runLater(() -> {
                        Utils.showAlertMessage("Выберите значение эвакуирующей организации", "Эвакуирующая организация не выбана.");
                        cmbEvacuationOrganization.requestFocus();
                    });
                }
            }
        });

        cmbParking.getEditor().setOnKeyTyped(event -> {
            String searchString = (cmbParking.getEditor().getText() + Utils.returnSymbol(event.getCharacter())).toLowerCase();
            final List<String> foundList = new ArrayList<>();
            List<Parking> parkings = parkingsResponse.getParkings();
            if (parkings != null) {
                for (Parking item : parkings) {
                    if (item.getName().toLowerCase().contains(searchString)) {
                        foundList.add(item.getName());
                    }
                }

            } else {
                foundList.add("");
            }
            Platform.runLater(() -> {
                cmbParking.getItems().clear();
                cmbParking.setItems(FXCollections.observableArrayList(foundList));
                cmbParking.show();
            });
        });

        cmbParking.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                if (cmbParking.getSelectionModel().getSelectedItem() == null && !cmbParking.getEditor().getText().equals("")) {
                    Platform.runLater(() -> {
                        Utils.showAlertMessage("Выберите значение стоянки", "Стоянка не выбана.");
                        cmbParking.requestFocus();
                    });
                }
            }
        });
    }
}
