package retrofit.model;

import interfaces.ItemWithDepends;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

@Root(name = "OrganizationItem")
public class OrganizationItem implements ItemWithDepends {
    @Element(name = "Name", required = false)
    private String name;

    @ElementList(name = "WreckerList", entry = "WreckerItem", required = false)
    private List<WreckerItem> wreckerItemList;

    @Override
    public List<String> getDependElements() {
        return getWreckerListAsString();
    }
    @Override
    public String getName(){
        return this.name;
    }

    public List<WreckerItem> getWreckerItemList(){
        return this.wreckerItemList;
    }

    public List<String> getWreckerListAsString(){
        ArrayList<String> arrayList = new ArrayList<>();
        if(wreckerItemList != null){
            for (WreckerItem item: wreckerItemList){
                arrayList.add(item.getName());
            }
        }else{
            arrayList.add("");
        }
        return arrayList;
    }
}
