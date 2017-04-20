package retrofit.model;

import interfaces.ItemWithDepends;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

@Root(name = "PoliceDepartmentItem")
public class PoliceDepartmentItem implements ItemWithDepends {
    @Element(name = "Name", required = false)
    private String name;
    @ElementList(name = "PolicemanList", entry = "PolicemanItem", required = false)
    private List<PolicemanItem> policemanList;

    @Override
    public List<String> getDependElements() {
        return getPolicemanListAsString();
    }

    @Override
    public String getName(){
        return this.name;
    }

    public List<PolicemanItem> getPolicemanList(){
        return this.policemanList;
    }

    public List<String> getPolicemanListAsString(){
        ArrayList<String > arrayList = new ArrayList<>();
        if(policemanList!= null){
            for(PolicemanItem item: policemanList){
                arrayList.add(item.getName());
            }
        }else{
            arrayList.add("");
        }
        return arrayList;
    }
}
