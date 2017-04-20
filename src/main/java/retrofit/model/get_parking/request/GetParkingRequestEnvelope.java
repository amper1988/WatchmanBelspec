package retrofit.model.get_parking.request;

import org.simpleframework.xml.*;

@Root(name = "soap12:Envelope")
@NamespaceList({
        @Namespace(prefix = "tes", reference = "uri.com"),
        @Namespace(prefix = "soap12", reference = "http://schemas.xmlsoap.org/soap/envelope/")
})
public class GetParkingRequestEnvelope {
    @Element(name = "tes:GetParkingList")
    @Path("soap12:Body")
    public GetParkingRequestData data;

    public GetParkingRequestEnvelope(String owner){
        this.data = new GetParkingRequestData(owner);
    }
}
