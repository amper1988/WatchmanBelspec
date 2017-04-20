package model;

import javafx.beans.property.*;

public class CarDataForLists {
    private IntegerProperty identifier;
    private StringProperty manufacture;
    private StringProperty model;
    private StringProperty carId;
    private StringProperty parkingDate;
    private StringProperty releaseDate;
    private StringProperty debtActNumber;
    private StringProperty protocolNumber;
    private StringProperty clause;
    private StringProperty policeDepartment;
    private StringProperty status;
    private BooleanProperty block;
    private int indexInTable = -1;

    public CarDataForLists(){
        identifier = new SimpleIntegerProperty();
        manufacture = new SimpleStringProperty();
        model = new SimpleStringProperty();
        carId = new SimpleStringProperty();
        parkingDate = new SimpleStringProperty();
        releaseDate = new SimpleStringProperty();
        debtActNumber = new SimpleStringProperty();
        protocolNumber = new SimpleStringProperty();
        clause = new SimpleStringProperty();
        policeDepartment = new SimpleStringProperty();
        status = new SimpleStringProperty();
        block = new SimpleBooleanProperty();
    }
    public int getIdentifier() {
        return identifier.get();
    }

    public IntegerProperty identifierProperty() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier.set(identifier);
    }

    public String getManufacture() {
        return manufacture.get();
    }

    public StringProperty manufactureProperty() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture.set(manufacture);
    }

    public String getModel() {
        return model.get();
    }

    public StringProperty modelProperty() {
        return model;
    }

    public void setModel(String model) {
        this.model.set(model);
    }

    public String getCarId() {
        return carId.get();
    }

    public StringProperty carIdProperty() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId.set(carId);
    }

    public String getParkingDate() {
        return parkingDate.get();
    }

    public StringProperty parkingDateProperty() {
        return parkingDate;
    }

    public void setParkingDate(String parkingDate) {
        this.parkingDate.set(parkingDate);
    }

    public String getReleaseDate() {
        return releaseDate.get();
    }

    public StringProperty releaseDateProperty() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate.set(releaseDate);
    }

    public String getDebtActNumber() {
        return debtActNumber.get();
    }

    public StringProperty debtActNumberProperty() {
        return debtActNumber;
    }

    public void setDebtActNumber(String debtActNumber) {
        this.debtActNumber.set(debtActNumber);
    }

    public String getProtocolNumber() {
        return protocolNumber.get();
    }

    public StringProperty protocolNumberProperty() {
        return protocolNumber;
    }

    public void setProtocolNumber(String protocolNumber) {
        this.protocolNumber.set(protocolNumber);
    }

    public String getClause() {
        return clause.get();
    }

    public StringProperty clauseProperty() {
        return clause;
    }

    public void setClause(String clause) {
        this.clause.set(clause);
    }

    public String getPoliceDepartment() {
        return policeDepartment.get();
    }

    public StringProperty policeDepartmentProperty() {
        return policeDepartment;
    }

    public void setPoliceDepartment(String policeDepartment) {
        this.policeDepartment.set(policeDepartment);
    }

    public String getStatus() {
        return status.get();
    }

    public StringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public boolean isBlock() {
        return block.get();
    }

    public BooleanProperty blockProperty() {
        return block;
    }

    public void setBlock(boolean block) {
        this.block.set(block);
    }

    public int getIndexInTable() {
        return indexInTable;
    }

    public void setIndexInTable(int indexInTable) {
        this.indexInTable = indexInTable;
    }
}
