package retrofit.model.get_protocol.request;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "tes:GetProtocol")
public class GetProtocolRequestData {
    @Element(name = "Identifier")
    private int identifier;

    public GetProtocolRequestData(int identifier){
        this.identifier = identifier;
    }
}
