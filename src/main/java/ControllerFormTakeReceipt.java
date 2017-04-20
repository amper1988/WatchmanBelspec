import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import model.DataChangeObserver;
import model.FileManager;
import retrofit.Api;
import retrofit.RetrofitService;
import retrofit.model.CarDataFull;
import retrofit.model.OwnerDriverData;
import retrofit.model.create_receipt.request.CreateReceiptRequestEnvelope;
import retrofit.model.create_receipt.response.CreateReceiptResponseEnvelope;
import retrofit.model.get_car_details.request.GetCarDetailsRequestEnvelope;
import retrofit.model.get_car_details.response.GetCarDetailsResponseEnvelope;
import retrofit.model.get_owner_driver_data.request.GetOwnerDriverDataRequestEnvelope;
import retrofit.model.get_owner_driver_data.response.GetOwnerDriverDataResponseEnvelope;
import retrofit.model.get_receipt_information.request.GetReceiptInformationRequestEnvelope;
import retrofit.model.get_receipt_information.response.GetReceiptInformationResponseData;
import retrofit.model.get_receipt_information.response.GetReceiptInformationResponseEnvelope;
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

public class ControllerFormTakeReceipt implements Initializable{
    @FXML
    private Label lblCommonCosts;
    @FXML
    private Label lblManufacture;
    @FXML
    private Label lblModel;
    @FXML
    private Label lblColor;
    @FXML
    private Label lblCarId;
    @FXML
    private Label lblPoliceDepartment;
    @FXML
    private Label lblPoliceman;
    @FXML
    private Label lblClause;
    @FXML
    private Label lblProtocolNumber;
    @FXML
    private Label lblEvacuatedBool;
    @FXML
    private Label lblEvacuationCosts;
    @FXML
    private Label lblEvacuationOrganization;
    @FXML
    private Label lblWrecker;
    @FXML
    private Label lblAddressFrom;
    @FXML
    private Label lblEvacuationDate;
    @FXML
    private Label lblStorageOrganization;
    @FXML
    private Label lblWhoTook;
    @FXML
    private Label lblParkingAddress;
    @FXML
    private Label lblParkingDate;
    @FXML
    private Button btnTakeReceipt;
    @FXML
    private Label lblReceiptDate;
    @FXML
    private Label lblParkingCosts;
    @FXML
    private TextField txtTechCertSeries;
    @FXML
    private TextField txtTechCertNumber;
    @FXML
    private TextField txtOwnerName;
    @FXML
    private TextField txtOwnerAddress;
    @FXML
    private TextField txtDriverLicenseSeries;
    @FXML
    private TextField txtDriverLicenseNumber;
    @FXML
    private TextField txtDriverName;
    @FXML
    private TextField txtDriverAddress;
    @FXML
    private TextField txtDriverContact;
    @FXML
    private CheckBox cbDebtActBool;
    @FXML
    private TextField txtDebtActNumber;
    @FXML
    private Button btnCopyData;
    @FXML
    private ImageView imvImage1;
    @FXML
    private ImageView imvImage2;
    @FXML
    private ImageView imvImage3;
    @FXML
    private ImageView imvImage4;
    @FXML
    private WebView wvMaps;
    @FXML
    private VBox vbTop;
    @FXML
    private VBox vbLeft;
    @FXML
    private VBox vbCenter;
    @FXML
    private VBox vbRight;

    private FormLoading formLoading;

    private int identifier = -1;

    private Stage primaryStage;
    private ControllerFormCarDetails controllerFormCarDetails = null;
    private String carId;

