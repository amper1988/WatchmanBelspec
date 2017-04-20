import interfaces.ChangerListener;
import interfaces.ImageContextMenuListener;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import model.DataChangeObserver;
import model.FileManager;
import retrofit.Api;
import retrofit.RetrofitService;
import retrofit.model.CarDataFull;
import retrofit.model.ServerAnswer;
import retrofit.model.create_back_to_parking.request.CreateBackToParkingRequestEnvelope;
import retrofit.model.create_back_to_parking.response.CreateBackToParkingResponseEnvelope;
import retrofit.model.get_car_details.request.GetCarDetailsRequestEnvelope;
import retrofit.model.get_car_details.response.GetCarDetailsResponseEnvelope;
import retrofit.model.get_debt_act_document.request.GetDebtActDocumentRequestEnvelope;
import retrofit.model.get_debt_act_document.response.GetDebtActDocumentResponseEnvelope;
import retrofit.model.get_photos.request.GetPhotosRequestEnvelope;
import retrofit.model.get_photos.response.GetPhotosResponseEnvelope;
import retrofit.model.get_protocol.request.GetProtocolRequestEnvelope;
import retrofit.model.get_protocol.response.GetProtocolResponseEnvelope;
import retrofit.model.get_receipt.request.GetReceiptRequestEnvelope;
import retrofit.model.get_receipt.response.GetReceiptResponseEnvelope;
import retrofit.model.get_release_information.request.GetReleaseInformationRequestEnvelope;
import retrofit.model.get_release_information.response.GetReleaseInformationResponseEnvelope;
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

public class ControllerFormCarDetails implements Initializable, ImageContextMenuListener, ChangerListener{
    @FXML
    private Label lblManufTitle;
    @FXML
    private VBox vbReleaseData;
    @FXML
    private Label lblEvacuatedBool;
    @FXML
    private Label lblPoliceman;
    @FXML
    private Label lblEvacuationCosts;
    @FXML
    private HBox hbEvacuationData;
    @FXML
    private HBox hbEvacuationCosts;
    @FXML
    private Label lblParkingDate;
    @FXML
    private Label lblOwnerAddress;
    @FXML
    private Label lblEvacuationDate;
    @FXML
    private Label lblColor;
    @FXML
    private Label lblDriverContact;
    @FXML
    private HBox hbWrecker;
    @FXML
    private WebView wvMaps;
    @FXML
    private Label lblSeriesDrvLicense;
    @FXML
    private Label lblTechCertNumber;
    @FXML
    private Label lblStatus;
    @FXML
    private Label lblManufacture;
    @FXML
    private Label lblCarId;
    @FXML
    private Label lblReceiptNumberBank;
    @FXML
    private Label lblOwnerName;
    @FXML
    private Label lblDebtActNumber;
    @FXML
    private Label lblEvacuationOrganization;
    @FXML
    private Label lblModel;
    @FXML
    private VBox vbOwnerData;
    @FXML
    private Label lblDriverAddress;
    @FXML
    private Label lblDriverName;
    @FXML
    private Label lblWrecker;
    @FXML
    private Label lblParkingAddress;
    @FXML
    private Label lblWhoRelease;
    @FXML
    private Label lblPoliceDepartment;
    @FXML
    private Label lblAddressFrom;
    @FXML
    private HBox hbEvacuationDate;
    @FXML
    private Label lblClause;
    @FXML
    private Label lblProtocolNumber;
    @FXML
    private VBox vbStoringData;
    @FXML
    private HBox hbEvacuationOrganization;
    @FXML
    private Label lblNumberDrvLicense;
    @FXML
    private Label lblStorageOrganization;
    @FXML
    private Label lblParkingCosts;
    @FXML
    private Label lblSeriesTechCert;
    @FXML
    private Label lblDebtActBool;
    @FXML
    private Label lblWhoTook;
    @FXML
    private ImageView imvImage1;
    @FXML
    private ImageView imvImage2;
    @FXML
    private ImageView imvImage3;
    @FXML
    private ImageView imvImage4;
    @FXML
    private Label lblReleaseDate;
    @FXML
    private Label lblReceiptNumberPolice;
    @FXML
    private HBox hbReceiptPoliceDepartment;
    @FXML
    private HBox hbReceiptBank;
    @FXML
    private HBox hbDebtActNumber;
    @FXML
    private HBox hbEvacuationAddress;
    @FXML
    private VBox vbTop;
    @FXML
    private VBox vbLeft;
    @FXML
    private VBox vbCenter;
    @FXML
    private VBox vbRight;
    @FXML
    private BorderPane brdpFull;
    @FXML
    private Button btnTakeProtocol;
    @FXML
    private Button btnTakePhotos;
    @FXML
    private Button btnTakeDebtAct;
    @FXML
    private Button btnTakeReceipt;
    @FXML
    private Button btnTakeReleaseInformation;
    @FXML
    private Button btnCreateReceipt;
    @FXML
    private Button btnRelease;
    @FXML
    private Button btnReturnToParking;
    @FXML
    private Button btnBlock;
    @FXML
    private Button btnBlockFree;
    @FXML
    private Button btnReleaseToAnotherParking;
    @FXML
    private Button btnTakeFromAnotherParking;
    @FXML
    private Button btnReleaseByDebtAct;

