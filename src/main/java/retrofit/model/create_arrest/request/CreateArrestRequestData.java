package retrofit.model.create_arrest.request;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "tes:CreateArrest")
public class CreateArrestRequestData {
    @Element(name = "Identifier")
    private int identifier;
    @Element(name = "WhoArrested")
    private String whoArrested;
    @Element(name = "ArrestReason")
    private String arrestReason;

    public CreateArrestRequestData(int identifier, String whoArrested, String arrestReason){
        this.identifier = identifier;
        this.whoArrested = whoArrested;
        this.arrestReason = arrestReason;
    }
}
