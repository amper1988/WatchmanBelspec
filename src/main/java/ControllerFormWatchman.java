import com.sun.javafx.scene.control.skin.TableViewSkin;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import interfaces.ChangerListener;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import model.CarDataForLists;
import model.DataChangeObserver;
import retrofit.Api;
import retrofit.RetrofitService;
import retrofit.model.CarDataShort;
import retrofit.model.get_cars_on_evacuation.request.GetCarsOnEvacuationRequestEnvelope;
import retrofit.model.get_cars_on_evacuation.response.GetCarsOnEvacuationResponseEnvelope;
import retrofit.model.get_cars_on_parking.request.GetCarsOnParkingRequestEnvelope;
import retrofit.model.get_cars_on_parking.response.GetCarsOnParkingResponseEnvelope;
import retrofit.model.get_cars_on_paying.request.GetCarsOnPayingRequestEnvelope;
import retrofit.model.get_cars_on_paying.response.GetCarsOnPayingResponseEnvelope;
import retrofit.model.get_debt_act_cars.request.GetDebtActCarsRequestEnvelope;
import retrofit.model.get_debt_act_cars.response.GetDebtActCarsResponseEnvelope;
import retrofit.model.get_released_cars.request.GetReleasedCarsRequestEnvelope;
import retrofit.model.get_released_cars.response.GetReleasedCarsResponseEnvelope;
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

import static java.lang.Thread.sleep;


public class ControllerFormWatchman implements Initializable, ChangerListener{
    @FXML
    TableView<CarDataForLists> tblCarOnParking;
    @FXML
    TableColumn<CarDataForLists, String> tbcProtocolNumber;
    @FXML
    TableColumn<CarDataForLists, String> tbcManufacture;
    @FXML
    TableColumn<CarDataForLists, String> tbcCarId;
    @FXML
    TableColumn<CarDataForLists, String> tbcPoliceDepartment;
    @FXML
    TableColumn<CarDataForLists, String> tbcClause;
    @FXML
    TableColumn<CarDataForLists, String> tbcParkingDate;

    @FXML
    TableView<CarDataForLists> tblReleasedCars;
    @FXML
    TableColumn<CarDataForLists, String> tbcProtocolNumberReleased;
    @FXML
    TableColumn<CarDataForLists, String> tbcManufactureReleased;
    @FXML
    TableColumn<CarDataForLists, String> tbcCarIdReleased;
    @FXML
    TableColumn<CarDataForLists, String> tbcParkingDateReleased;
    @FXML
    TableColumn<CarDataForLists, String> tbcReleaseDateReleased;

    @FXML
    TableView<CarDataForLists> tblCarsOnEvacuation;
    @FXML
    TableColumn<CarDataForLists, String> tbcProtocolNumberEvacuation;
    @FXML
    TableColumn<CarDataForLists, String> tbcManufactureEvacuation;
    @FXML
    TableColumn<CarDataForLists, String> tbcModelEvacuation;
    @FXML
    TableColumn<CarDataForLists, String> tbcClauseEvacuation;
    @FXML
    TableColumn<CarDataForLists, String> tbcCarIdEvacuation;
    @FXML
    TableColumn<CarDataForLists, String> tbcPoliceDepartmentEvacuation;

    @FXML
    TableView<CarDataForLists> tblDebtActCars;
    @FXML
    TableColumn<CarDataForLists, String> tbcProtocolNumberDebtAct;
    @FXML
    TableColumn<CarDataForLists, String> tbcManufactureDebtAct;
    @FXML
    TableColumn<CarDataForLists, String> tbcCarIdDebtAct;
    @FXML
    TableColumn<CarDataForLists, String> tbcDebtActNumber;
    @FXML
    TableColumn<CarDataForLists, String> tbcReleaseDateDebtAct;

    @FXML
    TableView<CarDataForLists> tblCarsOnPaying;
    @FXML
    TableColumn<CarDataForLists, String> tbcProtocolNumberOnPaying;
    @FXML
    TableColumn<CarDataForLists, String> tbcManufactureOnPaying;
    @FXML
    TableColumn<CarDataForLists, String> tbcCarIdOnPaying;
    @FXML
    TableColumn<CarDataForLists, String> tbcClauseOnPaying;
    @FXML
    TableColumn<CarDataForLists, String> tbcReleaseDateOnPaying;

    @FXML
    TextField txtOnParkingSearch;
    @FXML
    TextField txtReleasedSearch;
    @FXML
    TextField txtOnEvacuationSearch;
    @FXML
    TextField txtOnPayingSearch;
    @FXML
    TextField txtDebtActSearch;
    @FXML
    Button btnTookOnParking;
    @FXML
    Button btnReleaseFromParking;
    @FXML
    private TextField txtSearchInBase;

