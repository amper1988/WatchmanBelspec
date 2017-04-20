import com.sun.javafx.scene.control.skin.TableViewSkin;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.CarDataForLists;
import retrofit.Api;
import retrofit.model.CarDataShort;
import retrofit.model.get_car_list_from_base.request.GetCarListFromBaseRequestEnvelope;
import retrofit.model.get_car_list_from_base.response.GetCarListFromBaseResponseEnvelope;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utils.Converter;
import utils.Encode;
import utils.UserManager;
import utils.Utils;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerFormCarListFromBase implements Initializable {
    @FXML
    private TableView<CarDataForLists> tblSearchResult;
    @FXML
    private TableColumn<CarDataForLists, String> tbcProtocolNumber;
    @FXML
    private TableColumn<CarDataForLists, String> tbcManufacture;
    @FXML
    private TableColumn<CarDataForLists, String> tbcCarId;
    @FXML
    private TableColumn<CarDataForLists, String> tbcPoliceDepartment;
    @FXML
    private TableColumn<CarDataForLists, String> tbcClause;
    @FXML
    private TableColumn<CarDataForLists, String> tbcParkingDate;
    @FXML
    private TableColumn<CarDataForLists, String> tbcReleaseDate;
    @FXML
    private TableColumn<CarDataForLists, String> tbcStatus;

    private String param = "";
    private boolean loadingData = false;
    private Stage primaryStage = null;
    private ObservableList<CarDataForLists> listFromBase = FXCollections.observableArrayList();
    private int page = -1;

    private int getCarListCount = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initializeTableEvent();
    }

    public void setIdentifier(String param, Stage stage){
        this.page = -1;
        this.param= param;
        this.primaryStage = stage;
        refreshLists();

    }

    private void refreshLists(){
        loadMoreFromServer(param);
    }

    private void initializeTableEvent(){
        tblSearchResult.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                showDetails();
            }
        });
        //Set handler for scroll event.
        tblSearchResult.addEventFilter(ScrollEvent.ANY, scrollEvent -> loadMoreFromServer(param));
        //Set handler for key pressed event
        tblSearchResult.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER){
                showDetails();
            }
            if (event.getCode() == KeyCode.DOWN){
                //load more data when list scrolled to end;
                loadMoreFromServer(param);
            }
            if(event.getCode() == KeyCode.END){
                //load more data from server ;
                loadMoreFromServer(param);
            }
        });
    }

    private void showDetails(){

        try {
            FormCarDetails frm = new FormCarDetails(tblSearchResult.getSelectionModel().getSelectedItem().getIdentifier());
            frm.start(null);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private void loadMoreFromServer(String param){
        if(getLastVisibleItem(tblSearchResult)+ 100 > page*200 && !loadingData){
            loadingData = true;
            Api.createRetrofitService().executeGetCarListFromBase(Encode.getBasicAuthTemplate(UserManager.getInstanse().getmLogin(), UserManager.getInstanse().getmPassword()),
                    new GetCarListFromBaseRequestEnvelope(++page, param)).enqueue(new Callback<GetCarListFromBaseResponseEnvelope>() {
                @Override
                public void onResponse(Call<GetCarListFromBaseResponseEnvelope> call, final Response<GetCarListFromBaseResponseEnvelope> response) {
                    loadingData = false;
                    getCarListCount = 0;
                    if(response.code() == 200){
                        ObservableList<CarDataForLists> observableList = FXCollections.observableArrayList();
                        List<CarDataShort> carDataShortList = response.body().getData().getCarDataShortList();
                        if (carDataShortList != null){
                            int index = 0;
                            for(CarDataShort carDataShort: carDataShortList){
                                try {
                                    observableList.add(Converter.convertCarDataShortToCarDataForLists(carDataShort, index++));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                            //add information to list;
                            if(page == 0){
                                listFromBase.clear();
                            }
                            listFromBase.addAll(observableList);
                            //show new information in UI;
                            showTable(listFromBase);
                        }else if(page == 0){
                            listFromBase.clear();
                            showTable(listFromBase);
                        }
                    }else{
                        page--;
                        Platform.runLater(() -> Utils.showAlertMessage("Ошибка сервера " + response.code(), Converter.convertResponseToSting(response.errorBody())));
                    }
                }

                @Override
                public void onFailure(Call<GetCarListFromBaseResponseEnvelope> call, final Throwable t) {
                    loadingData = false;
                    page--;
                    if(getCarListCount++ < Main.COUNT_RETRY){
                        loadMoreFromServer(param);
                    }else {
                        getCarListCount = 0;
                        Platform.runLater(() -> Utils.showAlertMessage("Ошибка отправления запроса", t.getMessage()));
                    }
                }
            });
        }

    }

    private void showTable(ObservableList<CarDataForLists> observableList){
        tblSearchResult.setItems(observableList);
        tbcProtocolNumber.setCellValueFactory(cellData -> cellData.getValue().protocolNumberProperty());
        tbcProtocolNumber.setCellValueFactory(cellData -> cellData.getValue().protocolNumberProperty());
        tbcManufacture.setCellValueFactory(cellData -> cellData.getValue().manufactureProperty());
        tbcCarId.setCellValueFactory(cellData -> cellData.getValue().carIdProperty());
        tbcPoliceDepartment.setCellValueFactory(cellData -> cellData.getValue().policeDepartmentProperty());
        tbcClause.setCellValueFactory(cellData -> cellData.getValue().clauseProperty());
        tbcParkingDate.setCellValueFactory(cellData -> cellData.getValue().parkingDateProperty());
        tbcStatus.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
        tbcReleaseDate.setCellValueFactory(cellData -> cellData.getValue().releaseDateProperty());
    }

    /**
     * Get last visible item on UI in table.
     * @param table TableView
     * @return lat visible item from table or 0 if table is null;
     */
    private int getLastVisibleItem(TableView<?> table){
        try{
            return ((VirtualFlow)((TableViewSkin<?>) table.getSkin() ).getChildren().get(1)).getLastVisibleCell().getIndex();
        }catch (NullPointerException e){
            return 0;
        }

    }
}
