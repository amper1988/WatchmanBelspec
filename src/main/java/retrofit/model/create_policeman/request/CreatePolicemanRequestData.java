package retrofit.model.create_policeman.request;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "tes:CreatePoliceman")
public class CreatePolicemanRequestData {
    @Element(name = "PoliceDepartment")
    private String policeDepartment;
    @Element(name = "Rank")
    private String rank;
    @Element(name = "Position")
    private String position;
    @Element(name = "Name")
    private String name;
    @Element(name = "Code")
    private String code;

    public CreatePolicemanRequestData(String policeDepartment, String rank, String position, String name, String code){
        this.policeDepartment = policeDepartment;
        this.rank = rank;
        this.position = position;
        this.name = name;
        this.code = code;
    }
}
