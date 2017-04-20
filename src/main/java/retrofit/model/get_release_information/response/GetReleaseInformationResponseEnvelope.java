package retrofit.model.get_release_information.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;
import retrofit.model.ServerAnswer;

@Root(name = "Envelope")
public class GetReleaseInformationResponseEnvelope {
    @Element(name = "Header", required =  false)
    private String header;

    @Element(name = "return")
    @Path("Body/GetReleaseInformationResponse")
    private ServerAnswer serverAnswer;

    public ServerAnswer getServerAnswer(){
        return this.serverAnswer;
    }
}
