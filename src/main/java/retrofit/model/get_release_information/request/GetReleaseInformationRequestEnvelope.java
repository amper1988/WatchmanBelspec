package retrofit.model.get_release_information.request;

import org.simpleframework.xml.*;

@Root(name = "soap12:Envelope")
@NamespaceList({
        @Namespace(prefix = "tes", reference = "uri.com"),
        @Namespace(prefix = "soap12", reference = "http://schemas.xmlsoap.org/soap/envelope/")
})
public class GetReleaseInformationRequestEnvelope {
    @Element(name = "tes:GetReleaseInformation")
    @Path("soap12:Body")
    private GetReleaseInformationRequestData data;

    public GetReleaseInformationRequestEnvelope(int identifier){
        this.data = new GetReleaseInformationRequestData(identifier);
    }
}