    private int takeReceiptCount = 0;
    private int getOwnerInformationCount = 0;
    private int getReceiptInformationCount = 0;
    private int getCarDetailsCount = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        wvMaps.setContextMenuEnabled(false);
        txtTechCertSeries.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            if(event.getCode() == KeyCode.ENTER){
                txtTechCertNumber.requestFocus();
            }
        });
        txtTechCertSeries.focusedProperty().addListener((observable, oldValue, newValue)->{
                    if(txtTechCertSeries.getText() != null)
                        txtTechCertSeries.setText(txtTechCertSeries.getText().toUpperCase());
        });
        txtTechCertNumber.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            if(event.getCode() == KeyCode.ENTER)
                txtOwnerName.requestFocus();
        });
        txtTechCertNumber.focusedProperty().addListener((observable, oldValue, newValue)->{
                    if(txtTechCertNumber.getText() != null)
                        txtTechCertNumber.setText(txtTechCertNumber.getText().toUpperCase());
        });
        txtOwnerName.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            if(event.getCode() == KeyCode.ENTER)
                txtOwnerAddress.requestFocus();
        });
        txtOwnerName.focusedProperty().addListener((observable, oldValue, newValue)->{
            if(txtOwnerName.getText() != null)
                txtOwnerName.setText(txtOwnerName.getText().toUpperCase());
        });
        txtOwnerAddress.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            if(event.getCode() == KeyCode.ENTER)
                txtDriverLicenseSeries.requestFocus();
        });
        txtOwnerAddress.focusedProperty().addListener((observable, oldValue, newValue)->{
            if(txtOwnerAddress.getText() != null)
                txtOwnerAddress.setText(txtOwnerAddress.getText().toUpperCase());
        });
        txtDriverLicenseSeries.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            if(event.getCode() == KeyCode.ENTER)
                txtDriverLicenseNumber.requestFocus();
        });
        txtDriverLicenseSeries.focusedProperty().addListener((observable, oldValue, newValue)->{
            if(txtDriverLicenseSeries.getText() != null)
                txtDriverLicenseSeries.setText(txtDriverLicenseSeries.getText().toUpperCase());
        });
        txtDriverLicenseNumber.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            if(event.getCode() == KeyCode.ENTER)
                txtDriverName.requestFocus();
        });
        txtDriverLicenseNumber.focusedProperty().addListener((observable, oldValue, newValue)->{
            if(txtDriverLicenseNumber.getText() != null)
                txtDriverLicenseNumber.setText(txtDriverLicenseNumber.getText().toUpperCase());
        });
        txtDriverName.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            if(event.getCode() == KeyCode.ENTER)
                txtDriverAddress.requestFocus();
        });
        txtDriverName.focusedProperty().addListener((observable, oldValue, newValue)->{
            if(txtDriverName.getText() != null)
                txtDriverName.setText(txtDriverName.getText().toUpperCase());
        });
        txtDriverAddress.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            if(event.getCode() == KeyCode.ENTER)
                txtDriverContact.requestFocus();
        });
        txtDriverAddress.focusedProperty().addListener((observable, oldValue, newValue)->{
            if(txtDriverAddress.getText() != null)
                txtDriverAddress.setText(txtDriverAddress.getText().toUpperCase());
        });
        initializeButtonEvent();
    }

    private void initializeButtonEvent(){
        btnCopyData.setOnAction(actionEvent -> {
            txtDriverName.setText(txtOwnerName.getText());
            txtDriverAddress.setText(txtOwnerAddress.getText());
        });

        btnTakeReceipt.setOnAction(actionEvent -> {
            blockUI(true, "Отправление данных на сервер для создания счета.");
            if(confirmData()){
                RetrofitService retrofitService = Api.createRetrofitService();
                retrofitService.executeCreateReceipt(Encode.getBasicAuthTemplate(UserManager.getInstanse().getmLogin(), UserManager.getInstanse().getmPassword()),
                        new CreateReceiptRequestEnvelope(identifier, txtTechCertSeries.getText(), txtTechCertNumber.getText(), txtOwnerName.getText(), txtOwnerAddress.getText(),
                                txtDriverLicenseSeries.getText(), txtDriverLicenseNumber.getText(), txtDriverName.getText(), txtDriverAddress.getText(), txtDriverContact.getText(),
                                cbDebtActBool.isSelected(), txtDebtActNumber.getText(), lblReceiptDate.getText())).enqueue(new Callback<CreateReceiptResponseEnvelope>() {
                    @Override
                    public void onResponse(Call<CreateReceiptResponseEnvelope> call, final Response<CreateReceiptResponseEnvelope> response) {
                        if(response.code() == 200){
                            final int serverAnswer = response.body().getServerAnswer().getCode();
                            if(serverAnswer == 1){
                                String documentString = response.body().getServerAnswer().getDescription();
                                try {
                                    final File pdfFile = FileManager.createPdfFile();
                                    FileOutputStream fos = new FileOutputStream(pdfFile.getPath());
                                    byte [] bytePDF = Converter.convertBase64StringToByteArray(documentString);
                                    fos.write(bytePDF);
                                    fos.close();
                                    Desktop.getDesktop().open(pdfFile);
                                    DataChangeObserver.getInstance().dataChangeNotify();
                                    Platform.runLater(() -> Utils.showAlertMessage("Счет успешно выставлен", "Если файл не открылся автоматические его можно найти по пути: " + pdfFile.getAbsolutePath()));
                                    Platform.runLater(() -> close());
                                    if(controllerFormCarDetails !=null)
                                        controllerFormCarDetails.getActualData();

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            }else {
                                Platform.runLater(() -> Utils.showAlertMessage("Ошибка ответа сервера " + serverAnswer, response.body().getServerAnswer().getDescription()));
                            }
                        }else{
                            Platform.runLater(() -> Utils.showAlertMessage("Ошибка сервера "+ response.code(), Converter.convertResponseToSting(response.errorBody())));
                        }
                        blockUI(false, "Данные успешно получены.");
                        takeReceiptCount = 0;
                    }

                    @Override
                    public void onFailure(Call<CreateReceiptResponseEnvelope> call, final Throwable t) {
                        if(takeReceiptCount++ < Main.COUNT_RETRY){
                            btnTakeReceipt.fire();
                        }else{
                            takeReceiptCount = 0;
                            blockUI(false, "Ошибка при отправлении запроса.");
                            Platform.runLater(() -> Utils.showAlertMessage("Ошибка при отправлении запроса", t.getMessage()));
                        }

                    }
                });
            }else{
                blockUI(false, "Данные не заполнены.");
                Platform.runLater(() -> Utils.showAlertMessage("Данные не заполнены", "Проверьте введенные данные."));
            }

        });
        cbDebtActBool.setOnAction(actionEvent -> txtDebtActNumber.setVisible(cbDebtActBool.isSelected()));
        txtDebtActNumber.setVisible(false);
    }

    private void close(){
        if(primaryStage!= null){
            primaryStage.close();
        }else{
            ((Stage)imvImage1.getScene().getWindow()).close();
        }
    }

    public void setIdentifier(int identifier, Stage primaryStage, ControllerFormCarDetails controllerFormCarDetails, String carId){
        this.identifier = identifier;
        this.primaryStage = primaryStage;
        this.controllerFormCarDetails = controllerFormCarDetails;
        this.carId = carId;
        if(this.identifier != -1 ){
            getCarDetails();
            getReceiptInformation();
            getOwnerDriverData();
        }
    }

    private void getOwnerDriverData() {
        blockUI(true, "Получение данных о водителе (собственнике)");
        Api.createRetrofitService().executeGetOwnerDriverData(Encode.getBasicAuthTemplate(UserManager.getInstanse().getmLogin(), UserManager.getInstanse().getmPassword()),
                new GetOwnerDriverDataRequestEnvelope(this.carId)).enqueue(new Callback<GetOwnerDriverDataResponseEnvelope>() {
            @Override
            public void onResponse(Call<GetOwnerDriverDataResponseEnvelope> call, final Response<GetOwnerDriverDataResponseEnvelope> response) {
                blockUI(false, "Данные о вовдителе (собственнике) получены.");
                getOwnerInformationCount = 0;
                if(response.code() == 200){
                    Platform.runLater(() -> {
                        setOwnerData(response.body().getOwnerDriverData());
                        txtTechCertSeries.requestFocus();
                    });
                }else{
                    Platform.runLater(() -> Utils.showAlertMessage("Ошбика сервера " + response.code(), Converter.convertResponseToSting(response.errorBody())));
                }
            }

            @Override
            public void onFailure(Call<GetOwnerDriverDataResponseEnvelope> call, final Throwable t) {
                if(getOwnerInformationCount++ < Main.COUNT_RETRY){
                    getOwnerDriverData();
                }else{
                    blockUI(false, "При попытке получения данных собственника произошла ошибка.");
                    getOwnerInformationCount = 0;
                    Platform.runLater(() -> Utils.showAlertMessage("Ошибка отправления зарпоса", t.getMessage()));
                }

            }
        });
    }

    private void getReceiptInformation() {
        blockUI(true, "Получение данных о выдаче");
        Api.createRetrofitService().executeGetReceiptInformation(Encode.getBasicAuthTemplate(UserManager.getInstanse().getmLogin(), UserManager.getInstanse().getmPassword()),
                new GetReceiptInformationRequestEnvelope(this.identifier)).enqueue(new Callback<GetReceiptInformationResponseEnvelope>() {
            @Override
            public void onResponse(Call<GetReceiptInformationResponseEnvelope> call, final Response<GetReceiptInformationResponseEnvelope> response) {
                blockUI(false, "Данные о выдаче получены.");
                getReceiptInformationCount = 0;
                if(response.code() == 200){
                    Platform.runLater(() -> showReceiptInformation(response.body().getData()));
                }else
                    Platform.runLater(() -> Utils.showAlertMessage("Ошибка "+response.code(), Converter.convertResponseToSting(response.errorBody())));
            }

            @Override
            public void onFailure(Call<GetReceiptInformationResponseEnvelope> call, final Throwable t) {
                if(getReceiptInformationCount++ < Main.COUNT_RETRY ){
                    getReceiptInformation();
                }else{
                    blockUI(false, "При отправлении запроса о данных выдачи произошла ошибка.");
                    getReceiptInformationCount = 0;
                    Platform.runLater(() -> Utils.showAlertMessage("Ошибка отправления запроса" ,t.getMessage()));
                }
            }
        });
    }

    private void getCarDetails() {
        blockUI(true, "Получаем данные о транспортном средстве.");
        RetrofitService retrofitService = Api.createRetrofitService();
        retrofitService.executeGetCarDetails(Encode.getBasicAuthTemplate(UserManager.getInstanse().getmLogin(), UserManager.getInstanse().getmPassword()),
                new GetCarDetailsRequestEnvelope(this.identifier)).enqueue(new Callback<GetCarDetailsResponseEnvelope>() {
            @Override
            public void onResponse(Call<GetCarDetailsResponseEnvelope> call, final Response<GetCarDetailsResponseEnvelope> response) {
                blockUI(false, "Данные транспортного средства успешно получены.");
                getCarDetailsCount = 0;
                if(response.code() == 200){
                    final CarDataFull carData = response.body().getCarDataFull();
                    if(carData != null){
                        Platform.runLater(() -> showInformation(carData));

                    }else{
                        Platform.runLater(() -> Utils.showAlertMessage("Пустой ответ сервера.", "Данных на сервере не найдено. Повторите запрос позже."));
                    }
                }else{
                    Platform.runLater(() -> Utils.showAlertMessage("Ошибка полученного ответа.", Converter.convertResponseToSting(response.errorBody())));
                }
            }

            @Override
            public void onFailure(Call<GetCarDetailsResponseEnvelope> call, final Throwable t) {
                if(getCarDetailsCount++ < Main.COUNT_RETRY){
                    getCarDetails();
                }else{
                    getCarDetailsCount = 0;
                    blockUI(false, "Ошибка при отправлении запроса.");
                    Platform.runLater(() -> Utils.showAlertMessage("Ошибка при отправлении запроса.", t.getMessage()));
                }
            }
        });
    }

    private void showReceiptInformation(GetReceiptInformationResponseData data){
        if(data != null){
            lblCommonCosts.setText(data.getCommonCosts());
            lblReceiptDate.setText(data.getReceiptDate());
            lblParkingCosts.setText(data.getParkingCosts());
        }
    }

    private boolean confirmData(){
        if(txtTechCertSeries.getText() == null || txtTechCertSeries.getText().isEmpty()){
            txtTechCertSeries.requestFocus();
            return false;
        }
        if(txtTechCertNumber.getText() == null || txtTechCertNumber.getText().isEmpty()){
            txtTechCertNumber.requestFocus();
            return false;
        }
        if(txtOwnerName.getText() == null || txtOwnerName.getText().isEmpty()){
            txtOwnerName.requestFocus();
            return false;
        }
        if(txtOwnerAddress.getText() == null || txtOwnerAddress.getText().isEmpty()){
            txtOwnerAddress.requestFocus();
            return false;
        }
        if(txtDriverLicenseSeries.getText() == null || txtDriverLicenseSeries.getText().isEmpty()){
            txtDriverLicenseSeries.requestFocus();
            return false;
        }
        if(txtDriverLicenseNumber.getText() == null || txtDriverLicenseNumber.getText().isEmpty()){
            txtDriverLicenseNumber.requestFocus();
            return false;
        }
        if(txtDriverName.getText() == null || txtDriverName.getText().isEmpty()){
            txtDriverName.requestFocus();
            return  false;
        }
        if(txtDriverAddress.getText() == null || txtDriverAddress.getText().isEmpty()){
            txtDriverAddress.requestFocus();
            return false;
        }
        if(cbDebtActBool.isSelected() && (txtDebtActNumber.getText() == null || txtDebtActNumber.getText().isEmpty())){
            txtDebtActNumber.requestFocus();
            return false;
        }
        if(txtDriverContact.getText() == null || txtDriverContact.getText().isEmpty()){
            txtDriverContact.requestFocus();
            return false;
        }
        return true;
    }

    private void showInformation(CarDataFull carData){
        lblManufacture.setText(carData.getManufacture());
        lblModel.setText(carData.getModel());
        lblColor.setText(carData.getColor());
        lblCarId.setText(carData.getCarId());
        lblPoliceDepartment.setText(carData.getPoliceDepartment());
        lblPoliceman.setText(carData.getPoliceman());
        lblProtocolNumber.setText(carData.getProtocolNumber());
        lblClause.setText(carData.getClause());
        boolean evacuated = carData.isEvacuatedBool();
        lblEvacuatedBool.setText( evacuated ? "Да": "Нет");
        lblEvacuationCosts.setText(carData.getEvacuationCosts());
        lblEvacuationCosts.setVisible(evacuated);
        lblEvacuationOrganization.setText(carData.getEvacuationOrganization());
        lblEvacuationOrganization.setVisible(evacuated);
        lblWrecker.setText(carData.getWrecker());
        lblWrecker.setVisible(evacuated);
        lblEvacuationDate.setText(carData.getEvacuationDate());
        lblEvacuationDate.setVisible(evacuated);
        lblAddressFrom.setText(carData.getEvacuationAddress());
        lblAddressFrom.setVisible(evacuated);
        lblParkingAddress.setText(carData.getParkingAddress());
        lblWhoTook.setText(carData.getWhoTook());
        lblParkingDate.setText(carData.getParkingDate());
        lblStorageOrganization.setText(carData.getStoringOrganization());
        imvImage1.setImage(Converter.convertBase64StringToImage(carData.getImage1()));
        imvImage2.setImage(Converter.convertBase64StringToImage(carData.getImage2()));
        imvImage3.setImage(Converter.convertBase64StringToImage(carData.getImage3()));
        imvImage4.setImage(Converter.convertBase64StringToImage(carData.getImage4()));
        final WebEngine webEngine = wvMaps.getEngine();
        webEngine.loadContent(carData.getMaps());
    }

    private void blockUI(boolean bool, String info){
        if(bool){
            Platform.runLater(()->{
                try {
                    if(formLoading == null){
                        formLoading = new FormLoading();
                    }
                    formLoading.start(primaryStage, info);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }else{
            try {
                Platform.runLater(() -> {
                    try {
                        if(formLoading != null){
                            formLoading.stop(info);
                            formLoading = null;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        vbTop.setVisible(!bool);
        vbLeft.setVisible(!bool);
        vbCenter.setVisible(!bool);
        vbRight.setVisible(!bool);
    }

    private void setOwnerData(OwnerDriverData data){
        txtTechCertSeries.setText(data.getTechCertSeries());
        txtTechCertNumber.setText(data.getTechCertNumber());
        txtOwnerName.setText(data.getOwnerName());
        txtOwnerAddress.setText(data.getOwnerAddress());
        txtDriverLicenseSeries.setText(data.getDriverLicenseSeries());
        txtDriverLicenseNumber.setText(data.getDriverLicenseNumber());
        txtDriverName.setText(data.getDriverName());
        txtDriverAddress.setText(data.getDriverAddress());
        txtDriverContact.setText(data.getDriverContact());
    }
}
