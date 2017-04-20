package retrofit.model.get_released_cars.request;

import org.simpleframework.xml.*;

@Root(name = "soap12:Envelope")
@NamespaceList({
        @Namespace(prefix = "tes", reference = "uri.com"),
        @Namespace(prefix = "soap12", reference = "http://schemas.xmlsoap.org/soap/envelope/")
})
public class GetReleasedCarsRequestEnvelope {
    @Element(name = "tes:GetReleasedCars")
    @Path("soap12:Body")
    private GetReleasedCarsRequestData data;

    public GetReleasedCarsRequestEnvelope(int page, String param){
        this.data = new GetReleasedCarsRequestData(page, param);
    }

}
