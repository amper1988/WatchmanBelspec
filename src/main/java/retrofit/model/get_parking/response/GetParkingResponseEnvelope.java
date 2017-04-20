package retrofit.model.get_parking.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;
import retrofit.model.ParkingItem;

@Root(name = "Envelope")
public class GetParkingResponseEnvelope {
    @Element(name = "Header", required = false)
    private String header;

    @Element(name = "return")
    @Path("Body/GetParkingListResponse")
    private ParkingItem body;

    public ParkingItem getBody(){
        return body;
    }
}
