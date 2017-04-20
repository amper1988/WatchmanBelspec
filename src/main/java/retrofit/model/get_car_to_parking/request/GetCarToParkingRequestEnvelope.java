package retrofit.model.get_car_to_parking.request;

import org.simpleframework.xml.*;

@Root(name = "soap12:Envelope")
@NamespaceList({
        @Namespace(prefix = "tes", reference = "www.uri.com"),
        @Namespace(prefix = "soap12", reference = "http://schemas.xmlsoap.org/soap/envelope/")
})
public class GetCarToParkingRequestEnvelope {
    @Element(name = "tes:GetCarToParking")
    @Path("soap12:Body")
    private GetCarToParkingRequestData data;

    public GetCarToParkingRequestEnvelope(int identifier, String manufacture, String model, String color, String carId,
                                          String policeDepartment, String policeman, String clause, boolean evacuatedBool,
                                          String evacuationOrganization, String wrecker, String evacuationAddress,
                                          String photo1, String photo2, String photo3, String photo4){

        data = new GetCarToParkingRequestData(identifier, manufacture, model, color, carId,
                policeDepartment, policeman, clause, evacuatedBool, evacuationOrganization,
                wrecker, evacuationAddress, photo1, photo2, photo3, photo4);
    }
}