    private int returnToParkingCount = 0;
    private int getDebtActCount = 0;
    private int getProtocolCount = 0;
    private int getReceiptCount = 0;
    private int takeReleaseInformationCount = 0;
    private int takePhotosCount = 0;
    private int getActualDataCount = 0;

    private int identifier = -1;
    private Stage primaryStage = null;
    private FormLoading formLoading = null;
    private CarDataFull carData = null;



    public CarDataFull getCarData(){
        return this.carData;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       ImageContextMenu imageContextMenu = new ImageContextMenu(imvImage1);
       imageContextMenu.setListener(this);
       ImageContextMenu imageContextMenu2 = new ImageContextMenu(imvImage2);
        imageContextMenu2.setListener(this);
        ImageContextMenu imageContextMenu3 = new ImageContextMenu(imvImage3);
        imageContextMenu3.setListener(this);
        ImageContextMenu imageContextMenu4 = new ImageContextMenu(imvImage4);
        imageContextMenu4.setListener(this);
        wvMaps.setContextMenuEnabled(false);
        initializeButtonsEvent();
    }

    private void initializeButtonsEvent(){
        btnRelease.setOnAction(actionEvent -> {
            FormReleaseCar formReleaseCar = new FormReleaseCar(identifier, ControllerFormCarDetails.this);
            formReleaseCar.start(null);
        });

        btnCreateReceipt.setOnAction(actionEvent -> {
            FormTakeReceipt frm = new FormTakeReceipt(identifier, ControllerFormCarDetails.this, carData.getCarId());
            if(carData.isBlock()){
                Platform.runLater(() -> Utils.showAlertMessage("Снимите арест.", "Автомобиль находится под арестом. Перед выставлением счета необходимо снять арест."));
            }else{
                try {
                    frm.start(null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });

        btnReturnToParking.setOnAction((ActionEvent actionEvent) -> {
            blockUI(true, "Возвращение на стоянку.");
            RetrofitService retrofitService = Api.createRetrofitService();
            retrofitService.executeBackToParking(Encode.getBasicAuthTemplate(UserManager.getInstanse().getmLogin(), UserManager.getInstanse().getmPassword()),
                    new CreateBackToParkingRequestEnvelope(identifier)).enqueue(new Callback<CreateBackToParkingResponseEnvelope>() {
                @Override
                public void onResponse(Call<CreateBackToParkingResponseEnvelope> call, final Response<CreateBackToParkingResponseEnvelope> response) {
                    blockUI(false, "Данные получены от сервера.");
                    returnToParkingCount = 0;
                    if(response.code() == 200){
                        if(response.body().getServerAnswer().getCode() == 1){
                            Platform.runLater(() -> {
                                Utils.showAlertMessage("Запрос успешно завершен", response.body().getServerAnswer().getDescription());
                                setIdentifier(identifier, primaryStage);
                                DataChangeObserver.getInstance().dataChangeNotify();
                            });
                        }else{
                            Platform.runLater(() -> Utils.showAlertMessage("Ответ сервера с ошибкой " + response.body().getServerAnswer().getCode(), response.body().getServerAnswer().getDescription()));
                        }
                    }else{
                        Platform.runLater(() -> Utils.showAlertMessage("Ошибка сервера "+ response.code(), Converter.convertResponseToSting(response.errorBody())));
                    }
                }

                @Override
                public void onFailure(Call<CreateBackToParkingResponseEnvelope> call, final Throwable t) {
                    if(returnToParkingCount++ < Main.COUNT_RETRY){
                        btnReturnToParking.fire();
                    }else{
                        returnToParkingCount = 0;
                        blockUI(false, "Ошбика при отправлении запроса.");
                        Platform.runLater(() -> Utils.showAlertMessage("Ошибка при отправлении запроса", t.getMessage()));
                    }
                }
            });
        });

        btnTakeDebtAct.setOnAction(actionEvent -> {
            blockUI(true, "Получение акта приема-передачи имущества.");
            RetrofitService retrofitService = Api.createRetrofitService();
            retrofitService.executeGetDebtActDocument(Encode.getBasicAuthTemplate(UserManager.getInstanse().getmLogin(), UserManager.getInstanse().getmPassword()),
                    new GetDebtActDocumentRequestEnvelope(identifier)).enqueue(new Callback<GetDebtActDocumentResponseEnvelope>() {
                @Override
                public void onResponse(Call<GetDebtActDocumentResponseEnvelope> call, final Response<GetDebtActDocumentResponseEnvelope> response) {
                    blockUI(false, "Данные успешно получены с сервера.");
                    getDebtActCount = 0;
                    if(response.code() == 200){
                        if(response.body().getServerAnswer().getCode() == 1){
                            try {
                                final File pdfFile = FileManager.createPdfFile();
                                FileOutputStream fos = new FileOutputStream(pdfFile);
                                byte[] fileContent = Converter.convertBase64StringToByteArray(response.body().getServerAnswer().getDescription());
                                fos.write(fileContent);
                                fos.close();
                                Desktop.getDesktop().open(pdfFile);
                                Platform.runLater(() -> Utils.showAlertMessage("Запрос выполнен успешно", "Если документ не открылся автоматически то его можно найти по пути "+ pdfFile.getAbsolutePath()));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }else{
                            Platform.runLater(() -> Utils.showAlertMessage("Ошибка ответа сервера " + response.body().getServerAnswer().getCode(), response.body().getServerAnswer().getDescription()));
                        }
                    }else{
                        Platform.runLater(() -> Utils.showAlertMessage("Ошибка сервера "+ response.code(), Converter.convertResponseToSting(response.errorBody())));
                    }

                }

                @Override
                public void onFailure(Call<GetDebtActDocumentResponseEnvelope> call, final Throwable t) {
                    if(getDebtActCount++ < Main.COUNT_RETRY){
                        btnTakeDebtAct.fire();
                    }else{
                        getDebtActCount = 0;
                        blockUI(false, "Ошбика отправления запроса.");
                        Platform.runLater(() -> Utils.showAlertMessage("Ошибка отправления запроса", t.getMessage()));
                    }

                }
            });
        });

        btnTakeProtocol.setOnAction(actionEvent -> {
            blockUI(true, "Получаем протокол осмотра.");
            RetrofitService retrofitService = Api.createRetrofitService();
            retrofitService.executeGetProtocol(Encode.getBasicAuthTemplate(UserManager.getInstanse().getmLogin(), UserManager.getInstanse().getmPassword()),
                    new GetProtocolRequestEnvelope(identifier)).enqueue(new Callback<GetProtocolResponseEnvelope>() {
                @Override
                public void onResponse(Call<GetProtocolResponseEnvelope> call, final Response<GetProtocolResponseEnvelope> response) {
                    blockUI(false, "Данные успешно получены с сервера.");
                    getProtocolCount = 0;
                    if(response.code() == 200){
                        final ServerAnswer serverAnswer = response.body().getServerAnswer();
                        if(serverAnswer.getCode() == 1){
                            try {
                                final File pdfFile = FileManager.createPdfFile();
                                FileOutputStream fos = new FileOutputStream(pdfFile);
                                byte[] fileContent = Converter.convertBase64StringToByteArray(response.body().getServerAnswer().getDescription());
                                fos.write(fileContent);
                                fos.close();
                                Desktop.getDesktop().open(pdfFile);
                                Platform.runLater(() -> Utils.showAlertMessage("Запрос выполнен успешно", "Если документ не открылся автоматически то его можно найти по пути "+ pdfFile.getAbsolutePath()));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }else{
                            Platform.runLater(() -> Utils.showAlertMessage("Ошбика ответа сервера " + serverAnswer.getCode(), serverAnswer.getDescription()));
                        }
                    }else{
                        Platform.runLater(() -> Utils.showAlertMessage("Ошибка сервера " + response.code(), Converter.convertResponseToSting(response.errorBody())));
                    }
                }

                @Override
                public void onFailure(Call<GetProtocolResponseEnvelope> call, final Throwable t) {
                    if(getProtocolCount++ < Main.COUNT_RETRY){
                        btnTakeProtocol.fire();
                    }else{
                        getProtocolCount = 0;
                        blockUI(false, "Ошибка отправления запроса");
                        Platform.runLater(() -> Utils.showAlertMessage("Ошбика отправления запроса", t.getMessage()));
                    }
                }
            });
        });

        btnTakeReceipt.setOnAction(actionEvent -> {
            blockUI(true, "Получем квитанцию.");
            RetrofitService retrofitService = Api.createRetrofitService();
            retrofitService.executeGetReceipt(Encode.getBasicAuthTemplate(UserManager.getInstanse().getmLogin(), UserManager.getInstanse().getmPassword()),
                    new GetReceiptRequestEnvelope(identifier)).enqueue(new Callback<GetReceiptResponseEnvelope>() {
                @Override
                public void onResponse(Call<GetReceiptResponseEnvelope> call, final Response<GetReceiptResponseEnvelope> response) {
                    blockUI(false, "Данные успешно получены  с сервера.");
                    getReceiptCount = 0;
                    if(response.code() == 200){
                        final ServerAnswer serverAnswer = response.body().getServerAnswer();
                        if(serverAnswer.getCode() == 1){
                            try {
                                final File pdfFile = FileManager.createPdfFile();
                                FileOutputStream fos = new FileOutputStream(pdfFile);
                                byte[] fileContent = Converter.convertBase64StringToByteArray(response.body().getServerAnswer().getDescription());
                                fos.write(fileContent);
                                fos.close();
                                Desktop.getDesktop().open(pdfFile);
                                Platform.runLater(() -> Utils.showAlertMessage("Запрос выполнен успешно", "Если документ не открылся автоматически то его можно найти по пути "+ pdfFile.getAbsolutePath()));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }else{
                            Platform.runLater(() -> Utils.showAlertMessage("Ошбика ответа сервера " + serverAnswer.getCode(), serverAnswer.getDescription()));
                        }
                    }else{
                        Platform.runLater(() -> Utils.showAlertMessage("Ошбика сервера " + response.code(), Converter.convertResponseToSting(response.errorBody())));
                    }
                }

                @Override
                public void onFailure(Call<GetReceiptResponseEnvelope> call, final Throwable t) {
                    if(getReceiptCount++ < Main.COUNT_RETRY){
                        btnTakeReceipt.fire();
                    }else{
                        getReceiptCount = 0;
                        blockUI(false, "Ошибка отправления запроса.");
                        Platform.runLater(() -> Utils.showAlertMessage("Ошибка отправления запроса", t.getMessage()));
                    }
                }
            });
        });

        btnTakeReleaseInformation.setOnAction(actionEvent -> {
            blockUI(true, "Получаем данные о выдаче.");
            Api.createRetrofitService().executeGetReleaseInformation(Encode.getBasicAuthTemplate(UserManager.getInstanse().getmLogin(), UserManager.getInstanse().getmPassword()),
                    new GetReleaseInformationRequestEnvelope(identifier)).enqueue(new Callback<GetReleaseInformationResponseEnvelope>() {
                @Override
                public void onResponse(Call<GetReleaseInformationResponseEnvelope> call, final Response<GetReleaseInformationResponseEnvelope> response) {
                    blockUI(false, "Данные успешно получены с сервера.");
                    takeReleaseInformationCount = 0;
                    if(response.code() == 200){
                        final ServerAnswer serverAnswer = response.body().getServerAnswer();
                        if(serverAnswer.getCode() == 1){
                            try {
                                final File pdfFile = FileManager.createPdfFile();
                                FileOutputStream fos = new FileOutputStream(pdfFile);
                                byte[] fileContent = Converter.convertBase64StringToByteArray(response.body().getServerAnswer().getDescription());
                                fos.write(fileContent);
                                fos.close();
                                Desktop.getDesktop().open(pdfFile);
                                Platform.runLater(() -> Utils.showAlertMessage("Запрос выполнен успешно", "Если документ не открылся автоматически то его можно найти по пути "+ pdfFile.getAbsolutePath()));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }else{
                            Platform.runLater(() -> Utils.showAlertMessage("Ошибка ответа сервера " + serverAnswer.getCode(), serverAnswer.getDescription()));
                        }

                    }else{
                        Platform.runLater(() -> Utils.showAlertMessage("Ошибка сервера "+ response.code(), Converter.convertResponseToSting(response.errorBody())));
                    }
                }

                @Override
                public void onFailure(Call<GetReleaseInformationResponseEnvelope> call, final Throwable t) {
                    if(takeReleaseInformationCount++ < Main.COUNT_RETRY){
                        btnTakeReleaseInformation.fire();
                    }else{
                        takeReleaseInformationCount = 0;
                        blockUI(false, "Ошибка отправления запроса.");
                        Platform.runLater(() -> Utils.showAlertMessage("Ошбика отправки запроса", t.getMessage()));
                    }
                }
            });
        });

        btnTakePhotos.setOnAction(actionEvent -> {
            blockUI(true, "Получаем фотографии.");
            Api.createRetrofitService().executeGetPhotos(Encode.getBasicAuthTemplate(UserManager.getInstanse().getmLogin(), UserManager.getInstanse().getmPassword()),
                    new GetPhotosRequestEnvelope(identifier)).enqueue(new Callback<GetPhotosResponseEnvelope>() {
                @Override
                public void onResponse(Call<GetPhotosResponseEnvelope> call, final Response<GetPhotosResponseEnvelope> response) {
                    blockUI(false, "Данные успешно получены  с сервера.");
                    takePhotosCount = 0;
                    if(response.code() == 200){
                        final ServerAnswer serverAnswer = response.body().getServerAnswer();
                        if(serverAnswer.getCode() == 1){
                            try {
                                final File pdfFile = FileManager.createPdfFile();
                                FileOutputStream fos = new FileOutputStream(pdfFile);
                                byte[] fileContent = Converter.convertBase64StringToByteArray(response.body().getServerAnswer().getDescription());
                                fos.write(fileContent);
                                fos.close();
                                Desktop.getDesktop().open(pdfFile);
                                Platform.runLater(() -> Utils.showAlertMessage("Запрос выполнен успешно", "Если документ не открылся автоматически то его можно найти по пути "+ pdfFile.getAbsolutePath()));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }else{
                            Platform.runLater(() -> Utils.showAlertMessage("Ошбика ответа сервера " + serverAnswer.getCode(), serverAnswer.getDescription()));
                        }
                    }else{
                        Platform.runLater(() -> Utils.showAlertMessage("Ошбика сервера " + response.code(), Converter.convertResponseToSting(response.errorBody())));
                    }
                }

                @Override
                public void onFailure(Call<GetPhotosResponseEnvelope> call, final Throwable t) {
                    if(takePhotosCount++ < Main.COUNT_RETRY){
                        btnTakePhotos.fire();
                    }else{
                        takePhotosCount = 0;
                        blockUI(false, "Ошибка отправления запроса.");
                        Platform.runLater(() -> Utils.showAlertMessage("Ошбика отправления зарпоса", t.getMessage()));
                    }


                }
            });
        });

        btnBlock.setOnAction(actionEvent -> {
            FormCreateArrest formCreateArrest = new FormCreateArrest(identifier, ControllerFormCarDetails.this);
            formCreateArrest.start(null);
        });

        btnBlockFree.setOnAction(actionEvent -> {
            FormUncheckArrest frm = new FormUncheckArrest(identifier, ControllerFormCarDetails.this);
            frm.start(null);
        });

        btnReleaseToAnotherParking.setOnAction(actionEvent -> {
            FormReleaseToAnotherParking frm = new FormReleaseToAnotherParking(identifier, ControllerFormCarDetails.this);
            frm.start(null);
        });

        btnTakeFromAnotherParking.setOnAction(actionEvent -> {
            FormTakeFromAnotherParking frm = new FormTakeFromAnotherParking(identifier, ControllerFormCarDetails.this);
            frm.start(null);
        });

        btnReleaseByDebtAct.setOnAction(actionEvent -> {
            FormReleaseByDebtAct frm = new FormReleaseByDebtAct(identifier, ControllerFormCarDetails.this);
            frm.start(null);
        });
    }

    public void setIdentifier(int identifier, Stage primaryStage){
        this.identifier = identifier;
        this.primaryStage = primaryStage;
        if(this.identifier != -1){
            blockUI(true, "Получение данных о транспортном средстве.");
            getActualData();
        }
    }

    public void getActualData(){
        blockUI(true, "Получение данных о транспортном средстве.");
        RetrofitService retrofitService = Api.createRetrofitService();
        retrofitService.executeGetCarDetails(Encode.getBasicAuthTemplate(UserManager.getInstanse().getmLogin(), UserManager.getInstanse().getmPassword()),
                new GetCarDetailsRequestEnvelope(this.identifier)).enqueue(new Callback<GetCarDetailsResponseEnvelope>() {
            @Override
            public void onResponse(Call<GetCarDetailsResponseEnvelope> call, final Response<GetCarDetailsResponseEnvelope> response) {
                getActualDataCount = 0;
                blockUI(false, "Данные успешно получены с сервера.");
                if(response.code() == 200){
                    Platform.runLater(() -> setData(response.body().getCarDataFull()));
                }else{
                    Platform.runLater(() -> Utils.showAlertMessage("Ошибка сервера "+ response.code(), Converter.convertResponseToSting(response.errorBody())));
                }
            }

            @Override
            public void onFailure(Call<GetCarDetailsResponseEnvelope> call, final Throwable t) {
                if(getActualDataCount++ < Main.COUNT_RETRY){
                    getActualData();
                }else{
                    getActualDataCount = 0;
                    blockUI(false, "Ошбика при отправлении запроса.");
                    Platform.runLater(() -> Utils.showAlertMessage("Ошибка при отправлении запроса.", t.getMessage()));
                }

            }
        });
    }

    public void setData(CarDataFull carDataFull){
        this.carData = carDataFull;
        if(carDataFull != null){
            lblManufTitle.setText(carDataFull.getManufacture());
            lblStatus.setText(carDataFull.getStatus());
            lblReceiptNumberBank.setText(carDataFull.getReceiptBank());
            lblReceiptNumberPolice.setText(carDataFull.getReceiptPoliceDepartment());
            lblManufacture.setText(carDataFull.getManufacture());
            lblModel.setText(carDataFull.getModel());
            lblColor.setText(carDataFull.getColor());
            lblCarId.setText(carDataFull.getCarId());
            lblPoliceDepartment.setText(carDataFull.getPoliceDepartment());
            lblPoliceman.setText(carDataFull.getPoliceman());
            lblProtocolNumber.setText(carDataFull.getProtocolNumber());
            lblClause.setText(carDataFull.getClause());
            lblEvacuatedBool.setText(carDataFull.isEvacuatedBool() ? "Да" : "Нет");
            lblEvacuationOrganization.setText(carDataFull.getEvacuationOrganization());
            lblWrecker.setText(carDataFull.getWrecker());
            lblEvacuationCosts.setText(carDataFull.getEvacuationCosts());
            lblEvacuationDate.setText(carDataFull.getEvacuationDate());
            lblAddressFrom.setText(carDataFull.getEvacuationAddress());
            lblWhoTook.setText(carDataFull.getWhoTook());
            lblParkingAddress.setText(carDataFull.getParkingAddress());
            lblParkingDate.setText(carDataFull.getParkingDate());
            lblStorageOrganization.setText(carDataFull.getStoringOrganization());
            lblSeriesTechCert.setText(carDataFull.getSeriesTechCert());
            lblTechCertNumber.setText(carDataFull.getNumberTechCert());
            lblOwnerName.setText(carDataFull.getOwnerName());
            lblOwnerAddress.setText(carDataFull.getOwnerAddress());
            lblSeriesDrvLicense.setText(carDataFull.getSeriesDriverLicense());
            lblNumberDrvLicense.setText(carDataFull.getNumberDriverLicense());
            lblDriverName.setText(carDataFull.getDriverName());
            lblDriverAddress.setText(carDataFull.getDriverAddress());
            lblDriverContact.setText(carDataFull.getDriverContact());
            lblWhoRelease.setText(carDataFull.getWhoReleased());
            lblParkingCosts.setText(carDataFull.getParkingCosts());
            lblReleaseDate.setText(carDataFull.getReleasedDate());
            lblDebtActBool.setText(carDataFull.isDebtActBool() ? "Да" : "Нет");
            lblDebtActNumber.setText(carDataFull.getDebtActNumber());
            final WebEngine webEngine = wvMaps.getEngine();
            webEngine.loadContent(carDataFull.getMaps());
            imvImage1.setImage(Converter.convertBase64StringToImage(carDataFull.getImage1()));
            imvImage2.setImage(Converter.convertBase64StringToImage(carDataFull.getImage2()));
            imvImage3.setImage(Converter.convertBase64StringToImage(carDataFull.getImage3()));
            imvImage4.setImage(Converter.convertBase64StringToImage(carDataFull.getImage4()));
            applyView(carDataFull.getStatus(), carDataFull.isEvacuatedBool(), carDataFull.isDebtActBool(), carDataFull.isBlock());
        }
    }

    private void applyView(String status, boolean evacuated, boolean debtAct, boolean block){
        btnBlock.setDisable(block);
        btnBlockFree.setDisable(!block);

        switch (status){
            case "На стоянке":
                vbOwnerData.setVisible(false);
                vbReleaseData.setVisible(false);
                hbReceiptPoliceDepartment.setVisible(false);
                hbReceiptBank.setVisible(false);
                vbStoringData.setVisible(true);

                btnCreateReceipt.setDisable(false);
                btnReturnToParking.setDisable(true);
                btnRelease.setDisable(true);
                btnReleaseToAnotherParking.setDisable(false);
                btnTakeFromAnotherParking.setDisable(true);
                btnReleaseByDebtAct.setDisable(true);

                btnTakeProtocol.setDisable(false);
                btnTakeDebtAct.setDisable(true);
                btnTakeReceipt.setDisable(true);
                btnTakeReleaseInformation.setDisable(true);
                btnTakePhotos.setDisable(false);

                break;
            case "На оплате":
                hbReceiptPoliceDepartment.setVisible(false);
                hbReceiptBank.setVisible(false);
                vbStoringData.setVisible(true);

                btnCreateReceipt.setDisable(true);
                btnReturnToParking.setDisable(false);
                btnRelease.setDisable(false);
                btnReleaseToAnotherParking.setDisable(false);
                btnTakeFromAnotherParking.setDisable(true);
                btnReleaseByDebtAct.setDisable(false);

                btnTakeProtocol.setDisable(false);
                btnTakeDebtAct.setDisable(true);
                btnTakeReceipt.setDisable(false);
                btnTakeReleaseInformation.setDisable(true);
                btnTakePhotos.setDisable(false);
                break;
            case "На перемещении":
                vbOwnerData.setVisible(false);
                vbReleaseData.setVisible(false);
                vbStoringData.setVisible(false);
                hbReceiptPoliceDepartment.setVisible(false);
                hbReceiptBank.setVisible(false);

                btnCreateReceipt.setDisable(true);
                btnReturnToParking.setDisable(false);
                btnRelease.setDisable(true);
                btnReleaseToAnotherParking.setDisable(true);
                btnTakeFromAnotherParking.setDisable(false);
                btnReleaseByDebtAct.setDisable(true);
                btnTakeProtocol.setDisable(false);
                btnTakeDebtAct.setDisable(true);
                btnTakeReceipt.setDisable(true);
                btnTakeReleaseInformation.setDisable(true);
                btnTakePhotos.setDisable(false);
                break;

            case "Выдано":
                vbOwnerData.setVisible(true);
                vbReleaseData.setVisible(true);
                vbStoringData.setVisible(true);
                btnCreateReceipt.setDisable(true);
                btnReturnToParking.setDisable(false);
                btnRelease.setDisable(true);
                btnReleaseToAnotherParking.setDisable(true);
                btnTakeFromAnotherParking.setDisable(true);
                btnReleaseByDebtAct.setDisable(true);
                btnTakeProtocol.setDisable(false);
                btnTakeDebtAct.setDisable(true);
                btnTakeReceipt.setDisable(false);
                btnTakeReleaseInformation.setDisable(false);
                btnTakePhotos.setDisable(false);
                break;

            case  "Выдано по долговому акту":
                vbOwnerData.setVisible(true);
                vbReleaseData.setVisible(true);
                vbStoringData.setVisible(true);
                btnCreateReceipt.setDisable(true);
                btnReturnToParking.setDisable(false);
                btnRelease.setDisable(true);
                btnReleaseToAnotherParking.setDisable(true);
                btnTakeFromAnotherParking.setDisable(true);
                btnReleaseByDebtAct.setDisable(true);
                btnTakeProtocol.setDisable(false);
                btnTakeDebtAct.setDisable(false);
                btnTakeReceipt.setDisable(false);
                btnTakeReleaseInformation.setDisable(false);
                btnTakePhotos.setDisable(false);
                break;

        }
        hbEvacuationCosts.setVisible(evacuated);
        hbWrecker.setVisible(evacuated);
        hbEvacuationDate.setVisible(evacuated);
        hbEvacuationOrganization.setVisible(evacuated);
        hbEvacuationAddress.setVisible(evacuated);
        hbDebtActNumber.setVisible(debtAct);


    }

    public void blockUI(boolean block, String info){
        if(block){
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
            Platform.runLater(() -> {
                try {
                    if(formLoading!=null){
                        formLoading.stop(info);
                        formLoading = null;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            });


        }
        brdpFull.setVisible(!block);
        vbTop.setVisible(!block);
        vbLeft.setVisible(!block);
        vbCenter.setVisible(!block);
        vbRight.setVisible(!block);

    }

    public Stage getPrimaryStage(){
        return primaryStage;
    }

    @Override
    public void showPicture(ImageView imv) {
        if(imv.equals(imvImage1)){
            FormImageFull frm = new FormImageFull(this.identifier, 1);
            frm.start(null);
        }
        if(imv.equals(imvImage2)){
            FormImageFull frm = new FormImageFull(this.identifier, 2);
            frm.start(null);
        }
        if(imv.equals(imvImage3)){
            FormImageFull frm = new FormImageFull(this.identifier, 3);
            frm.start(null);
        }
        if(imv.equals(imvImage4)){
            FormImageFull frm = new FormImageFull(this.identifier, 4);
            frm.start(null);
        }
    }

    @Override
    public void onChangeData() {
        getActualData();
    }
}
