package retrofit.model.get_cars_on_paying.response;


import org.simpleframework.xml.Element;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;
import retrofit.model.CarDataShortList;

@Root(name = "Envelope")
public class GetCarsOnPayingResponseEnvelope {
    @Element(name = "Header", required = false)
    private String header;

    @Element(name="return")
    @Path("Body/GetCarsOnPayingResponse")
    private CarDataShortList data;

    public CarDataShortList getData(){
        return this.data;
    }
}
