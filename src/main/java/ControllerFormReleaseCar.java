import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.DataChangeObserver;
import model.FileManager;
import retrofit.Api;
import retrofit.RetrofitService;
import retrofit.model.ServerAnswer;
import retrofit.model.create_recheck_receipt.request.CreateRecheckReceiptRequestEnvelope;
import retrofit.model.create_recheck_receipt.response.CreateRecheckReceiptResponseEnvelope;
import retrofit.model.create_release.request.CreateReleaseRequestEnvelope;
import retrofit.model.create_release.response.CreateReleaseResponseEnvelope;
import retrofit.model.create_release_without_recheck.request.CreateReleaseWithoutRecheckRequestEnvelope;
import retrofit.model.create_release_without_recheck.response.CreateReleaseWithoutRecheckResponseEnvelope;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utils.Converter;
import utils.Encode;
import utils.UserManager;
import utils.Utils;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControllerFormReleaseCar implements Initializable {
    private int identifier = -1;
    private ControllerFormCarDetails controllerFormCarDetails;
    @FXML
    private TextField txtBankReceiptNumber;
    @FXML
    private TextField txtPoliceDepartmentReceiptNumber;
    @FXML
    private Button btnReleaseCar;

    private int releaseCount = 0;
    private int withoutRecheckCount = 0;
    private int recheckCount = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnReleaseCar.setOnAction(actionEvent -> {
            if (confirmData() && identifier != -1) {
                sendReleaseData();
            }
        });
    }

    private boolean confirmData() {
        if (txtBankReceiptNumber.getText().isEmpty()) {
            return false;
        }
        if (txtPoliceDepartmentReceiptNumber.getText().isEmpty()) {
            return false;
        }
        return true;
    }

    public void setIdentifier(int identifier, ControllerFormCarDetails controllerFormCarDetails) {
        this.identifier = identifier;
        this.controllerFormCarDetails = controllerFormCarDetails;
    }

    private void sendReleaseData() {
        controllerFormCarDetails.blockUI(true, "");
        RetrofitService retrofitService = Api.createRetrofitService();
        retrofitService.executeCreateRelease(Encode.getBasicAuthTemplate(UserManager.getInstanse().getmLogin(), UserManager.getInstanse().getmPassword()),
                new CreateReleaseRequestEnvelope(this.identifier, txtBankReceiptNumber.getText(), txtPoliceDepartmentReceiptNumber.getText())).enqueue(new Callback<CreateReleaseResponseEnvelope>() {
            @Override
            public void onResponse(Call<CreateReleaseResponseEnvelope> call, final Response<CreateReleaseResponseEnvelope> response) {
                releaseCount = 0;
                if (response.code() == 200) {
                    controllerFormCarDetails.blockUI(false, "");
                    final ServerAnswer serverAnswer = response.body().getServerAnswer();
                    if (serverAnswer.getCode() == 1) {
                        try {
                            final File pdfFile = FileManager.createPdfFile();
                            FileOutputStream fos = new FileOutputStream(pdfFile);
                            byte[] fileContent = Converter.convertBase64StringToByteArray(serverAnswer.getDescription());
                            fos.write(fileContent);
                            fos.close();
                            Desktop.getDesktop().open(pdfFile);
                            Platform.runLater(() -> Utils.showAlertMessage("Запрос выполнен успешно", "Если документ не открылся автоматически то его можно найти по пути " + pdfFile.getAbsolutePath()));
                            Platform.runLater(() -> close());
                            controllerFormCarDetails.getActualData();
                            DataChangeObserver.getInstance().dataChangeNotify();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    } else if (serverAnswer.getCode() == 5) {
                        Platform.runLater(() -> {
                            Alert alertDialog = new Alert(Alert.AlertType.INFORMATION);
                            alertDialog.setTitle("Выберите действие");
                            alertDialog.setHeaderText(serverAnswer.getDescription());
                            alertDialog.setContentText("Нажмите 'Выставить новый счет' для выставления нового счета, 'Без выставления новго счета' для попытки выдать без нового счета.");
                            alertDialog.initModality(Modality.APPLICATION_MODAL);
                            alertDialog.getButtonTypes().clear();
                            ButtonType buttonRecheck = new ButtonType("Выставить новый счет");
                            ButtonType buttonWithoutRecheck = new ButtonType("Без выставления новго счета");
                            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                            alertDialog.getButtonTypes().addAll(buttonRecheck, buttonWithoutRecheck, buttonTypeCancel);

                            Optional<ButtonType> result = alertDialog.showAndWait();
                            if (result.get() == buttonRecheck) {
                                sendRecheckReceipt();
                            } else if (result.get() == buttonWithoutRecheck) {
                                if (confirmData()) {
                                    sendReleaseDataWithoutRecheck();
                                } else {
                                    Platform.runLater(() -> Utils.showAlertMessage("Данные не заполнены.", "Проверьте данные"));
                                }
                            }
                        });

                    } else {
                        Platform.runLater(() -> Utils.showAlertMessage("Ответ сервера с ошибкой " + serverAnswer.getCode(), serverAnswer.getDescription()));
                    }
                } else {
                    Platform.runLater(() -> Utils.showAlertMessage("Ошибка сервера " + response.code(), Converter.convertResponseToSting(response.errorBody())));
                }
            }

            @Override
            public void onFailure(Call<CreateReleaseResponseEnvelope> call, final Throwable t) {
                if (releaseCount++ < Main.COUNT_RETRY) {
                    btnReleaseCar.fire();
                } else {
                    releaseCount = 0;
                    controllerFormCarDetails.blockUI(false, "");
                    Platform.runLater(() -> Utils.showAlertMessage("Ошибка при отправлении запроса.", t.getMessage()));
                }
            }
        });
    }

    private void close() {
        ((Stage) txtPoliceDepartmentReceiptNumber.getScene().getWindow()).close();
    }

    private void sendReleaseDataWithoutRecheck() {
        Platform.runLater(() -> controllerFormCarDetails.blockUI(true, ""));
        RetrofitService retrofitService = Api.createRetrofitService();
        retrofitService.executeCreateReleaseWithoutRecheck(Encode.getBasicAuthTemplate(UserManager.getInstanse().getmLogin(), UserManager.getInstanse().getmPassword()),
                new CreateReleaseWithoutRecheckRequestEnvelope(this.identifier, txtBankReceiptNumber.getText(), txtPoliceDepartmentReceiptNumber.getText())).enqueue(new Callback<CreateReleaseWithoutRecheckResponseEnvelope>() {
            @Override
            public void onResponse(Call<CreateReleaseWithoutRecheckResponseEnvelope> call, final Response<CreateReleaseWithoutRecheckResponseEnvelope> response) {
                controllerFormCarDetails.blockUI(false, "");
                withoutRecheckCount = 0;
                if (response.code() == 200) {
                    final ServerAnswer serverAnswer = response.body().getServerAnswer();
                    if (serverAnswer.getCode() == 1) {
                        try {
                            final File pdfFile = FileManager.createPdfFile();
                            FileOutputStream fos = new FileOutputStream(pdfFile);
                            byte[] fileContent = Converter.convertBase64StringToByteArray(serverAnswer.getDescription());
                            fos.write(fileContent);
                            fos.close();
                            Desktop.getDesktop().open(pdfFile);
                            Platform.runLater(() -> Utils.showAlertMessage("Запрос выполнен успешно", "Если документ не открылся автоматически то его можно найти по пути " + pdfFile.getAbsolutePath()));
                            Platform.runLater(() -> close());
                            controllerFormCarDetails.getActualData();
                            DataChangeObserver.getInstance().dataChangeNotify();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Platform.runLater(() -> Utils.showAlertMessage("Ошибка ответа сервера " + serverAnswer.getCode(), serverAnswer.getDescription()));
                    }
                } else {
                    Platform.runLater(() -> Utils.showAlertMessage("Ошибка сервера " + response.code(), Converter.convertResponseToSting(response.errorBody())));
                }
            }

            @Override
            public void onFailure(Call<CreateReleaseWithoutRecheckResponseEnvelope> call, final Throwable t) {
                if (withoutRecheckCount++ < Main.COUNT_RETRY) {
                    sendReleaseDataWithoutRecheck();
                } else {
                    withoutRecheckCount = 0;
                    controllerFormCarDetails.blockUI(false, "");
                    Platform.runLater(() -> Utils.showAlertMessage("Ошбика при отправлении запроса.", t.getMessage()));
                }

            }
        });
    }

    private void sendRecheckReceipt() {
        Platform.runLater(() -> controllerFormCarDetails.blockUI(true, ""));
        RetrofitService retrofitService = Api.createRetrofitService();
        retrofitService.executeCreateRecheckReceipt(Encode.getBasicAuthTemplate(UserManager.getInstanse().getmLogin(), UserManager.getInstanse().getmPassword()),
                new CreateRecheckReceiptRequestEnvelope(this.identifier)).enqueue(new Callback<CreateRecheckReceiptResponseEnvelope>() {
            @Override
            public void onResponse(Call<CreateRecheckReceiptResponseEnvelope> call, final Response<CreateRecheckReceiptResponseEnvelope> response) {
                Platform.runLater(() -> controllerFormCarDetails.blockUI(false, ""));
                recheckCount = 0;
                if (response.code() == 200) {
                    final ServerAnswer serverAnswer = response.body().getServerAnswer();
                    if (serverAnswer.getCode() == 1) {
                        try {
                            final File pdfFile = FileManager.createPdfFile();
                            FileOutputStream fos = new FileOutputStream(pdfFile);
                            byte[] fileContent = Converter.convertBase64StringToByteArray(serverAnswer.getDescription());
                            fos.write(fileContent);
                            fos.close();
                            Desktop.getDesktop().open(pdfFile);
                            Platform.runLater(() -> Utils.showAlertMessage("Запрос выполнен успешно", "Если документ не открылся автоматически то его можно найти по пути " + pdfFile.getAbsolutePath()));
                            Platform.runLater(() -> close());
                            controllerFormCarDetails.getActualData();
                            DataChangeObserver.getInstance().dataChangeNotify();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Platform.runLater(() -> Utils.showAlertMessage("Ответ сервера содержит ошибку " + serverAnswer.getCode(), serverAnswer.getDescription()));
                    }
                } else {
                    Platform.runLater(() -> Utils.showAlertMessage("Ошибка сервера " + response.code(), Converter.convertResponseToSting(response.errorBody())));
                }
            }

            @Override
            public void onFailure(Call<CreateRecheckReceiptResponseEnvelope> call, final Throwable t) {
                if (recheckCount++ < Main.COUNT_RETRY) {
                    sendRecheckReceipt();
                } else {
                    recheckCount = 0;
                    Platform.runLater(() -> {
                        controllerFormCarDetails.blockUI(false, "");
                        Utils.showAlertMessage("Ошибка при отправлении запроса", t.getMessage());
                    });
                }
            }
        });

    }
}
