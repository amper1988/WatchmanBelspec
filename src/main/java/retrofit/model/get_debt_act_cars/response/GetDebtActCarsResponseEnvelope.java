package retrofit.model.get_debt_act_cars.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;
import retrofit.model.CarDataShortList;

@Root(name = "Envelope")
public class GetDebtActCarsResponseEnvelope {
    @Element(name = "Header", required = false)
    private String header;

    @Element(name="return")
    @Path("Body/GetDebtActCarsResponse")
    private CarDataShortList data;

    public CarDataShortList getData(){
        return this.data;
    }
}
