package retrofit.model.get_parkings.request;

import org.simpleframework.xml.*;

@Root(name = "soap12:Envelope")
@NamespaceList({
        @Namespace(prefix = "tes", reference = "uri.com"),
        @Namespace(prefix = "soap12", reference = "http://schemas.xmlsoap.org/soap/envelope/")
})
public class GetParkingsRequestEnvelope {
    @Element(name = "tes:GetParkings")
    @Path("soap12:Body")
    private String body;

    public GetParkingsRequestEnvelope(){
        this.body = "";
    }
}
