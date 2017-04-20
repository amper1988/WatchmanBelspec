package retrofit.model.create_release_without_recheck.request;

import org.simpleframework.xml.*;

@Root(name = "soap12:Envelope")
@NamespaceList({
        @Namespace(prefix = "tes", reference = "www.uri.com"),
        @Namespace(prefix = "soap12", reference = "http://schemas.xmlsoap.org/soap/envelope/")
})
public class CreateReleaseWithoutRecheckRequestEnvelope {
    @Element(name = "soap12:Header", required = false)
    private String header;
    @Element(name = "tes:CreateReleaseWithoutRecheck")
    @Path("soap12:Body")
    private CreateReleaseWithoutRecheckRequestData data;

    public CreateReleaseWithoutRecheckRequestEnvelope(int identifier, String bankReceiptNumber, String policeDepartmentReceiptNumber){
        this.data = new CreateReleaseWithoutRecheckRequestData(identifier, bankReceiptNumber, policeDepartmentReceiptNumber);
    }
}
