package retrofit.model.get_manufactures_with_models.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;
import retrofit.model.ManufactureItem;

import java.util.ArrayList;
import java.util.List;

@Root(name = "Envelope")
public class GetManufacturesWithModelsResponseEnvelope {
    @Element(name = "Header", required = false)
    private String header;
    @ElementList(name = "return", entry = "ManufactureItem", required = false)
    @Path("Body/GetManufacturesWithModelsResponse")
    private List<ManufactureItem> manufacturesList;

    public List<ManufactureItem> getManufacturesList(){
        return  this.manufacturesList;
    }

    public List<String> getManufacturesListAsString(){
        ArrayList<String> arrayList = new ArrayList<>();
        if(manufacturesList != null){
            for(ManufactureItem item: manufacturesList){
                arrayList.add(item.getName());
            }
        }else{
            arrayList.add("");
        }
        return arrayList;
    }
}
