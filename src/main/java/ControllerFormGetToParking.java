import interfaces.ChangerListener;
import interfaces.LoadingDataComplete;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.AutoCompleteBoxWithDep;
import model.DataChangeObserver;
import model.LoadingDataFormGetToParkingComplete;
import retrofit.Api;
import retrofit.RetrofitService;
import retrofit.model.*;
import retrofit.model.get_car_details.request.GetCarDetailsRequestEnvelope;
import retrofit.model.get_car_details.response.GetCarDetailsResponseEnvelope;
import retrofit.model.get_car_to_parking.request.GetCarToParkingRequestEnvelope;
import retrofit.model.get_car_to_parking.response.GetCarToParkingResponseEnvelope;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utils.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class ControllerFormGetToParking implements Initializable, LoadingDataComplete, ChangerListener {
    @FXML
    private Button btnGetToParking;
    @FXML
    private Button btnAddPoliceman;
    @FXML
    private ImageView imvImage1;
    @FXML
    private ImageView imvImage2;
    @FXML
    private ImageView imvImage3;
    @FXML
    private ImageView imvImage4;
    @FXML
    private Button btnInsertImage1;
    @FXML
    private Button btnInsertImage2;
    @FXML
    private Button btnInsertImage3;
    @FXML
    private Button btnInsertImage4;
    @FXML
    private ComboBox<String> cmbManufacture;
    @FXML
    private ComboBox<String> cmbModel;
    @FXML
    private ComboBox<String> cmbColor;
    @FXML
    private ComboBox<String> cmbClause;
    @FXML
    private ComboBox<String> cmbPoliceDepartment;
    @FXML
    private ComboBox<String> cmbPoliceman;
    @FXML
    private ComboBox<String> cmbEvacuationOrganization;
    @FXML
    private ComboBox<String> cmbWrecker;
    @FXML
    private TextField txtEvacuationAddress;
    @FXML
    private TextField txtCarId;
    @FXML
    private HBox hbEvacuationOrganization;
    @FXML
    private HBox hbWrecker;
    @FXML
    private HBox hbEvacuationAddress;
    @FXML
    private CheckBox cbEvacuatedBool;
    @FXML
    private BorderPane brdpFull;

    private LoadingDataFormGetToParkingComplete loadingDataFormGetToParkingComplete;
    private int identifier = -1;
    private Image image1;
    private Image image2;
    private Image image3;
    private Image image4;
    private boolean canClose = true;
    private Stage primaryStage = null;
    private FormLoading formLoading = null;
    private int getCarToParkingCount;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initViews();
        initializeButtonsEvent();
    }

    private Image getJPGFile(Scene scene, int maxWidthHeightSize){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add( new FileChooser.ExtensionFilter("JPG", "*.jpg"));
        File file = fileChooser.showOpenDialog(scene.getWindow());
        if(file != null){
            Image image = new Image(file.toURI().toString());
            int width = (int)image.getWidth();
            int height = (int)image.getHeight();

            if(width > maxWidthHeightSize || height > maxWidthHeightSize){
                double ratio = Math.min(
                        (float) maxWidthHeightSize/ width,
                        (float) maxWidthHeightSize / height);
                width = Math.round((float) ratio * width);
                height = Math.round((float) ratio * height);
            }
           return new Image(file.toURI().toString(), width, height, false, false);
        }
        return null;
    }

    private void initializeButtonsEvent(){
        btnInsertImage1.setOnAction(actionEvent -> {
            Image image = getJPGFile(btnInsertImage1.getScene(), 1280);
            if(image!= null) {
                image1 = image;
                imvImage1.setImage(image);
            }
        });

        btnInsertImage2.setOnAction(actionEvent -> {
            Image image = getJPGFile(btnInsertImage2.getScene(), 1280);
            if(image!=null){
                image2 = image;
                imvImage2.setImage(image);
            }
        });
        btnInsertImage3.setOnAction(actionEvent -> {
            Image image = getJPGFile(btnInsertImage3.getScene(), 1280);
            if(image!=null){
                image3 = image;
                imvImage3.setImage(image);
            }
        });
        btnInsertImage4.setOnAction(actionEvent -> {
            Image image = getJPGFile(btnInsertImage4.getScene(), 1280);
            if(image!=null){
                image4 = image;
                imvImage4.setImage(image);
            }
        });
        btnGetToParking.setOnAction(actionEvent -> {
            if(confirmData()){
                getCarToParkingCount = 0;
                blockUI(true, "Принятие транспортного средства на стоянку");
                RetrofitService retrofitService = Api.createRetrofitService();
                retrofitService.executeGetCarToParking(Encode.getBasicAuthTemplate(UserManager.getInstanse().getmLogin(), UserManager.getInstanse().getmPassword()),
                        new GetCarToParkingRequestEnvelope(identifier, cmbManufacture.getValue(), cmbModel.getValue(), cmbColor.getValue(), txtCarId.getText(),
                                cmbPoliceDepartment.getValue(), cmbPoliceman.getValue(), cmbClause.getValue(), cbEvacuatedBool.isSelected(), cmbEvacuationOrganization.getValue(), cmbWrecker.getValue()
                                ,txtEvacuationAddress.getText(), Converter.convertImageToBase64String(image1), Converter.convertImageToBase64String(image2) ,
                                Converter.convertImageToBase64String(image3), Converter.convertImageToBase64String(image4))).enqueue(new Callback<GetCarToParkingResponseEnvelope>() {
                    @Override
                    public void onResponse(Call<GetCarToParkingResponseEnvelope> call, final Response<GetCarToParkingResponseEnvelope> response) {
                        blockUI(false, "Данные о принятии переданы.");
                        if(response.code() == 200){
                            final int serverCode = response.body().getAnswer().getCode();
                            if(serverCode == 1 ){
                                Platform.runLater(() -> {
                                    Utils.showAlertMessage("Сообщение от сервера", "Транспортное средство успешно добавлено на стоянку. "+ response.body().getAnswer().getDescription());
                                    DataChangeObserver.getInstance().dataChangeNotify();
                                    close();
                                });

                            }else{
                                Platform.runLater(() -> Utils.showAlertMessage("Ошибка сервера.", "Код ошибки: "+ serverCode +
                                        " \nОписание ошибки: "+ response.body().getAnswer().getDescription()));
                            }

                        }else{
                            Platform.runLater(() -> Utils.showAlertMessage("Ошибка при попытке принять на стоянку.", Converter.convertResponseToSting(response.errorBody())));
                        }
                    }

                    @Override
                    public void onFailure(Call<GetCarToParkingResponseEnvelope> call, final Throwable t) {
                        if(getCarToParkingCount++ < Main.COUNT_RETRY){
                            btnGetToParking.fire();
                        }else{
                            getCarToParkingCount = 0;
                            blockUI(false, "Ошибка при попытке принять на стоянку.");
                            Platform.runLater(() -> Utils.showAlertMessage("Ошибка при попытке принять на стоянку.", t.getMessage()));
                        }
                    }
                });
            }else{
                Platform.runLater(() -> Utils.showAlertMessage("Данные не заполнены.", "Проверьте выбранные данные."));
            }
        });

        btnAddPoliceman.setOnAction(actionEvent -> {
            FormCreatePoliceman frm = new FormCreatePoliceman(ControllerFormGetToParking.this, cmbPoliceDepartment.getSelectionModel().getSelectedItem());
            frm.start(null);
        });
    }

    private void close(){
        ((Stage)btnInsertImage1.getScene().getWindow()).close();
    }

    private void initViews(){
        cbEvacuatedBool.setSelected(false);
        hbEvacuationOrganization.setVisible(false);
        hbWrecker.setVisible(false);
        hbEvacuationAddress.setVisible(false);
        cbEvacuatedBool.setOnAction(actionEvent -> Platform.runLater(() -> {
            if(cbEvacuatedBool.isSelected()){
                hbEvacuationOrganization.setVisible(true);
                hbEvacuationAddress.setVisible(true);
                hbWrecker.setVisible(true);
            }else{
                hbEvacuationOrganization.setVisible(false);
                hbEvacuationAddress.setVisible(false);
                hbWrecker.setVisible(false);
            }


        }));
        cmbModel.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            KeyCode code = event.getCode();
            if(code == KeyCode.ENTER)
                txtCarId.requestFocus();
        });
        cmbWrecker.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            KeyCode code = event.getCode();
            if(code == KeyCode.ENTER)
                txtEvacuationAddress.requestFocus();
        });
        cmbPoliceman.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            KeyCode code = event.getCode();
            if(code == KeyCode.ENTER){
                cmbClause.requestFocus();
            }
        });
        cmbColor.getEditor().setOnKeyTyped(event -> {
            KeyCode code = event.getCode();
            if(code == KeyCode.ENTER)
                txtCarId.requestFocus();
        });
        txtCarId.addEventHandler(KeyEvent.KEY_PRESSED, event ->{
            KeyCode code = event.getCode();
            if(code == KeyCode.ENTER){
                cmbPoliceDepartment.requestFocus();
            }
        });
        cmbClause.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            KeyCode code = event.getCode();
            if(code == KeyCode.ENTER) {
                cbEvacuatedBool.requestFocus();
            }
        });
        cbEvacuatedBool.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            KeyCode code = event.getCode();
            if(code == KeyCode.ENTER){
                if(cbEvacuatedBool.isSelected())
                    cmbEvacuationOrganization.requestFocus();
                else
                    btnGetToParking.requestFocus();
            }
        });
        txtEvacuationAddress.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if(event.getCode() == KeyCode.ENTER){
                btnGetToParking.requestFocus();
            }
        });
    }

    private boolean confirmData(){
        if(cmbManufacture.getSelectionModel().isEmpty()){
            return false;
        }
        if(cmbPoliceDepartment.getSelectionModel().isEmpty()){
            return false;
        }
        if(cmbPoliceman.getSelectionModel().isEmpty()){
            return false;
        }
        if(txtCarId.getText().isEmpty()){
            return false;
        }
        if(cbEvacuatedBool.isSelected()){
            if(cmbEvacuationOrganization.getSelectionModel().isEmpty()){
                return false;
            }
            if(cmbWrecker.getSelectionModel().isEmpty()){
                return false;
            }
            if(txtEvacuationAddress.getText().isEmpty()){
                return false;
            }
        }
        return true;
    }

    public void setStage(Stage stage){
        this.primaryStage = stage;
    }

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
                if(formLoading != null){
                    try {
                        formLoading.stop(info);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                formLoading = null;
            });

        }
