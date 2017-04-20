package retrofit.model.get_receipt.request;

import org.simpleframework.xml.*;

@Root(name = "soap12:Envelope")
@NamespaceList({
        @Namespace(prefix = "tes", reference = "uri.com"),
        @Namespace(prefix = "soap12", reference = "http://schemas.xmlsoap.org/soap/envelope/")
})
public class GetReceiptRequestEnvelope {
    @Element(name = "tes:GetReceipt")
    @Path("soap12:Body")
    private GetReceiptRequestData data;

    public GetReceiptRequestEnvelope(int identifier){

        this.data = new GetReceiptRequestData(identifier);

    }
}
