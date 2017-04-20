package retrofit.model.create_back_to_parking.request;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "tes:CreateBackToParking")
public class CreateBackToParkingRequestData {
    @Element(name = "Identifier")
    private int identifier;

    public CreateBackToParkingRequestData(int identifier){
        this.identifier = identifier;
    }
}
