package retrofit.model.get_voucher.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;
import retrofit.model.ServerAnswer;

@Root(name = "Envelope")
public class GetVoucherResponseEnvelope {
    @Element(name = "Header", required = false)
    private String header;

    @Element(name = "return")
    @Path("Body/GetVoucherResponse")
    private ServerAnswer serverAnswer;

    public ServerAnswer getServerAnswer(){
        return this.serverAnswer;
    }
}
