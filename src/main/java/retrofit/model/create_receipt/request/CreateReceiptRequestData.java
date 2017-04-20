package retrofit.model.create_receipt.request;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "tes:CreateReceipt")
public class CreateReceiptRequestData {
    @Element(name = "Identifier")
    private int identifier;
    @Element(name =  "TechCertSeries")
    private String techCertSeries;
    @Element(name = "TechCertNumber")
    private String techCertNumber;
    @Element(name = "OwnerName")
    private String ownerName;
    @Element(name = "OwnerAddress")
    private String ownerAddress;
    @Element(name = "DriverLicenseSeries")
    private String driverLicenseSeries;
    @Element(name = "DriverLicenseNumber")
    private String driverLicenseNumber;
    @Element(name = "DriverName")
    private String driverName;
    @Element(name = "DriverAddress")
    private String driverAddress;
    @Element(name = "DriverContact" , required = false)
    private String driverContact;
    @Element(name = "DebtAct")
    private boolean debtActBool;
    @Element(name = "DebtActNumber")
    private String debtActNumber;
    @Element(name = "ReceiptDate")
    private String receiptDate;

    public CreateReceiptRequestData(int identifier, String techCertSeries, String techCertNumber, String ownerName, String ownerAddress,
                                    String driverLicenseSeries, String driverLicenseNumber, String driverName, String driverAddress, String driverContact,
                                    boolean debtActBool, String debtActNumber, String receiptDate){
        this.identifier = identifier;
        this.techCertSeries = techCertSeries;
        this.techCertNumber = techCertNumber;
        this.ownerName = ownerName;
        this.ownerAddress = ownerAddress;
        this.driverLicenseSeries = driverLicenseSeries;
        this.driverLicenseNumber = driverLicenseNumber;
        this.driverName = driverName;
        this.driverAddress = driverAddress;
        this.driverContact = driverContact;
        this.debtActBool = debtActBool;
        this.debtActNumber = debtActNumber;
        this.receiptDate = receiptDate;
    }
}
