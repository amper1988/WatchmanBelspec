package retrofit.model.get_release_information.request;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "tes:GetReleaseInformation")
public class GetReleaseInformationRequestData {
    @Element(name = "Identifier")
    private int identifier;

    public GetReleaseInformationRequestData(int identifier){
        this.identifier = identifier;
    }
}
