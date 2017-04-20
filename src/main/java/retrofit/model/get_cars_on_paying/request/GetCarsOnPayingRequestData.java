package retrofit.model.get_cars_on_paying.request;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "tes:GetCarsOnPaying")
public class GetCarsOnPayingRequestData {
    @Element(name = "tes:Page")
    private int page;
    @Element(name = "tes:Param")
    private String param;

    GetCarsOnPayingRequestData(int page, String param){
        this.page = page;
        this.param = param;
    }
}
