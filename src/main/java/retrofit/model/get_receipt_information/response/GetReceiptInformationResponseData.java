package retrofit.model.get_receipt_information.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "return")
public class GetReceiptInformationResponseData {
    @Element(name = "ReceiptDate", required = false)
    private String receiptDate;
    @Element(name = "CommonCosts", required = false)
    private String commonCosts;
    @Element(name = "ParkingCosts", required = false)
    private String parkingCosts;

    public String getReceiptDate() {
        return receiptDate;
    }

    public String getCommonCosts() {
        return commonCosts;
    }

    public String getParkingCosts() {
        return parkingCosts;
    }
}
