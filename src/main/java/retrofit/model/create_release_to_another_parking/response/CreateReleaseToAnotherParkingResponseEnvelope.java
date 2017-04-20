package retrofit.model.create_release_to_another_parking.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;
import retrofit.model.ServerAnswer;

@Root(name = "Envelope")
public class CreateReleaseToAnotherParkingResponseEnvelope {
    @Element(name = "Header", required = false)
    private String header;

    @Element( name = "return")
    @Path("Body/CreateReleaseToAnotherParkingResponse")
    private ServerAnswer serverAnswer;

    public ServerAnswer getServerAnswer(){
        return this.serverAnswer;
    }

}
