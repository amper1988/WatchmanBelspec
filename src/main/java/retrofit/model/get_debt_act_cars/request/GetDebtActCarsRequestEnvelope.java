package retrofit.model.get_debt_act_cars.request;

import org.simpleframework.xml.*;

@Root(name = "soap12:Envelope")
@NamespaceList({
        @Namespace(prefix = "tes", reference = "uri.com"),
        @Namespace(prefix = "soap12", reference = "http://schemas.xmlsoap.org/soap/envelope/")
})
public class GetDebtActCarsRequestEnvelope {
    @Element(name = "tes:GetDebtActCars")
    @Path("soap12:Body")
    private GetDebtActCarsRequestData data;

    public GetDebtActCarsRequestEnvelope(int page, String param){
        this.data = new GetDebtActCarsRequestData(page, param);
    }
}
