package retrofit.model.get_protocol.request;

import org.simpleframework.xml.*;

@Root(name = "soap12:Envelope")
@NamespaceList({
        @Namespace(prefix = "tes", reference = "uri.com"),
        @Namespace(prefix = "soap12", reference = "http://schemas.xmlsoap.org/soap/envelope/")
})
public class GetProtocolRequestEnvelope {
    @Element(name = "tes:GetProtocol")
    @Path("soap12:Body")
    private GetProtocolRequestData data;

    public GetProtocolRequestEnvelope(int identifier){
        this.data = new GetProtocolRequestData(identifier);
    }
}
