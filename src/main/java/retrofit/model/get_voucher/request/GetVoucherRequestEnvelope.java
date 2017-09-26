package retrofit.model.get_voucher.request;

import org.simpleframework.xml.*;

@Root(name = "soap12:Envelope")
@NamespaceList({
        @Namespace(prefix = "tes", reference = "uri.com"),
        @Namespace(prefix = "soap12", reference = "http://schemas.xmlsoap.org/soap/envelope/")
})
public class GetVoucherRequestEnvelope {
    @Element(name = "tes:GetVoucher")
    @Path("soap12:Body")
    private GetVoucherRequestData data;

    public GetVoucherRequestEnvelope(int identifier){

        this.data = new GetVoucherRequestData(identifier);

    }
}