    private CarDataForLists lastSelectedOnParking = null;
    private CarDataForLists lastSelectedReleased = null;
    private CarDataForLists lastSelectedOnEvacuation = null;
    private CarDataForLists lastSelectedOnDebtAct = null;
    private CarDataForLists lastSelectedOnPaying = null;

    //contains loaded list from server for cars on parking;
    private ObservableList<CarDataForLists> listOnParking = FXCollections.observableArrayList();
    //Contains loaded list from server for released cars;
    private final ObservableList<CarDataForLists> listReleased = FXCollections.observableArrayList();
    //Contains loaded list from server for cars on evacuation;
    private ObservableList<CarDataForLists> listOnEvacuation = FXCollections.observableArrayList();
    //Contains loaded list from server for debt act cars;
    private ObservableList<CarDataForLists> listDebtAct = FXCollections.observableArrayList();
    //Contains loaded list from server for cars on paying;
    private ObservableList<CarDataForLists> listOnPaying = FXCollections.observableArrayList();

    private boolean parkingLoading = false;
    private int pageOnParking = -1;
    private boolean releasedLoading = false;
    private int pageReleased = -1;
    private boolean evacuationLoading = false;
    private int pageOnEvacuation = -1;
    private boolean payingLoading = false;
    private int pageOnPaying = -1;
    private boolean debtActLoading = false;
    private int pageDebtAct = -1;

    private int loadOnParkingCount = 0;
    private int loadReleasedCount = 0;
    private int loadOnEvacuationCount = 0;
    private int loadDebtActCount = 0;
    private int loadOnPayingCount = 0;

    private boolean windowClosed = false;


    private RetrofitService retrofitService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        retrofitService = Api.createRetrofitService();
        DataChangeObserver.getInstance().setListener(this);
        refreshLists();
        initializeTableParkingEvent();
        initializeTableReleasedEvent();
        initializeTableEvacuationEvent();
        initializeTableDebtActEvent();
        initializeTablePayingEvent();

        startUpdater();

