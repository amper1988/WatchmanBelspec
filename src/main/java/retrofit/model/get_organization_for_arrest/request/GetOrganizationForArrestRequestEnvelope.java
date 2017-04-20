package retrofit.model.get_organization_for_arrest.request;

import org.simpleframework.xml.*;

@Root(name = "soap12:Envelope")
@NamespaceList({
        @Namespace(prefix = "tes", reference = "uri.com"),
        @Namespace(prefix = "soap12", reference = "http://schemas.xmlsoap.org/soap/envelope/")
})
public class GetOrganizationForArrestRequestEnvelope {
    @Element(name = "tes:GetOrganizationForArrest")
    @Path("soap12:Body")
    private String body;

    public GetOrganizationForArrestRequestEnvelope(){
        this.body = "";
    }
}
