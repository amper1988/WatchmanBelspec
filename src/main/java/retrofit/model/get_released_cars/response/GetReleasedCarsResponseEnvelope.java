package retrofit.model.get_released_cars.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;
import retrofit.model.CarDataShortList;

@Root(name = "Envelope")
public class GetReleasedCarsResponseEnvelope {
    @Element(name = "Header", required = false)
    private String header;

    @Element(name="return")
    @Path("Body/GetReleasedCarsResponse")
    private CarDataShortList data;

    public CarDataShortList getData(){
        return this.data;
    }

}
