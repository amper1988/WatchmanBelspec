package retrofit.model.create_release.request;

import org.simpleframework.xml.*;

@Root(name = "soap12:Envelope")
@NamespaceList({
        @Namespace(prefix = "tes", reference = "www.uri.com"),
        @Namespace(prefix = "soap12", reference = "http://schemas.xmlsoap.org/soap/envelope/")
})
public class CreateReleaseRequestEnvelope {
    @Element(name = "soap12:Header", required = false)
    private String header;
    @Element(name = "tes:CreateRelease")
    @Path("soap12:Body")
    private CreateReleaseRequestData data;

    public CreateReleaseRequestEnvelope(int identifier, String bankReceiptNumber, String policeDepartmentReceiptNumber){
        this.data = new CreateReleaseRequestData(identifier, bankReceiptNumber, policeDepartmentReceiptNumber);
    }
}
