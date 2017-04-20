package retrofit.model.create_release_without_recheck.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;
import retrofit.model.ServerAnswer;

@Root(name = "Envelope")
public class CreateReleaseWithoutRecheckResponseEnvelope {
    @Element(name = "Header", required = false)
    private String header;
    @Element(name = "return")
    @Path("Body/CreateReleaseWithoutRecheckResponse")
    private ServerAnswer serverAnswer;

    public ServerAnswer getServerAnswer(){
        return this.serverAnswer;
    }
}
