package retrofit.model.get_debt_act_document.request;

import org.simpleframework.xml.*;

@Root(name = "soap12:Envelope")
@NamespaceList({
        @Namespace(prefix = "tes", reference = "uri.com"),
        @Namespace(prefix = "soap12", reference = "http://schemas.xmlsoap.org/soap/envelope/")
})
public class GetDebtActDocumentRequestEnvelope {
    @Element(name = "soap12:Header" , required = false)
    private String header;
    @Element(name = "tes:GetDebtActDocument")
    @Path("soap12:Body")
    private GetDebtActDocumentRequestData data;

    public GetDebtActDocumentRequestEnvelope(int identifier){
        this.data = new GetDebtActDocumentRequestData(identifier);
    }
}
