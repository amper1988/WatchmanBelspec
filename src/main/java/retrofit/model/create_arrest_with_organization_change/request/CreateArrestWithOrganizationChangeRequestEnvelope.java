package retrofit.model.create_arrest_with_organization_change.request;

import org.simpleframework.xml.*;

@Root(name = "soap12:Envelope")
@NamespaceList({
        @Namespace(prefix = "tes", reference = "www.uri.com"),
        @Namespace(prefix = "soap12", reference = "http://schemas.xmlsoap.org/soap/envelope/")
})
public class CreateArrestWithOrganizationChangeRequestEnvelope {
    @Element(name = "tes:CreateArrestWithOrganizationChange")
    @Path("soap12:Body")
    private CreateArrestWithOrganizationChangeRequestData data;

    public CreateArrestWithOrganizationChangeRequestEnvelope(int identifier, String whoArrested, String arrestReason, String organization){
        this.data = new CreateArrestWithOrganizationChangeRequestData(identifier, whoArrested, arrestReason, organization);
    }
}
