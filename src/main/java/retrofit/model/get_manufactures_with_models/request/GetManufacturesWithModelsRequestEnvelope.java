package retrofit.model.get_manufactures_with_models.request;

import org.simpleframework.xml.*;

@Root(name = "soap12:Envelope")
@NamespaceList({
        @Namespace(prefix = "tes", reference = "uri.com"),
        @Namespace(prefix = "soap12", reference = "http://schemas.xmlsoap.org/soap/envelope/")
})
public class GetManufacturesWithModelsRequestEnvelope {
    @Element(name = "tes:GetManufacturesWithModels")
    @Path("soap12:Body")
    private String body;

    public GetManufacturesWithModelsRequestEnvelope(){
        this.body = "";
    }
}
