package retrofit.model.get_released_cars.request;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "tes:GetReleasedCars")
public class GetReleasedCarsRequestData {
    @Element(name = "tes:Page")
    private int page;
    @Element(name = "tes:Param")
    private String param;

    public GetReleasedCarsRequestData(int page, String param){
        this.page = page;
        this.param = param;
    }
}
