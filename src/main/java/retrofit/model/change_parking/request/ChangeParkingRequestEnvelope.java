package retrofit.model.change_parking.request;

import org.simpleframework.xml.*;

@Root(name = "soap12:Envelope")
@NamespaceList({
        @Namespace(prefix = "tes", reference = "uri.com"),
        @Namespace(prefix = "soap12", reference = "http://schemas.xmlsoap.org/soap/envelope/")
})
public class ChangeParkingRequestEnvelope {
    @Element(name = "tes:ChangeParking")
    @Path("soap12:Body")
    private ChangeParkingRequestData data;

    public ChangeParkingRequestEnvelope(String parking) {
        this.data = new ChangeParkingRequestData(parking);
    }
}
