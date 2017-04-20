package retrofit.model.get_car_to_parking.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;
import retrofit.model.ServerAnswer;

@Root(name = "Envelope")
public class GetCarToParkingResponseEnvelope {
    @Element(name = "Header", required = false)
    private String header;

    @Element(name = "return")
    @Path("Body/GetCarToParkingResponse")
    private ServerAnswer answer;

    public ServerAnswer getAnswer(){
        return this.answer;
    }
}
