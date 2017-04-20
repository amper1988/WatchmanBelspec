package retrofit.model.create_policeman.request;

import org.simpleframework.xml.*;

@Root(name = "soap12:Envelope")
@NamespaceList({
        @Namespace(prefix = "tes", reference = "www.uri.com"),
        @Namespace(prefix = "soap12", reference = "http://schemas.xmlsoap.org/soap/envelope/")
})
public class CreatePolicemanRequestEnvelope {
    @Element(name = "tes:CreatePoliceman")
    @Path("soap12:Body")
    private CreatePolicemanRequestData data;

    public CreatePolicemanRequestEnvelope(String policeDepartment, String rank, String position, String name, String code){
        this.data = new CreatePolicemanRequestData(policeDepartment, rank, position, name, code);
    }
}
