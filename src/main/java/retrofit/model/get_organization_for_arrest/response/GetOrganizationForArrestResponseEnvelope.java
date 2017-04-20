package retrofit.model.get_organization_for_arrest.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;
import retrofit.model.OrganizationItem;

import java.util.ArrayList;
import java.util.List;

@Root(name = "Envelope")
public class GetOrganizationForArrestResponseEnvelope {
    @Element(name = "Header", required = false)
    private String header;

    @ElementList(name = "return", entry="OrganizationItem", required = false)
    @Path("Body/GetOrganizationForArrestResponse")
    private List<OrganizationItem> organizationItemList;

    public List<String> getOrganizationListAsString(){
        ArrayList<String> arrayList = new ArrayList<>();
        if(organizationItemList != null && organizationItemList.size()>0){
            for(OrganizationItem item: organizationItemList){
                arrayList.add(item.getName());
            }
        }else{
            arrayList.add("");
        }

        return arrayList;
    }
}
