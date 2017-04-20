package retrofit.model.create_take_from_another_parking.request;

import org.simpleframework.xml.*;

@Root(name = "soap12:Envelope")
@NamespaceList({
        @Namespace(prefix = "tes", reference = "www.uri.com"),
        @Namespace(prefix = "soap12", reference = "http://schemas.xmlsoap.org/soap/envelope/")
})
public class CreateTakeFromAnotherParkingRequestEnvelope {
    @Element(name = "tes:CreateTakeFromAnotherParking")
    @Path("soap12:Body")
    private  CreateTakeFromAnotherParkingRequestData data;

    public CreateTakeFromAnotherParkingRequestEnvelope(int identifier, String wrecker){
        this.data = new CreateTakeFromAnotherParkingRequestData(identifier, wrecker);
    }
}
