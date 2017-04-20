package retrofit.model.get_clauses.request;

import org.simpleframework.xml.*;

@Root(name = "soap12:Envelope")
@NamespaceList({
        @Namespace(prefix = "tes", reference = "uri.com"),
        @Namespace(prefix = "soap12", reference = "http://schemas.xmlsoap.org/soap/envelope/")
})
public class GetClausesRequestEnvelope {
    @Element(name = "tes:GetClauses")
    @Path("soap12:Body")
    private String body;

    public GetClausesRequestEnvelope(){
        this.body = "";
    }

}
