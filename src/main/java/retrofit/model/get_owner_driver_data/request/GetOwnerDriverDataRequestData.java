package retrofit.model.get_owner_driver_data.request;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "tes:GetOwnerDriverData")
public class GetOwnerDriverDataRequestData {
    @Element(name = "tes:CarID")
    private String carId;

    public GetOwnerDriverDataRequestData(String carId){
        super();
        this.carId = carId;
    }
}
