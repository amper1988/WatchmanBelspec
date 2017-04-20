package retrofit.model.change_parking.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;
import retrofit.model.ServerAnswer;

@Root(name = "Envelope")
public class ChangeParkingResponseEnvelope {
    @Element(name = "Header", required = false)
    private String header;

    @Element(name = "return")
    @Path("Body/ChangeParkingResponse")
    private ServerAnswer serverAnswer;

    public ServerAnswer getServerAnswer(){
        return serverAnswer;
    }
}