//        brdpFull.setVisible(!block);
//        btnInsertImage1.setDisable(block);
//        btnInsertImage2.setDisable(block);
//        btnInsertImage3.setDisable(block);
//        btnInsertImage4.setDisable(block);
//        btnGetToParking.setDisable(block);
//        cmbManufacture.setDisable(block);
//        cmbModel.setDisable(block);
//        cmbColor.setDisable(block);
//        txtCarId.setDisable(block);
//        cmbPoliceDepartment.setDisable(block);
//        cmbPoliceman.setDisable(block);
//        cmbClause.setDisable(block);
//        cbEvacuatedBool.setDisable(block);
//        cmbEvacuationOrganization.setDisable(block);
//        cmbWrecker.setDisable(block);
//        txtEvacuationAddress.setDisable(block);
        Platform.setImplicitExit(block);
        canClose = !block;
        if(primaryStage != null){
            primaryStage.setOnCloseRequest(event -> {
                if (!canClose)
                    event.consume();
            });
        }
    }

    public void setIdentifier(int identifier, Stage stage) {
        this.identifier = identifier;
        this.primaryStage = stage;
        loadingDataFormGetToParkingComplete = new LoadingDataFormGetToParkingComplete(this);
        loadingDataFormGetToParkingComplete.loadData();
        blockUI(true, "Получение данных о транпортном средстве.");
        if (this.identifier != -1) {
           getActualData();
        }
    }

    private void getActualData(){
        blockUI(true, "Получение данных о транспортном средстве.");
        while (!loadingDataFormGetToParkingComplete.loadingIsComplete()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(identifier != -1){
            RetrofitService retrofitService = Api.createRetrofitService();
            retrofitService.executeGetCarDetails(Encode.getBasicAuthTemplate(UserManager.getInstanse().getmLogin(), UserManager.getInstanse().getmPassword()),
                    new GetCarDetailsRequestEnvelope(identifier)).enqueue(new Callback<GetCarDetailsResponseEnvelope>() {
                @Override
                public void onResponse(Call<GetCarDetailsResponseEnvelope> call, final Response<GetCarDetailsResponseEnvelope> response) {
                    blockUI(false, "Даные получены с сервера");
                    if (response.code() == 200) {
                        Platform.runLater(() -> setData(response.body().getCarDataFull()));
                    } else {
                        Platform.runLater(() -> Utils.showAlertMessage("Response error code " + response.code(), Converter.convertResponseToSting(response.errorBody())));
                    }
                }

                @Override
                public void onFailure(Call<GetCarDetailsResponseEnvelope> call, final Throwable t) {
                    blockUI(false, "Ошбика при получении данных транпортного средства.");
                    Platform.runLater(() -> Utils.showAlertMessage("Request fail.", t.getMessage()));
                }
            });
        }

    }

    private void setData(CarDataFull carData){
        if(carData != null){
            imvImage1.setImage(Converter.convertBase64StringToImage(carData.getImage1()));
            imvImage2.setImage(Converter.convertBase64StringToImage(carData.getImage2()));
            imvImage3.setImage(Converter.convertBase64StringToImage(carData.getImage3()));
            imvImage4.setImage(Converter.convertBase64StringToImage(carData.getImage4()));
            cmbManufacture.getSelectionModel().select(carData.getManufacture());
            cmbModel.getSelectionModel().select(carData.getModel());
            cmbColor.getSelectionModel().select(carData.getColor());
            txtCarId.setText(carData.getCarId());
            cmbPoliceDepartment.getSelectionModel().select(carData.getPoliceDepartment());
            cmbPoliceman.getSelectionModel().select(carData.getPoliceman());
            cmbClause.getSelectionModel().select(carData.getClause());
            cbEvacuatedBool.setSelected(carData.isEvacuatedBool());
            hbEvacuationOrganization.setVisible(cbEvacuatedBool.isSelected());
            cmbEvacuationOrganization.getSelectionModel().select(carData.getEvacuationOrganization());
            hbWrecker.setVisible(cbEvacuatedBool.isSelected());
            cmbWrecker.getSelectionModel().select(carData.getWrecker());
            cmbWrecker.setVisible(cbEvacuatedBool.isSelected());
            hbEvacuationAddress.setVisible(cbEvacuatedBool.isSelected());
            txtEvacuationAddress.setText(carData.getEvacuationAddress());
        }

    }

    @Override
    public void onOrganizationsWreckersComplete(List<OrganizationItem> organizationItems) {

    }

    @Override
    public void onManufacturesModelsComplete(List<ManufactureItem> manufactureItems) {

    }

    @Override
    public void onClausesComplete(List<ClauseItem> clauseItems) {

    }

    @Override
    public void onPoliceDepartmentsPolicemanComplete(List<PoliceDepartmentItem> policeDepartmentItems) {

    }

    @Override
    public void onColorComplete(List<ColorItem> colorItems) {

    }

    @Override
    public void onDataComplete(List<OrganizationItem> organizationItems, List<ManufactureItem> manufactureItems, List<ClauseItem> clauseItems, List<PoliceDepartmentItem> policeDepartmentItems) {

        Platform.runLater(()->{
            new AutoCompleteBoxWithDep(cmbManufacture, cmbModel, loadingDataFormGetToParkingComplete.getManufactureItems());
            new AutoCompleteBoxWithDep(cmbPoliceDepartment, cmbPoliceman, loadingDataFormGetToParkingComplete.getPoliceDepartmentItems());
            new AutoCompleteBoxWithDep(cmbEvacuationOrganization, cmbWrecker, loadingDataFormGetToParkingComplete.getOrganizationItems());
            new AutoCompleteBoxWithDep(cmbClause, null, loadingDataFormGetToParkingComplete.getClauseItems());
            new AutoCompleteBoxWithDep(cmbColor, null, loadingDataFormGetToParkingComplete.getColorItems());
            blockUI(false, "Спики данных загружены.");
        });
    }

    @Override
    public void onChangeData() {
        loadingDataFormGetToParkingComplete.loadData();
    }

    public void setFocusDefault(){
        cmbManufacture.requestFocus();
    }
}
