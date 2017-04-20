package retrofit.model.get_colors.request;

import org.simpleframework.xml.*;

@Root(name = "soap12:Envelope")
@NamespaceList({
        @Namespace(prefix = "tes", reference = "uri.com"),
        @Namespace(prefix = "soap12", reference = "http://schemas.xmlsoap.org/soap/envelope/")
})
public class GetColorsRequestEnvelope {
    @Element(name = "tes:GetColors")
    @Path("soap12:Body")
    private String body;

    public GetColorsRequestEnvelope(){
        this.body = "";
    }

}
