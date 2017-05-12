import interfaces.ChangerListener;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.DataChangeObserver;
import model.FileManager;
import retrofit.Api;
import retrofit.model.ServerAnswer;
import retrofit.model.create_release_by_debt_act.request.CreateReleaseByDebtActRequestEnvelope;
import retrofit.model.create_release_by_debt_act.response.CreateReleaseByDebtActResponseEnvelope;
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
import java.util.ResourceBundle;


public class ControllerFormReleaseByDebtAct implements Initializable {
    @FXML
    private TextField txtDebtActNumber;
    @FXML
    private TextField txtPoliceDepartmentReceiptNumber;
    @FXML
    private Button btnReleaseByDebtAct;

    private int identifier;
    private ChangerListener listener;
    private int createReleaseByDebtActCount;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnReleaseByDebtAct.setOnAction(actionEvent -> {
            {
                if (confirmData()) {
                    createReleaseByDebtAct();
                } else {
                    Utils.showAlertMessage("Данные не заполнены", "Проверьте введенные данные");
                }
            }
        });

    }

    private void createReleaseByDebtAct() {
        Api.createRetrofitService().executeCreateReleaseByDebtAct(Encode.getBasicAuthTemplate(UserManager.getInstanse().getmLogin(), UserManager.getInstanse().getmPassword()),
                new CreateReleaseByDebtActRequestEnvelope(identifier, txtDebtActNumber.getText(), txtPoliceDepartmentReceiptNumber.getText())).enqueue(new Callback<CreateReleaseByDebtActResponseEnvelope>() {
            @Override
            public void onResponse(Call<CreateReleaseByDebtActResponseEnvelope> call, final Response<CreateReleaseByDebtActResponseEnvelope> response) {
                createReleaseByDebtActCount = 0;
                if (response.code() == 200) {
                    final ServerAnswer serverAnswer = response.body().getServerAnswer();
                    if (serverAnswer.getCode() == 1) {
                        String documentString = response.body().getServerAnswer().getDescription();
                        try {
                            final File pdfFile = FileManager.createPdfFile();
                            FileOutputStream fos = new FileOutputStream(pdfFile.getPath());
                            byte[] bytePDF = Converter.convertBase64StringToByteArray(documentString);
                            fos.write(bytePDF);
                            fos.close();
                            Desktop.getDesktop().open(pdfFile);
                            DataChangeObserver.getInstance().dataChangeNotify();
                            Platform.runLater(() -> Utils.showAlertMessage("Успешно выдано по долговому акту", "Если файл не открылся автоматические его можно найти по пути: " + pdfFile.getAbsolutePath()));
                            Platform.runLater(() -> ((Stage) btnReleaseByDebtAct.getScene().getWindow()).close());
                            listener.onChangeData();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Platform.runLater(() -> Utils.showAlertMessage("Ошбика ответа сервера " + serverAnswer.getCode(), serverAnswer.getDescription()));
                    }
                } else {
                    Platform.runLater(() -> Utils.showAlertMessage("Ошибка сервера " + response.code(), Converter.convertResponseToSting(response.errorBody())));
                }
            }

            @Override
            public void onFailure(Call<CreateReleaseByDebtActResponseEnvelope> call, final Throwable t) {
                if (createReleaseByDebtActCount++ < Main.COUNT_RETRY) {
                    createReleaseByDebtAct();
                } else {
                    createReleaseByDebtActCount = 0;
                    Platform.runLater(() -> Utils.showAlertMessage("Ошбика отправления запроса", t.getMessage()));
                }

            }
        });
    }

    public void setData(int identifier, ChangerListener listener) {
        this.listener = listener;
        this.identifier = identifier;
    }

    private boolean confirmData() {
        if (txtDebtActNumber.getText().isEmpty()) {
            txtDebtActNumber.requestFocus();
            return false;
        }
        if (txtPoliceDepartmentReceiptNumber.getText().isEmpty()) {
            txtPoliceDepartmentReceiptNumber.requestFocus();
            return false;
        }
        return true;
    }
}
