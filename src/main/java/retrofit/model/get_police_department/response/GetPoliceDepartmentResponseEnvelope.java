package retrofit.model.get_police_department.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;
import retrofit.model.PoliceDepartmentItem;

import java.util.ArrayList;
import java.util.List;

@Root(name = "Envelope")
public class GetPoliceDepartmentResponseEnvelope {
    @Element(name = "Header", required = false)
    private String header;

    @ElementList(name = "PoliceDepartmentList", entry = "PoliceDepartmentItem", inline = true)
    @Path("Body/GetPoliceDepartmentResponse/return")
    private List<PoliceDepartmentItem> policeDepartmentItems;

    public List<PoliceDepartmentItem> getPoliceDepartmentItems(){
        return this.policeDepartmentItems;
    }

    public List<String> getPoliceDepartmentAsStirng(){
        ArrayList<String > arrayList = new ArrayList<>();
        if(policeDepartmentItems != null){
            for(PoliceDepartmentItem item: policeDepartmentItems){
                arrayList.add(item.getName());
            }
        }else {
            arrayList.add("");
        }
        return arrayList;
    }
}
