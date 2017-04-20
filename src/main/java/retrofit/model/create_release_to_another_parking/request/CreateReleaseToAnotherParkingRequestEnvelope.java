package retrofit.model.create_release_to_another_parking.request;

import org.simpleframework.xml.*;

@Root(name = "soap12:Envelope")
@NamespaceList({
        @Namespace(prefix = "tes", reference = "www.uri.com"),
        @Namespace(prefix = "soap12", reference = "http://schemas.xmlsoap.org/soap/envelope/")
})
public class CreateReleaseToAnotherParkingRequestEnvelope {
    @Element(name = "tes:CreateReleaseToAnotherParking")
    @Path("soap12:Body")
    private CreateReleaseToAnotherParkingRequestData data;

    public  CreateReleaseToAnotherParkingRequestEnvelope(int identifier, String wrecker, String evacuationReason, String targetParking){
        this.data = new CreateReleaseToAnotherParkingRequestData(identifier, wrecker, evacuationReason, targetParking);

    }
}
