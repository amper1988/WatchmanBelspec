package retrofit.model.create_release_to_another_parking.request;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "tes:CreateReleaseToAnotherParking")
public class CreateReleaseToAnotherParkingRequestData {
    @Element(name = "tes:Identifier")
    private int identifier;
    @Element(name = "tes:Wrecker")
    private String wrecker;
    @Element(name = "tes:ReleaseReason")
    private String evacuationReason;
    @Element(name = "tes:TargetParking")
    private String targetParking;

    public CreateReleaseToAnotherParkingRequestData(int identifier, String wrecker, String evacuationReason, String targetParking){
        this.identifier = identifier;
        this.wrecker = wrecker;
        this.evacuationReason = evacuationReason;
        this.targetParking = targetParking;
    }
}
