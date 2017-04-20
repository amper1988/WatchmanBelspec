package retrofit.model.create_back_to_parking.request;

import org.simpleframework.xml.*;

@Root(name = "soap12:Envelope")
@NamespaceList({
        @Namespace(prefix = "tes", reference = "www.uri.com"),
        @Namespace(prefix = "soap12", reference = "http://schemas.xmlsoap.org/soap/envelope/")
})
public class CreateBackToParkingRequestEnvelope {
    @Element(name = "tes:CreateBackToParking")
    @Path("soap12:Body")
    private CreateBackToParkingRequestData data;

    public CreateBackToParkingRequestEnvelope(int identifier){
        this.data = new CreateBackToParkingRequestData(identifier);
    }
}
