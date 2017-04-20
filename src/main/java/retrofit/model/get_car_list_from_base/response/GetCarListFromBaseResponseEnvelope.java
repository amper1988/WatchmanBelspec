package retrofit.model.get_car_list_from_base.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;
import retrofit.model.CarDataShortList;

@Root(name = "Envelope")
public class GetCarListFromBaseResponseEnvelope {
    @Element(name = "Header", required = false)
    private String header;

    @Element(name = "return")
    @Path("Body/GetCarListFromBaseResponse")
    private CarDataShortList data;

    public CarDataShortList getData(){
        return this.data;
    }
}
