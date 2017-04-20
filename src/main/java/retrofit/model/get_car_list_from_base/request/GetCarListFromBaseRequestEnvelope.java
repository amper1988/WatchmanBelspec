package retrofit.model.get_car_list_from_base.request;

import org.simpleframework.xml.*;

@Root(name = "soap12:Envelope")
@NamespaceList({
        @Namespace(prefix = "tes", reference = "uri.com"),
        @Namespace(prefix = "soap12", reference = "http://schemas.xmlsoap.org/soap/envelope/")
})
public class GetCarListFromBaseRequestEnvelope {
    @Element(name = "tes:GetCarListFromBase")
    @Path("soap12:Body")
    private GetCarListFromBaseRequestData data;

    public GetCarListFromBaseRequestEnvelope(int page, String param){
        this.data = new GetCarListFromBaseRequestData(page, param);
    }
}
