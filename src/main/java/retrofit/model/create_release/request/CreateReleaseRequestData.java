package retrofit.model.create_release.request;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "tes:CreateRelease")
public class CreateReleaseRequestData {
    @Element(name = "Identifier")
    private int identifier;
    @Element(name = "BankReceiptNumber")
    private String bankReceiptNumber;
    @Element(name = "PoliceDepartmentReceiptNumber")
    private String policeDepartmentReceiptNumber;

    public CreateReleaseRequestData(int identifier, String bankReceiptNumber, String policeDepartmentReceiptNumber){
        this.identifier = identifier;
        this.bankReceiptNumber = bankReceiptNumber;
        this.policeDepartmentReceiptNumber = policeDepartmentReceiptNumber;
    }
}
