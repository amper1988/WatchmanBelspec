package retrofit.model.get_organizations_with_employers.request;

import org.simpleframework.xml.*;

@Root(name = "soap12:Envelope")
@NamespaceList({
        @Namespace(prefix = "tes", reference = "uri.com"),
        @Namespace(prefix = "soap12", reference = "http://schemas.xmlsoap.org/soap/envelope/")
})
public class GetOrganizationsWithEmployersRequestEnvelope {
    @Element(name = "tes:GetOrganizationsWithEmployers")
    @Path("soap12:Body")
    private String body;

    public GetOrganizationsWithEmployersRequestEnvelope(){
        this.body = "";
    }
}
