package retrofit.model.create_release_by_debt_act.request;

import org.simpleframework.xml.*;

@Root(name = "soap12:Envelope")
@NamespaceList({
        @Namespace(prefix = "tes", reference = "www.uri.com"),
        @Namespace(prefix = "soap12", reference = "http://schemas.xmlsoap.org/soap/envelope/")
})
public class CreateReleaseByDebtActRequestEnvelope {
    @Element(name = "tes:CreateReleaseByDebtAct")
    @Path("soap12:Body")
    private CreateReleaseByDebtActRequestData data;

    public CreateReleaseByDebtActRequestEnvelope(int identifier, String debtActNumber, String policeDepartmentReceiptNumber){
        this.data = new CreateReleaseByDebtActRequestData(identifier, debtActNumber, policeDepartmentReceiptNumber);
    }
}
