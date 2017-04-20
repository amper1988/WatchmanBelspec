package retrofit.model.get_receipt_information.request;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "tes:GetReceiptInformation")
public class GetReceiptInformationRequestData {
    @Element(name = "Identifier")
    private int identifier;

    public GetReceiptInformationRequestData(int identifier){
        this.identifier = identifier;
    }

    public void setIdentifier(int identifier){
        this.identifier = identifier;
    }
}
