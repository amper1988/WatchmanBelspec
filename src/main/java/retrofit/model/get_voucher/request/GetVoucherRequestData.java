package retrofit.model.get_voucher.request;


import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "tes:GetVoucher")
public class GetVoucherRequestData {
    @Element(name = "Identifier")
    private  int identifier;

    public GetVoucherRequestData(int identifier){
        this.identifier = identifier;
    }


}
