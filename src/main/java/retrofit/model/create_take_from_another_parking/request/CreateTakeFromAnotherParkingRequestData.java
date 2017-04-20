package retrofit.model.create_take_from_another_parking.request;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "tes:CreateTakeFromAnotherParking")
public class CreateTakeFromAnotherParkingRequestData {
    @Element(name = "Identifier")
    private int identifier;
    @Element(name = "Wrecker")
    private String wrecker;

    public CreateTakeFromAnotherParkingRequestData(int identifier, String wrecker){
        this.identifier = identifier;
        this.wrecker = wrecker;

    }
}
