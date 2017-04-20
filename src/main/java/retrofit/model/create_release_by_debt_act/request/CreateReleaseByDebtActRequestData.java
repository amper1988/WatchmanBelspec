package retrofit.model.create_release_by_debt_act.request;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "tes:CreateReleaseByDebtAct")
public class CreateReleaseByDebtActRequestData {
    @Element(name = "Identifier")
    private int identifier;
    @Element(name = "DebtActNumber")
    private String debtActNumber;
    @Element(name = "PoliceDepartmentReceiptNumber")
    private String policeDepartmentReceiptNumber;

    public CreateReleaseByDebtActRequestData(int identifier, String debtActNumber, String policeDepartmentReceiptNumber){
        this.identifier = identifier;
        this.debtActNumber = debtActNumber;
        this.policeDepartmentReceiptNumber = policeDepartmentReceiptNumber;
    }
}
