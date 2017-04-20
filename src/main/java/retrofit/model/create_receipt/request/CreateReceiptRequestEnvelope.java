package retrofit.model.create_receipt.request;

import org.simpleframework.xml.*;

@Root(name = "soap12:Envelope")
@NamespaceList({
        @Namespace(prefix = "tes", reference = "www.uri.com"),
        @Namespace(prefix = "soap12", reference = "http://schemas.xmlsoap.org/soap/envelope/")
})
public class CreateReceiptRequestEnvelope {
    @Element(name = "tes:CreateReceipt")
    @Path("soap12:Body")
    private CreateReceiptRequestData data;

    public CreateReceiptRequestEnvelope(int identifier, String techCertSeries, String techCertNumber, String ownerName, String ownerAddress,
                                        String driverLicenseSeries, String driverLicenseNumber, String driverName, String driverAddress, String driverContact,
                                        boolean debtActBool, String debtActNumber, String receiptDate){
        this.data = new CreateReceiptRequestData(identifier, techCertSeries,  techCertNumber,  ownerName,  ownerAddress,
                driverLicenseSeries,  driverLicenseNumber,  driverName, driverAddress,  driverContact,
           debtActBool,  debtActNumber,  receiptDate);
    }
}
