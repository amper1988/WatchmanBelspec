package retrofit.model;


import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "return")
public class OwnerDriverData {
    @Element(name = "OwnerName", required = false)
    private String ownerName;
    @Element(name = "OwnerAddress", required = false)
    private String ownerAddress;
    @Element(name = "TechCertSeries", required = false)
    private String techCertSeries;
    @Element(name = "TechCertNumber", required = false)
    private String techCertNumber;
    @Element(name = "DriverLicenseSeries", required = false)
    private String driverLicenseSeries;
    @Element(name = "DriverLicenseNumber", required = false)
    private String driverLicenseNumber;
    @Element(name = "DriverName", required = false)
    private String driverName;
    @Element(name = "DriverAddress", required = false)
    private String driverAddress;
    @Element(name = "DriverContact", required = false)
    private String driverContact;

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerAddress() {
        return ownerAddress;
    }

    public void setOwnerAddress(String ownerAddress) {
        this.ownerAddress = ownerAddress;
    }

    public String getTechCertSeries() {
        return techCertSeries;
    }

    public void setTechCertSeries(String techCertSeries) {
        this.techCertSeries = techCertSeries;
    }

    public String getTechCertNumber() {
        return techCertNumber;
    }

    public void setTechCertNumber(String techCertNumber) {
        this.techCertNumber = techCertNumber;
    }

    public String getDriverLicenseSeries() {
        return driverLicenseSeries;
    }

    public void setDriverLicenseSeries(String driverLicenseSeries) {
        this.driverLicenseSeries = driverLicenseSeries;
    }

    public String getDriverLicenseNumber() {
        return driverLicenseNumber;
    }

    public void setDriverLicenseNumber(String driverLicenseNumber) {
        this.driverLicenseNumber = driverLicenseNumber;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverAddress() {
        return driverAddress;
    }

    public void setDriverAddress(String driverAddress) {
        this.driverAddress = driverAddress;
    }

    public String getDriverContact() {
        return driverContact;
    }

    public void setDriverContact(String driverContact) {
        this.driverContact = driverContact;
    }
}
