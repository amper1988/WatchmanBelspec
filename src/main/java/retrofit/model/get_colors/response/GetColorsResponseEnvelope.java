package retrofit.model.get_colors.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;
import retrofit.model.ColorItem;

import java.util.ArrayList;
import java.util.List;

@Root(name = "Envelope")
public class GetColorsResponseEnvelope {
    @Element(name = "Header", required = false)
    private String header;

    @ElementList(name = "return", entry = "ColorItem", required = false)
    @Path("Body/GetColorsResponse")
    private List<ColorItem> colorList;

    public List<ColorItem> getColorList(){
        return this.colorList;
    }

    public List<String> getColorListAsString(){
        ArrayList<String> arrayList = new ArrayList<>();
        if(colorList != null){
            for(ColorItem item: colorList){
                arrayList.add(item.getName());
            }
        }else{
            arrayList.add("");
        }
        return arrayList;

    }
}
