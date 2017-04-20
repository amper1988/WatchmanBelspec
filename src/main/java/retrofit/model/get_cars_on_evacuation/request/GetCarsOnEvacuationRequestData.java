package retrofit.model.get_cars_on_evacuation.request;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "tes:GetCarsOnEvacuation")
public class GetCarsOnEvacuationRequestData {
    @Element(name = "tes:Page")
    private int page;
    @Element(name = "tes:Param")
    private String param;

    GetCarsOnEvacuationRequestData(int page, String param){
        this.page = page;
        this.param = param;
    }
}
