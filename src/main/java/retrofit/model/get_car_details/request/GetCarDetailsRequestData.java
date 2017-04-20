package retrofit.model.get_car_details.request;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "tes:GetCarDetails")
public class GetCarDetailsRequestData {
    @Element(name = "tes:Identifier")
    private int identifier;

    public GetCarDetailsRequestData(int identifier){
        this.identifier = identifier;
    }
}
