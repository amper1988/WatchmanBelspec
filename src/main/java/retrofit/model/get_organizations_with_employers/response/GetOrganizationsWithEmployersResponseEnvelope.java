package retrofit.model.get_organizations_with_employers.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;
import retrofit.model.OrganizationItem;

import java.util.ArrayList;
import java.util.List;

@Root(name = "Envelope")
public class GetOrganizationsWithEmployersResponseEnvelope {
    @Element(name = "Header", required = false)
    private String header;

    @ElementList(name = "return", entry = "OrganizationItem", required = false)
    @Path("Body/GetOrganizationsWithEmployersResponse")
    private List<OrganizationItem> organizationItemList;

    public List<OrganizationItem> getOrganizationItemList(){
        return this.organizationItemList;
    }

    public List<String> getOrganizationListAsString(){
        ArrayList<String> arrayList = new ArrayList<>();
        if(organizationItemList!= null){
            for(OrganizationItem item: organizationItemList){
                arrayList.add(item.getName());
            }
        }else{
            arrayList.add("");
        }
        return arrayList;
    }
}
