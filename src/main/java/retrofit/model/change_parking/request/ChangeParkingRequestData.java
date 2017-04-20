package retrofit.model.change_parking.request;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "tes:ChangeParking")
public class ChangeParkingRequestData {
    @Element(name = "Parking")
    private String parking;

    public ChangeParkingRequestData(String parking){
        this.parking = parking;
    }

    public void setParking(String parking) {
        this.parking = parking;
    }
}
