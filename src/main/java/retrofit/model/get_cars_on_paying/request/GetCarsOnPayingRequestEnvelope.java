package retrofit.model.get_cars_on_paying.request;

import org.simpleframework.xml.*;

@Root(name = "soap12:Envelope")
@NamespaceList({
        @Namespace(prefix = "tes", reference = "uri.com"),
        @Namespace(prefix = "soap12", reference = "http://schemas.xmlsoap.org/soap/envelope/")
})
public class GetCarsOnPayingRequestEnvelope {
    @Element(name = "tes:GetCarsOnPaying")
    @Path("soap12:Body")
    private GetCarsOnPayingRequestData data;

    public GetCarsOnPayingRequestEnvelope(int page, String param){
        this.data = new GetCarsOnPayingRequestData(page, param);
    }
}