        btnTookOnParking.setOnMouseClicked(mouseEvent -> {
            FormGetToParking frm = new FormGetToParking();
            try {
                frm.start(null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        btnReleaseFromParking.setOnMousePressed(mouseEvent -> {
            {
                if(tblCarOnParking.getSelectionModel().getSelectedItem() != null){
                    int identifier = tblCarOnParking.getSelectionModel().getSelectedItem().getIdentifier();
                    FormTakeReceipt frm = new FormTakeReceipt(identifier, null, tblCarOnParking.getSelectionModel().getSelectedItem().getCarId());

                    boolean block = tblCarOnParking.getSelectionModel().getSelectedItem().isBlock();
                    if(block){
                        Platform.runLater(() -> Utils.showAlertMessage("Автомобиль находится под арестом.", "Автомобиль находится под арестом. Снимите арест перед выставлением счета."));
                    }else{
                        try {
                            frm.start(null);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }else
                    Utils.showAlertMessage("Не выбрано значение", "Выберите необходимое для выдачи транспортное средство, выделив его в списке.");

            }
        });
    }

    /**
     * Go to server and get information about all needed lists;
     */
    private void refreshLists(){
        loadMoreOnParkingFromServer("");
        loadMoreReleasedCarsFromServer("");
        loadMoreOnEvacuationFromServer("");
        loadMoreDebtActCarsFromServer("");
        loadMoreOnPayingFromServer("");
    }

    private void initializeTableParkingEvent(){
        //Set search on server procedure when typing in text field;
        txtOnParkingSearch.addEventHandler(KeyEvent.KEY_TYPED, keyEvent -> {
            if(keyEvent.getCharacter().hashCode() == 13) {
                String searchString = txtOnParkingSearch.getText() + Utils.returnSymbol(keyEvent.getCharacter());
                pageOnParking = -1; //load from server from start;
                loadMoreOnParkingFromServer(searchString);
            }
        });

        txtSearchInBase.addEventHandler(KeyEvent.KEY_TYPED, keyEvent -> {
            if(keyEvent.getCharacter().hashCode() == 13){
                FormCarListFromBase frm = new FormCarListFromBase(txtSearchInBase.getText());
                try {
                    frm.start(null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        //Set handler for double click event in table parking;
        tblCarOnParking.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton().equals(MouseButton.PRIMARY) && mouseEvent.getClickCount() == 2) {
                showCarDetails(tblCarOnParking.getSelectionModel().getSelectedItem().getIdentifier());
            }
        });
        //Set handler for scroll event.
        tblCarOnParking.addEventFilter(ScrollEvent.ANY, scrollEvent -> loadMoreOnParkingFromServer(txtOnParkingSearch.getText()));
        //Set handler for key pressed event
        tblCarOnParking.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER){
                showCarDetails(tblCarOnParking.getSelectionModel().getSelectedItem().getIdentifier());
            }
            if (keyEvent.getCode() == KeyCode.DOWN){
                //load more data when list scrolled to end;
                loadMoreOnParkingFromServer(txtOnParkingSearch.getText());
            }
            if(keyEvent.getCode() == KeyCode.END){
                //load more data from server ;
                loadMoreOnParkingFromServer(txtOnParkingSearch.getText());
            }
        });
    }

    private void startUpdater(){
        Thread thread = new Thread(() -> {
            while (! windowClosed){
                try {
                    sleep(100000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                pageOnParking = -1;
                lastSelectedOnParking = tblCarOnParking.getSelectionModel().getSelectedItem();
                loadMoreOnParkingFromServer(txtOnParkingSearch.getText());

                pageOnPaying = -1;
                lastSelectedOnPaying = tblCarsOnPaying.getSelectionModel().getSelectedItem();
                loadMoreOnPayingFromServer(txtOnPayingSearch.getText());

                pageOnEvacuation = -1;
                lastSelectedOnEvacuation = tblCarsOnEvacuation.getSelectionModel().getSelectedItem();
                loadMoreOnEvacuationFromServer(txtOnEvacuationSearch.getText());

                pageReleased = -1;
                lastSelectedReleased = tblReleasedCars.getSelectionModel().getSelectedItem();
                loadMoreReleasedCarsFromServer(txtReleasedSearch.getText());

                pageDebtAct = -1;
                lastSelectedOnDebtAct = tblDebtActCars.getSelectionModel().getSelectedItem();
                loadMoreDebtActCarsFromServer(txtDebtActSearch.getText());

                System.out.println("thread2 worked");
            }
        });
        thread.start();
    }
//
//    public boolean isWindowClosed() {
//        return windowClosed;
//    }

    void setWindowClosed() {
        this.windowClosed = true;
        DataChangeObserver.getInstance().deleteListener(this);
    }

    private void initializeTableReleasedEvent(){
        //Set search on server procedure when typing in text field;
        txtReleasedSearch.addEventHandler(KeyEvent.KEY_RELEASED, keyEvent -> {
            if(keyEvent.getCode() == KeyCode.ENTER){
                String searchString = txtReleasedSearch.getText()+Utils.returnSymbol(keyEvent.getCharacter());
                pageReleased = -1; //load from server from start;
                loadMoreReleasedCarsFromServer(searchString);
            }
        });
        //Set handler for double click event in table parking;
        tblReleasedCars.setOnMousePressed(mouseEvent -> {
            if (mouseEvent.isPrimaryButtonDown() && mouseEvent.getClickCount() == 2) {
                showCarDetails(tblReleasedCars.getSelectionModel().getSelectedItem().getIdentifier());
            }
        });
        //Set handler for scroll event.
        tblReleasedCars.addEventFilter(ScrollEvent.ANY, scrollEvent -> loadMoreReleasedCarsFromServer(txtReleasedSearch.getText()));
        //Set handler for key pressed event
        tblReleasedCars.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER){
                showCarDetails(tblReleasedCars.getSelectionModel().getSelectedItem().getIdentifier());
            }
            if (event.getCode() == KeyCode.DOWN){
                //load more data when list scrolled to end;
                loadMoreReleasedCarsFromServer(txtReleasedSearch.getText());
            }
            if(event.getCode() == KeyCode.END){
                //load more data from server ;
                loadMoreReleasedCarsFromServer(txtReleasedSearch.getText());
            }
        });
    }

    private void initializeTableEvacuationEvent(){
        txtOnEvacuationSearch.addEventHandler(KeyEvent.KEY_TYPED, event -> {
            if(event.getCharacter().hashCode() == 13){
                String searchString = txtOnEvacuationSearch.getText()+Utils.returnSymbol(event.getCharacter());
                pageOnEvacuation = -1; //load from server from start;
                loadMoreOnEvacuationFromServer(searchString);
            }
        });
        tblCarsOnEvacuation.setOnMousePressed(event -> {
            if(event.isPrimaryButtonDown() && event.getClickCount() == 2){
                showDetailsOnEvacuation();
            }
        });
        tblCarsOnEvacuation.addEventFilter(ScrollEvent.ANY, scrollEvent -> loadMoreOnEvacuationFromServer(txtOnEvacuationSearch.getText()));
        tblCarsOnEvacuation.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if(event.getCode() == KeyCode.ENTER){
                showDetailsOnEvacuation();
            }
            if(event.getCode() == KeyCode.DOWN){
                loadMoreOnEvacuationFromServer(txtOnEvacuationSearch.getText());
            }
            if(event.getCode() == KeyCode.END){
                loadMoreOnEvacuationFromServer(txtOnEvacuationSearch.getText());
            }
        });

    }

    private void initializeTableDebtActEvent(){
        txtDebtActSearch.addEventHandler(KeyEvent.KEY_TYPED, event -> {
            if(event.getCharacter().hashCode() == 13){
                String searchString = txtDebtActSearch.getText()+Utils.returnSymbol(event.getCharacter());
                pageDebtAct = -1; //load from server from start;
                loadMoreDebtActCarsFromServer(searchString);
            }
        });
        tblDebtActCars.setOnMousePressed(event -> {
            try {
                if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                    showCarDetails(tblDebtActCars.getSelectionModel().getSelectedItem().getIdentifier());
                }
            }catch(Throwable t){
                t.printStackTrace();
            }
        });
        tblDebtActCars.addEventFilter(ScrollEvent.ANY, scrollEvent -> loadMoreDebtActCarsFromServer(txtDebtActSearch.getText()));
        tblDebtActCars.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if(event.getCode() == KeyCode.ENTER){
                showCarDetails(tblDebtActCars.getSelectionModel().getSelectedItem().getIdentifier());
            }
            if(event.getCode() == KeyCode.DOWN){
                loadMoreDebtActCarsFromServer(txtDebtActSearch.getText());
            }
            if(event.getCode() == KeyCode.END){
                loadMoreDebtActCarsFromServer(txtDebtActSearch.getText());
            }
        });
    }

    private void initializeTablePayingEvent(){
        txtOnPayingSearch.addEventHandler(KeyEvent.KEY_TYPED, event -> {
            if(event.getCharacter().hashCode() == 13){
                String searchString = txtOnPayingSearch.getText()+Utils.returnSymbol(event.getCharacter());
                pageOnPaying = -1; //load from server from start;
                loadMoreOnPayingFromServer(searchString);
            }
        });
        tblCarsOnPaying.setOnMousePressed(event -> {
            if(event.isPrimaryButtonDown() && event.getClickCount() == 2){
                showCarDetails(tblCarsOnPaying.getSelectionModel().getSelectedItem().getIdentifier());
            }
        });
        tblCarsOnPaying.addEventFilter(ScrollEvent.ANY, scrollEvent -> loadMoreDebtActCarsFromServer(txtOnPayingSearch.getText()));
        tblCarsOnPaying.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            {
                if(event.getCode() == KeyCode.ENTER){
                    showCarDetails(tblCarsOnPaying.getSelectionModel().getSelectedItem().getIdentifier());
                }
                if(event.getCode() == KeyCode.DOWN){
                    loadMoreOnPayingFromServer(txtOnPayingSearch.getText());
                }
                if(event.getCode() == KeyCode.END){
                    loadMoreOnPayingFromServer(txtOnPayingSearch.getText());
                }
            }
        });
    }

    private void showTableOnParking(ObservableList<CarDataForLists> observableList){
        Platform.runLater(()->{
            tblCarOnParking.setItems(observableList);
            tbcProtocolNumber.setCellValueFactory(cellData -> cellData.getValue().protocolNumberProperty());
            tbcManufacture.setCellValueFactory(cellData -> cellData.getValue().manufactureProperty());
            tbcCarId.setCellValueFactory(cellData -> cellData.getValue().carIdProperty());
            tbcPoliceDepartment.setCellValueFactory(cellData -> cellData.getValue().policeDepartmentProperty());
            tbcClause.setCellValueFactory(cellData -> cellData.getValue().clauseProperty());
            tbcParkingDate.setCellValueFactory(cellData -> cellData.getValue().parkingDateProperty());
            tblCarOnParking.setRowFactory(new javafx.util.Callback<TableView<CarDataForLists>, TableRow<CarDataForLists>>() {
                @Override
                public TableRow<CarDataForLists> call(TableView<CarDataForLists> carDataForListsTableView) {
                    return new TableRow<CarDataForLists>(){
                        @Override
                        protected void updateItem(CarDataForLists item, boolean empty) {
                            super.updateItem(item, empty);
                            if(item!=null && item.isBlock()){
                                setStyle("-fx-background-color: orange");
                            }else{
                                setTextFill(Color.BLACK);
                                setStyle("");
                            }

                        }
                    };
                }
            });
            if(lastSelectedOnParking != null)
                tblCarOnParking.getSelectionModel().select(lastSelectedOnParking.getIndexInTable());
        });

    }

    private void showTableReleased(ObservableList<CarDataForLists> observableList){
        Platform.runLater(()->{
            tblReleasedCars.setItems(observableList);
            tbcProtocolNumberReleased.setCellValueFactory(cellData -> {
                if(cellData != null && cellData.getValue() != null)
                    return cellData.getValue().protocolNumberProperty();
                return null;
            });
            tbcManufactureReleased.setCellValueFactory(cellData -> {
                if(cellData != null && cellData.getValue() != null)
                    return cellData.getValue().manufactureProperty();
                return null;
            });
            tbcCarIdReleased.setCellValueFactory(cellData -> {
                if(cellData != null && cellData.getValue() != null)
                    return cellData.getValue().carIdProperty();
                return null;
            });
            tbcParkingDateReleased.setCellValueFactory(cellData -> {
                if(cellData != null && cellData.getValue() != null)
                    return cellData.getValue().parkingDateProperty();
                return null;
            });
            tbcReleaseDateReleased.setCellValueFactory(cellData -> {
                if(cellData != null && cellData.getValue() != null)
                    return cellData.getValue().releaseDateProperty();
                return null;
            });
            if(lastSelectedReleased != null)
                tblReleasedCars.getSelectionModel().select(lastSelectedReleased.getIndexInTable());
        });

    }

    private void showTableOnEvacuation(ObservableList<CarDataForLists> observableList){
        Platform.runLater(()->{
            tblCarsOnEvacuation.setItems(observableList);
            tbcProtocolNumberEvacuation.setCellValueFactory(cellData -> cellData.getValue().protocolNumberProperty());
            tbcManufactureEvacuation.setCellValueFactory(cellData -> cellData.getValue().manufactureProperty());
            tbcModelEvacuation.setCellValueFactory(cellData -> cellData.getValue().modelProperty());
            tbcCarIdEvacuation.setCellValueFactory(cellData -> cellData.getValue().carIdProperty());
            tbcClauseEvacuation.setCellValueFactory(cellData -> cellData.getValue().clauseProperty());
            tbcPoliceDepartmentEvacuation.setCellValueFactory(cellData -> cellData.getValue().policeDepartmentProperty());
            tblCarsOnEvacuation.getSelectionModel().select(lastSelectedOnEvacuation);
            if(lastSelectedOnEvacuation != null)
                tblCarsOnEvacuation.getSelectionModel().select(lastSelectedOnEvacuation.getIndexInTable());
        });

    }

    private void showTableOnPaying(ObservableList<CarDataForLists> observableList){
        Platform.runLater(()->{
            tblCarsOnPaying.setItems(observableList);
            tbcProtocolNumberOnPaying.setCellValueFactory(cellData -> cellData.getValue().protocolNumberProperty());
            tbcManufactureOnPaying.setCellValueFactory(cellData -> cellData.getValue().manufactureProperty());
            tbcCarIdOnPaying.setCellValueFactory(cellData -> cellData.getValue().carIdProperty());
            tbcClauseOnPaying.setCellValueFactory(cellData -> cellData.getValue().clauseProperty());
            tbcReleaseDateOnPaying.setCellValueFactory(cellData -> cellData.getValue().releaseDateProperty());
            tblCarsOnPaying.getSelectionModel().select(lastSelectedOnPaying);
            if(lastSelectedOnPaying != null)
                tblCarsOnPaying.getSelectionModel().select(lastSelectedOnPaying.getIndexInTable());
        });

    }

    private void showTableDebtAct(ObservableList<CarDataForLists> observableList){
        Platform.runLater(()->{
            tblDebtActCars.setItems(observableList);
            tbcProtocolNumberDebtAct.setCellValueFactory(cellData -> cellData.getValue().protocolNumberProperty());
            tbcManufactureDebtAct.setCellValueFactory(cellData -> cellData.getValue().manufactureProperty());
            tbcCarIdDebtAct.setCellValueFactory(cellData -> cellData.getValue().carIdProperty());
            tbcDebtActNumber.setCellValueFactory(cellData -> cellData.getValue().debtActNumberProperty());
            tbcReleaseDateDebtAct.setCellValueFactory(cellData -> cellData.getValue().releaseDateProperty());
            tblDebtActCars.getSelectionModel().select(lastSelectedOnDebtAct);
            if(lastSelectedOnDebtAct != null)
                tblDebtActCars.getSelectionModel().select(lastSelectedOnDebtAct.getIndexInTable());
        });

    }

    /**
     * Get last visible item on UI in table.
     * @param table TableView
     * @return lat visible item from table or 0 if table is null;
     */
    private int getLastVisibleItem(TableView<?> table){
        try{
            return ((VirtualFlow)((TableViewSkin<?>) table.getSkin()).getChildren().get(1)).getLastVisibleCell().getIndex();
        }catch (NullPointerException e){
            return 0;
        }

    }

    /**
     * Go to server and load more information about cars on parking
     * Depends on private field 'pageOnParking'
     * 'pageOnParking' contains information about loaded pages from server
     */
    private void loadMoreOnParkingFromServer( String param){
        if(((getLastVisibleItem(tblCarOnParking)+100 > pageOnParking*200) && !parkingLoading)){
            //list ends soon;
            //Go to server and get more information;
            parkingLoading = true; //set flags that data is loading;
            retrofitService.executeGetCarOnParking(Encode.getBasicAuthTemplate(UserManager.getInstanse().getmLogin(), UserManager.getInstanse().getmPassword()),
                    new GetCarsOnParkingRequestEnvelope(++pageOnParking, param)).enqueue(new Callback<GetCarsOnParkingResponseEnvelope>() {
                @Override
                public void onResponse(Call<GetCarsOnParkingResponseEnvelope> call, final Response<GetCarsOnParkingResponseEnvelope> response) {
                    if(response.code() == 200){
                        //response is good;
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
                            Platform.runLater(()->{
                                    //add information to list;
                                    if(pageOnParking == 0){
                                        listOnParking.clear();
                                    listOnParking.addAll(observableList);
                                    //show new information in UI;
                                    showTableOnParking(listOnParking);
                                }

                            });

                        }else if(!param.equals("") || pageOnParking == 0){
                            Platform.runLater(()->{
                                    listOnParking.clear();
                                    showTableOnParking(listOnParking);

                            });

                        }
                    }else{
                        //response is bad;
                        pageOnParking--;// page isn't loaded.
                        Platform.runLater(() -> Utils.showAlertMessage("Response error.", Converter.convertResponseToSting(response.errorBody())));
                    }
                    parkingLoading = false; //free flag that data is loading;
                    loadOnParkingCount = 0;
                }

                @Override
                public void onFailure(Call<GetCarsOnParkingResponseEnvelope> call, final Throwable t) {
                    //request if fail;
                    parkingLoading = false; //free flag that data is loading;
                    pageOnParking--; // page isn't loaded;
                    if(loadOnParkingCount++ < Main.COUNT_RETRY){
                       loadMoreOnParkingFromServer(param);
                    }else{
                        loadOnParkingCount=0;
                        Platform.runLater(() -> Utils.showAlertMessage("Fail request.", t.getMessage()));
                    }

                }
            });
        }
    }

