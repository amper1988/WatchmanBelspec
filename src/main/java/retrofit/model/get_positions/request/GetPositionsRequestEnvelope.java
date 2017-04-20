package retrofit.model.get_positions.request;

import org.simpleframework.xml.*;

@Root(name = "soap12:Envelope")
@NamespaceList({
        @Namespace(prefix = "tes", reference = "uri.com"),
        @Namespace(prefix = "soap12", reference = "http://schemas.xmlsoap.org/soap/envelope/")
})
public class GetPositionsRequestEnvelope {
    @Element(name = "tes:GetPositions")
    @Path("soap12:Body")
    private String body;

    public GetPositionsRequestEnvelope(){
        this.body = "";
    }
}
