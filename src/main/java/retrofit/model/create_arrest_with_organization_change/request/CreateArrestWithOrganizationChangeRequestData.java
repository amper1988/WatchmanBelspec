package retrofit.model.create_arrest_with_organization_change.request;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "tes:CreateArrestWithOrganization")
public class CreateArrestWithOrganizationChangeRequestData {
    @Element(name = "Identifier")
    private int identifier;
    @Element(name = "WhoArrested")
    private String whoArrested;
    @Element(name = "ArrestReason")
    private String arrestReason;
    @Element(name = "OrganizationAfter")
    private String organization;

    public CreateArrestWithOrganizationChangeRequestData(int identifier, String whoArrested, String arrestReason, String organization){
        this.identifier = identifier;
        this.whoArrested = whoArrested;
        this.arrestReason = arrestReason;
        this.organization = organization;
    }
}
