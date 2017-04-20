package retrofit.model.get_owner_driver_data.request;

import org.simpleframework.xml.*;

@Root(name = "soap12:Envelope")
@NamespaceList({
        @Namespace(prefix = "tes", reference = "uri.com"),
        @Namespace(prefix = "soap12", reference = "http://schemas.xmlsoap.org/soap/envelope/")
})
public class GetOwnerDriverDataRequestEnvelope {
    @Element(name = "tes:GetOwnerDriverData")
    @Path("soap12:Body")
    private GetOwnerDriverDataRequestData data;

    public GetOwnerDriverDataRequestEnvelope(String carId){
        this.data = new GetOwnerDriverDataRequestData(carId);
    }
}
