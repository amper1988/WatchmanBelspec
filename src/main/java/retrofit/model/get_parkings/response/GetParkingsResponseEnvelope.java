package retrofit.model.get_parkings.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;
import retrofit.model.Parking;

import java.util.ArrayList;
import java.util.List;

@Root(name = "Envelope")
public class GetParkingsResponseEnvelope {
    @Element(name = "Header", required = false)
    private String header;

    @ElementList(name = "ParkingList", entry = "ParkingItem", inline = true)
    @Path("Body/GetParkingsResponse/return")
    private List<Parking> parkings;

    public List<Parking> getParkings(){
        return this.parkings;
    }

    public List<String> getParkingsAsString(){
        ArrayList<String> arrayList = new ArrayList<>();
        if(parkings != null){
            for(Parking item: parkings){
                arrayList.add(item.getName());
            }
        }else {
            arrayList.add("");
        }
        return arrayList;
    }

}
