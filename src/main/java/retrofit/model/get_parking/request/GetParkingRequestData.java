package retrofit.model.get_parking.request;


import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "tes:GetParkingList")
public class GetParkingRequestData {
    @Element(name = "tes:Owner")
    private String owner;

    public GetParkingRequestData(String owner){
        this.owner = owner;
    }

    public GetParkingRequestData(){
        this.owner = "";
    }
}
