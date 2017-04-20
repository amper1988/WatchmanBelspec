package retrofit.model.get_ranks.request;

import org.simpleframework.xml.*;

@Root(name = "soap12:Envelope")
@NamespaceList({
        @Namespace(prefix = "tes", reference = "uri.com"),
        @Namespace(prefix = "soap12", reference = "http://schemas.xmlsoap.org/soap/envelope/")
})
public class GetRanksRequestEnvelope {
    @Element(name = "tes:GetRanks")
    @Path("soap12:Body")
    private String body;

    public GetRanksRequestEnvelope(){
        this.body ="";
    }
}
