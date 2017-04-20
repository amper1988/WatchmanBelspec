package retrofit.model.get_owner_driver_data.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;
import retrofit.model.OwnerDriverData;

@Root(name = "Envelope")
public class GetOwnerDriverDataResponseEnvelope {
    @Element(name = "Header", required = false)
    private String header;
    @Element(name = "return")
    @Path("Body/GetOwnerDriverDataResponse")
    private OwnerDriverData ownerDriverData;

    public OwnerDriverData getOwnerDriverData(){
        return this.ownerDriverData;
    }
}
