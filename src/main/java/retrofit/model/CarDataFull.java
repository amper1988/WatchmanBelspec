package retrofit.model;

import org.simpleframework.xml.Element;

public class CarDataFull {
    @Element(name = "Manufacture", required = false)
    private String manufacture;
    @Element(name = "Model", required = false)
    private String model;
    @Element(name = "Color", required = false)
    private String color;
    @Element(name = "CarId", required = false)
    private String carId;
    @Element(name = "Status", required = false)
    private String status;
    @Element(name = "ReceiptPoliceDepartment", required = false)
    private String receiptPoliceDepartment;
    @Element(name = "ReceiptBank", required = false)
    private String receiptBank;
    @Element(name = "PoliceDepartment", required = false)
    private String policeDepartment;
    @Element(name = "Policeman", required = false)
    private String policeman;
    @Element(name = "ProtocolNumber", required = false)
    private String protocolNumber;
    @Element(name = "Clause", required = false)
    private String clause;
    @Element(name = "EvacuatedBool", required = false)
    private boolean evacuatedBool;
    @Element(name = "EvacuationOrganization", required = false)
    private String evacuationOrganization;
    @Element(name = "Wrecker", required = false)
    private String wrecker;
    @Element(name = "EvacuationCosts", required = false)
    private String evacuationCosts;
    @Element(name = "EvacuationDate", required = false)
    private String evacuationDate;
    @Element(name = "Image1", required = false)
    private String image1;
    @Element(name = "Image2", required = false)
    private String image2;
    @Element(name = "Image3", required = false)
    private String image3;
    @Element(name = "Image4", required = false)
    private String image4;
    @Element(name = "EvacuationAddress", required = false)
    private String evacuationAddress;
    @Element(name = "StoringOrganization", required = false)
    private String storingOrganization;
    @Element(name = "WhoTook", required = false)
    private String whoTook;
    @Element(name = "ParkingAddress", required = false)
    private String parkingAddress;
    @Element(name = "ParkingDate", required = false)
    private String parkingDate;
    @Element(name = "SeriesTechCert", required = false)
    private String seriesTechCert;
    @Element(name = "NumberTechCert", required = false)
    private String numberTechCert;
    @Element(name = "OwnerName", required = false)
    private String ownerName;
    @Element(name = "OwnerAddress", required = false)
    private String ownerAddress;
    @Element(name = "SeriesDriverLicense", required = false)
    private String seriesDriverLicense;
    @Element(name = "NumberDriverLicense", required = false)
    private String numberDriverLicense;
    @Element(name = "DriverName", required = false)
    private String driverName;
    @Element(name = "DriverAddress", required = false)
    private String driverAddress;
    @Element(name = "DriverContact", required = false)
    private String driverContact;
    @Element(name = "WhoReleased", required = false)
    private String whoReleased;
    @Element(name = "ParkingCosts", required = false)
    private String parkingCosts;
    @Element(name = "ReleaseDate", required = false)
    private String releaseDate;
    @Element(name = "DebtActBool", required = false)
    private boolean debtActBool;
    @Element(name = "DebtActNumber", required = false)
    private  String debtActNumber;
    @Element(name = "Maps", required = false)
    private String maps;
    @Element(name = "Block", required = false)
    private boolean block;

    public String getManufacture() {
        return manufacture;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public String getCarId() {
        return carId;
    }

    public String getStatus() {
        return status;
    }

    public String getReceiptPoliceDepartment() {
        return receiptPoliceDepartment;
    }

    public String getReceiptBank() {
        return receiptBank;
    }

    public String getPoliceDepartment() {
        return policeDepartment;
    }

    public String getPoliceman() {
        return policeman;
    }

    public String getProtocolNumber() {
        return protocolNumber;
    }

    public String getClause() {
        return clause;
    }

    public boolean isEvacuatedBool() {
        return evacuatedBool;
    }

    public String getEvacuationOrganization() {
        return evacuationOrganization;
    }

    public String getWrecker() {
        return wrecker;
    }

    public String getEvacuationCosts() {
        return evacuationCosts;
    }

    public String getEvacuationDate() {
        return evacuationDate;
    }

    public String getImage1() {
        return image1;
    }

    public String getImage2() {
        return image2;
    }

    public String getImage3() {
        return image3;
    }

    public String getImage4() {
        return image4;
    }

    public String getEvacuationAddress() {
        return evacuationAddress;
    }

    public String getStoringOrganization() {
        return storingOrganization;
    }

    public String getWhoTook() {
        return whoTook;
    }

    public String getParkingAddress() {
        return parkingAddress;
    }

    public String getParkingDate() {
        return parkingDate;
    }

    public String getSeriesTechCert() {
        return seriesTechCert;
    }

    public String getNumberTechCert() {
        return numberTechCert;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getOwnerAddress() {
        return ownerAddress;
    }

    public String getSeriesDriverLicense() {
        return seriesDriverLicense;
    }

    public String getNumberDriverLicense() {
        return numberDriverLicense;
    }

    public String getDriverName() {
        return driverName;
    }

    public String getDriverAddress() {
        return driverAddress;
    }

    public String getDriverContact() {
        return driverContact;
    }

    public String getWhoReleased() {
        return whoReleased;
    }

    public String getParkingCosts() {
        return parkingCosts;
    }

    public String getReleasedDate() {
        return releaseDate;
    }

    public boolean isDebtActBool() {
        return debtActBool;
    }

    public String getDebtActNumber() {
        return debtActNumber;
    }

    public String getMaps() {
        return maps;
    }

    public boolean isBlock() {
        return block;
    }
}