//    private long lastTimeReleased;
    private void loadMoreReleasedCarsFromServer( String param){
        if((getLastVisibleItem(tblReleasedCars)+100 > pageReleased*200)&& !releasedLoading) {
//            long currentTime = System.currentTimeMillis();
//            if(currentTime - lastTimeReleased < 1000 ){
//                return;
//            }
//            lastTimeReleased = currentTime;
            //list ends soon;
            //Go to server for more data
            releasedLoading = true; //Set flags that data is loading;
            retrofitService.executeGetReleasedCars(Encode.getBasicAuthTemplate(UserManager.getInstanse().getmLogin(), UserManager.getInstanse().getmPassword()),
                    new GetReleasedCarsRequestEnvelope(++pageReleased, param)).enqueue(new Callback<GetReleasedCarsResponseEnvelope>() {
                @Override
                public void onResponse(Call<GetReleasedCarsResponseEnvelope> call, Response<GetReleasedCarsResponseEnvelope> response) {
                    //get response;
                    if (response.code() == 200) {
                        //response is good.
                        ObservableList<CarDataForLists> observableList = FXCollections.observableArrayList();
                        List<CarDataShort> carDataShortList = response.body().getData().getCarDataShortList();
                        if (carDataShortList != null) {
                            int index = 0;
                            for (CarDataShort carDataShort : carDataShortList) {
                                try {
                                    observableList.add(Converter.convertCarDataShortToCarDataForLists(carDataShort, index++));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                            Platform.runLater(()->{
                                if(pageReleased == 0){
                                        listReleased.clear();
                                }
                                listReleased.addAll(observableList);
                                showTableReleased(listReleased);
                            });
                        }else if(!param.equals("") || pageReleased == 0){
                                Platform.runLater(()->{
                                        listReleased.clear();
                                        showTableReleased(listReleased);
                                });

                        }

                    } else {
                        //response is bad
                        pageReleased--; // page isn't loaded;
                        Platform.runLater(() -> Utils.showAlertMessage("Response error code: " + response.code(), Converter.convertResponseToSting(response.errorBody())));
                    }
                    releasedLoading = false; //Free flags that data is loading;
                    loadReleasedCount = 0;
                }

                @Override
                public void onFailure(Call<GetReleasedCarsResponseEnvelope> call, final Throwable t) {
                    //response fail.
                    releasedLoading = false; //free flags that data is loading;
                    pageReleased--; //page isn't loaded;
                    if(loadReleasedCount++ < Main.COUNT_RETRY){
                        loadMoreReleasedCarsFromServer(param);
                    }else{
                        loadReleasedCount = 0;
                        Platform.runLater(() -> Utils.showAlertMessage("Fail request.", t.getMessage()));
                    }

                }
            });
        }
    }

//    private long lastTimeEvacuation;
    private void loadMoreOnEvacuationFromServer( String param){
        if((getLastVisibleItem(tblCarsOnEvacuation)+100 > pageOnEvacuation*200)&& !evacuationLoading){
            evacuationLoading = true;
            retrofitService.executeGetCarsOnEvacuation(Encode.getBasicAuthTemplate(UserManager.getInstanse().getmLogin(), UserManager.getInstanse().getmPassword()),
                    new GetCarsOnEvacuationRequestEnvelope(++pageOnEvacuation, param)).enqueue(new Callback<GetCarsOnEvacuationResponseEnvelope>() {
                @Override
                public void onResponse(Call<GetCarsOnEvacuationResponseEnvelope> call, final Response<GetCarsOnEvacuationResponseEnvelope> response) {

                    if(response.code()==200){
                        ObservableList<CarDataForLists> observableList = FXCollections.observableArrayList();
                        List<CarDataShort> carDataShortList = response.body().getData().getCarDataShortList();
                        if(carDataShortList != null){
                            int index = 0;
                            for(CarDataShort carDataShort: carDataShortList){
                                try {
                                    observableList.add(Converter.convertCarDataShortToCarDataForLists(carDataShort, index++));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                            }
                            Platform.runLater(()-> {
                                if(pageOnEvacuation == 0){
                                    listOnEvacuation.clear();
                                }
                                listOnEvacuation.addAll(observableList);
                                showTableOnEvacuation(listOnEvacuation);
                            });

                        }else if(!param.equals("") || pageOnEvacuation == 0){
                            Platform.runLater(()->{
                                listOnEvacuation.clear();
                                showTableOnEvacuation(listOnEvacuation);
                            });

                        }

                    }else {
                        pageOnEvacuation--;
                        Platform.runLater(() -> Utils.showAlertMessage("Response error code: " + response.code(), Converter.convertResponseToSting(response.errorBody())));
                    }
                    evacuationLoading = false;
                    loadOnEvacuationCount = 0;
                }

                @Override
                public void onFailure(Call<GetCarsOnEvacuationResponseEnvelope> call, final Throwable t) {
                    pageOnEvacuation--;
                    evacuationLoading = false;
                    if(loadOnEvacuationCount++ < Main.COUNT_RETRY){
                        loadMoreOnEvacuationFromServer(param);
                    }else{
                        loadOnEvacuationCount = 0;
                        Platform.runLater(() -> Utils.showAlertMessage("Fail request", t.getMessage()));
                    }
                }
            });
        }
    }

    private void loadMoreDebtActCarsFromServer( String param){
        if((getLastVisibleItem(tblDebtActCars)+100 > pageDebtAct*200)&& !debtActLoading){
            debtActLoading = true;
            retrofitService.executeGetDebtActCars(Encode.getBasicAuthTemplate(UserManager.getInstanse().getmLogin(), UserManager.getInstanse().getmPassword()),
                    new GetDebtActCarsRequestEnvelope(++pageDebtAct, param)).enqueue(new Callback<GetDebtActCarsResponseEnvelope>() {
                @Override
                public void onResponse(Call<GetDebtActCarsResponseEnvelope> call, final Response<GetDebtActCarsResponseEnvelope> response) {

                    if(response.code()==200){
                        ObservableList<CarDataForLists> observableList = FXCollections.observableArrayList();
                        List<CarDataShort> carDataShortList = response.body().getData().getCarDataShortList();
                        if(carDataShortList != null){
                            int index = 0;
                            for(CarDataShort carDataShort: carDataShortList){
                                try {
                                    observableList.add(Converter.convertCarDataShortToCarDataForLists(carDataShort, index++));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                            Platform.runLater(()->{
                                if(pageDebtAct == 0){
                                    listDebtAct.clear();
                                }
                                listDebtAct.addAll(observableList);
                                showTableDebtAct(listDebtAct);
                            });


                        }else if(!param.equals("") || pageDebtAct == 0){
                            Platform.runLater(()->{
                                listDebtAct.clear();
                                showTableDebtAct(listDebtAct);
                            });

                        }
                    }else {
                        pageDebtAct--;
                        Platform.runLater(() -> Utils.showAlertMessage("Response error code: " + response.code(), Converter.convertResponseToSting(response.errorBody())));
                    }
                    debtActLoading = false;
                    loadDebtActCount = 0;
                }

                @Override
                public void onFailure(Call<GetDebtActCarsResponseEnvelope> call, final Throwable t) {
                    pageDebtAct--;
                    debtActLoading = false;
                    if(loadDebtActCount++ < Main.COUNT_RETRY){
                        loadMoreDebtActCarsFromServer(param);
                    }else{
                        loadDebtActCount = 0;
                        Platform.runLater(() -> Utils.showAlertMessage("Fail request ", t.getMessage()));
                    }
                }
            });
        }
    }

    private void loadMoreOnPayingFromServer( String param){
        if((getLastVisibleItem(tblCarsOnPaying)+100 > pageOnPaying*200)&& !payingLoading){
            payingLoading = true;
            retrofitService.executeGetCarsOnPaying(Encode.getBasicAuthTemplate(UserManager.getInstanse().getmLogin(), UserManager.getInstanse().getmPassword()),
                    new GetCarsOnPayingRequestEnvelope(++pageOnPaying, param)).enqueue(new Callback<GetCarsOnPayingResponseEnvelope>() {
                @Override
                public void onResponse(Call<GetCarsOnPayingResponseEnvelope> call, final Response<GetCarsOnPayingResponseEnvelope> response) {

                    if(response.code() == 200){
                        ObservableList<CarDataForLists> observableList = FXCollections.observableArrayList();
                        List<CarDataShort> carDataShortList = response.body().getData().getCarDataShortList();
                        if(carDataShortList != null){
                            int index = 0;
                            for(CarDataShort carDataShort: carDataShortList){
                                try {
                                    observableList.add(Converter.convertCarDataShortToCarDataForLists(carDataShort, index++));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                            Platform.runLater(()->{
                                if(pageOnPaying == 0){
                                    listOnPaying.clear();
                                }
                                listOnPaying.addAll(observableList);
                                showTableOnPaying(listOnPaying);
                            });

                        }
                        else if(!param.equals("") || pageOnPaying == 0){
                            Platform.runLater(()-> {
                                listOnPaying.clear();
                                showTableOnPaying(listOnPaying);
                            });

                        }
                    }else{
                        pageOnPaying--;
                        Platform.runLater(() -> Utils.showAlertMessage("Response error code: " + response.code(), Converter.convertResponseToSting(response.errorBody())));
                    }
                    payingLoading = false;
                    loadOnPayingCount = 0;
                }

                @Override
                public void onFailure(Call<GetCarsOnPayingResponseEnvelope> call, final Throwable t) {
                    payingLoading = false;
                    pageOnPaying--;
                    if(loadOnPayingCount++ < Main.COUNT_RETRY){
                        loadMoreOnPayingFromServer(param);
                    }else{
                        loadOnPayingCount = 0;
                        Platform.runLater(() -> Utils.showAlertMessage("Fail request.", t.getMessage()));
                    }
                }
            });
        }
    }

    private void showCarDetails(int identifier){
        try{
            FormCarDetails frm = new FormCarDetails(identifier);
            frm.start(null);
        }catch (Throwable t){
            t.printStackTrace();
        }
    }

    private void showDetailsOnEvacuation(){

        String status = tblCarsOnEvacuation.getSelectionModel().getSelectedItem().getStatus();
        switch (status){
            case "На перемещении":
                showCarDetails(tblCarsOnEvacuation.getSelectionModel().getSelectedItem().getIdentifier());
                break;
            case "На эвакуации":
                    FormGetToParking frm = new FormGetToParking();
                    frm.setIdentifier(tblCarsOnEvacuation.getSelectionModel().getSelectedItem().getIdentifier());
                try {
                    frm.start(null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }

    }

    @Override
    public void onChangeData() {
        pageOnParking = -1;
        pageDebtAct = -1;
        pageOnEvacuation = - 1;
        pageReleased = -1;
        pageOnPaying = -1;
        refreshLists();
    }


}
