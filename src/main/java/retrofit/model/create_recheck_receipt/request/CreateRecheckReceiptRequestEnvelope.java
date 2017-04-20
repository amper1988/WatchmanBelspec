package retrofit.model.create_recheck_receipt.request;

import org.simpleframework.xml.*;

@Root(name = "soap12:Envelope")
@NamespaceList({
        @Namespace(prefix = "tes", reference = "www.uri.com"),
        @Namespace(prefix = "soap12", reference = "http://schemas.xmlsoap.org/soap/envelope/")
})
public class CreateRecheckReceiptRequestEnvelope {
    @Element(name = "tes:CreateRecheckReceipt")
    @Path("soap12:Body")
    private CreateRecheckReceiptRequestData data;

    public CreateRecheckReceiptRequestEnvelope(int identifier){
        this.data = new CreateRecheckReceiptRequestData(identifier);
    }
}
