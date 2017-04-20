package retrofit.model.get_car_list_from_base.request;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "tes:GetCarListFromBase")
public class GetCarListFromBaseRequestData {
    @Element(name = "Page")
    private int page;
    @Element(name = "Param")
    private String param;

    public GetCarListFromBaseRequestData(int page, String param){
        this.page = page;
        this.param = param;
    }
}
