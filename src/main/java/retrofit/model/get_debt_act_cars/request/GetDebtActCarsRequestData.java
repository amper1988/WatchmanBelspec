package retrofit.model.get_debt_act_cars.request;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "tes:GetDebtActCars")
public class GetDebtActCarsRequestData {
    @Element(name = "tes:Page")
    private int page;
    @Element(name = "tes:Param")
    private String param;

    GetDebtActCarsRequestData(int page, String param){
        this.page = page;
        this.param = param ;
    }
}
