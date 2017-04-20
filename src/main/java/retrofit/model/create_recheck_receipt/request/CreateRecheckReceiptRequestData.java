package retrofit.model.create_recheck_receipt.request;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "tes:CreateRecheckReceipt")
public class CreateRecheckReceiptRequestData {
    @Element(name = "Identifier")
    private int identifier;

    public CreateRecheckReceiptRequestData(int identifier){
        this.identifier = identifier;
    }
}
