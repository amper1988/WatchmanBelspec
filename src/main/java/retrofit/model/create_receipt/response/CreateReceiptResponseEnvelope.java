package retrofit.model.create_receipt.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;
import retrofit.model.ServerAnswer;

@Root(name = "Envelope")
public class CreateReceiptResponseEnvelope {
    @Element(name = "Header", required = false)
    private String header;
    @Element(name = "return")
    @Path("Body/CreateReceiptResponse")
    private ServerAnswer serverAnswer;

    public ServerAnswer getServerAnswer(){
        return this.serverAnswer;
    }
}
