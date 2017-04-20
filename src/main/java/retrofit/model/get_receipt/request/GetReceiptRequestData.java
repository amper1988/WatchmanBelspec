package retrofit.model.get_receipt.request;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "tes:GetReceipt")
public class GetReceiptRequestData {
    @Element(name = "Identifier")
    private  int identifier;

    public GetReceiptRequestData(int identifier){
        this.identifier = identifier;
    }


}
