package retrofit.model.get_positions.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;
import retrofit.model.PositionItem;

import java.util.ArrayList;
import java.util.List;

@Root(name = "Envelope")
public class GetPositionsResponseEnvelope {
    @Element(name = "Header", required = false)
    private String header;

    @ElementList(name = "PositionList", inline = true, entry = "PositionItem")
    @Path("Body/GetPositionsResponse/return")
    private List<PositionItem> positionItems;

    public List<PositionItem> getPositionItems(){
        return this.positionItems;
    }

    public List<String> getPositionsAsString(){
        ArrayList<String> arrayList = new ArrayList<>();
        if(positionItems != null){
            for(PositionItem item: positionItems){
                arrayList.add(item.getName());
            }
        }else
            arrayList.add("");
        return arrayList;
    }
}
