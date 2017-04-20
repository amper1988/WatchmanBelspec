package retrofit.model.get_police_department_with_employers.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;
import retrofit.model.PoliceDepartmentItem;

import java.util.ArrayList;
import java.util.List;

@Root(name = "Envelope")
public class GetPoliceDepartmentWithEmployersResponseEnvelope {
    @Element(name = "Header", required = false)
    private String header;
    @ElementList(name = "return", entry = "PoliceDepartmentItem", required = false)
    @Path("Body/GetPoliceDepartmentWithEmployersResponse")
    private List<PoliceDepartmentItem> policeDepartmentList;

    public List<PoliceDepartmentItem> getPoliceDepartmentList(){
        return this.policeDepartmentList;
    }

    public List<String> getPoliceDepartmentListAsString(){
        ArrayList<String> arrayList = new ArrayList<>();
        if(policeDepartmentList !=null){
            for(PoliceDepartmentItem item: policeDepartmentList){
                arrayList.add(item.getName());
            }
        }else{
            arrayList.add("");
        }
        return arrayList;
    }
}
