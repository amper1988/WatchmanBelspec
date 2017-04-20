package retrofit.model.get_receipt_information.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

@Root(name = "Envelope")
public class GetReceiptInformationResponseEnvelope {
    @Element(name = "Header", required = false)
    private String header;
    @Element(name = "return")
    @Path("Body/GetReceiptInformationResponse")
    private GetReceiptInformationResponseData data;

    public GetReceiptInformationResponseData getData(){
        return this.data;
    }
}
