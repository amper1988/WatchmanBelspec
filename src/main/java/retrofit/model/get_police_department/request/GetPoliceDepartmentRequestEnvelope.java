package retrofit.model.get_police_department.request;

import org.simpleframework.xml.*;

@Root(name = "soap12:Envelope")
@NamespaceList({
        @Namespace(prefix = "tes", reference = "uri.com"),
        @Namespace(prefix = "soap12", reference = "http://schemas.xmlsoap.org/soap/envelope/")
})
public class GetPoliceDepartmentRequestEnvelope {
    @Element(name = "tes:GetPoliceDepartment")
    @Path("soap12:Body")
    private String body;

    public GetPoliceDepartmentRequestEnvelope(){
        this.body ="";
    }
}
