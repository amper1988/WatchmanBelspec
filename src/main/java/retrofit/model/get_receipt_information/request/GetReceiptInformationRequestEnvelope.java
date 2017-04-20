package retrofit.model.get_receipt_information.request;

import org.simpleframework.xml.*;

@Root(name = "soap12:Envelope")
@NamespaceList({
        @Namespace(prefix = "tes", reference = "uri.com"),
        @Namespace(prefix = "soap12", reference = "http://schemas.xmlsoap.org/soap/envelope/")
})
public class GetReceiptInformationRequestEnvelope {
    @Element(name = "tes:GetReceiptInformation")
    @Path("soap12:Body")
    private GetReceiptInformationRequestData data;

    public GetReceiptInformationRequestEnvelope (int identifier){
       data = new GetReceiptInformationRequestData(identifier);
    }
}

