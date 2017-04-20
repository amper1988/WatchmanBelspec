package retrofit.model;

import org.simpleframework.xml.Element;

public class CarDataShort {
    @Element(name = "Identifier")
    private int identifier;
    @Element(name="Manufacture")
    private String manufacture;
    @Element(name="Model", required = false)
    private String model;
    @Element(name="CarId", required = false)
    private String carId;
    @Element(name = "DebtActNumber", required = false)
    private String debtActNumber;
    @Element(name = "Clause", required = false)
    private String clause;
    @Element(name = "ProtocolNumber", required = false)
    private String protocolNumber;
    @Element(name = "ParkingDate", required = false)
    private String parkingDate;
    @Element(name = "ReleaseDate", required = false)
    private String releaseDate;
    @Element(name = "PoliceDepartment", required = false)
    private String policeDepartment;
    @Element(name = "Status", required = false)
    private String status;
    @Element(name = "Block", required = false)
    private boolean block;

    public int getIdentifier() {
        return identifier;
    }

    public String getManufacture() {
        return manufacture;
    }

    public String getModel() {
        return model;
    }

    public String getCarId() {
        return carId;
    }

    public String getDebtActNumber() {
        return debtActNumber;
    }

    public String getClause() {
        return clause;
    }

    public String getProtocolNumber() {
        return protocolNumber;
    }

    public String getParkingDate() {
        if(parkingDate!=null){
            return parkingDate;
        }
        return "01.01.0001 00:00:00";
    }

    public String getReleaseDate() {
        if(releaseDate!=null){
            return releaseDate;
        }
        return "01.01.0001 00:00:00";
    }

    public String getPoliceDepartment() {
        return policeDepartment;
    }

    public String getStatus() {
        return status;
    }

    public boolean isBlock() {
        return block;
    }
}
