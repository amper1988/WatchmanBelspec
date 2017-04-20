package retrofit.model;

import interfaces.ItemWithDepends;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

@Root(name = "ManufactureItem")
public class ManufactureItem implements ItemWithDepends{
    @Element(name = "Name")
    private String name;

    @ElementList(name = "ModelList", entry = "ModelItem", required = false)
    private List<ModelItem> modelList;

    public List<ModelItem> getModelList() {
        return this.modelList;
    }

    public List<String> getModelListAsString() {
        ArrayList<String> arrayList = new ArrayList<>();
        if(modelList != null){
            for(ModelItem item: modelList){
                arrayList.add(item.getName());
            }

        }else{
            arrayList.add("");
        }
        return arrayList;
    }

    @Override
    public List<String> getDependElements() {
       return getModelListAsString();
    }

    @Override
    public String getName(){
        return this.name;
    }

}
