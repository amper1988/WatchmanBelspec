package retrofit.model.create_back_to_parking.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;
import retrofit.model.ServerAnswer;

@Root(name = "Envelope")
public class CreateBackToParkingResponseEnvelope {
    @Element(name = "Header" , required = false)
    private String header;

    @Element(name = "return")
    @Path("Body/CreateBackToParkingResponse")
    private ServerAnswer serverAnswer;

    public ServerAnswer getServerAnswer(){
        return this.serverAnswer;
    }
}
