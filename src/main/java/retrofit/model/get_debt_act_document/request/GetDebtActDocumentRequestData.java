package retrofit.model.get_debt_act_document.request;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "tes:GetDebtActDocument")
public class GetDebtActDocumentRequestData {
    @Element(name = "Identifier")
    private int identifier;

    public GetDebtActDocumentRequestData(int identifier){
        this.identifier = identifier;
    }
}
