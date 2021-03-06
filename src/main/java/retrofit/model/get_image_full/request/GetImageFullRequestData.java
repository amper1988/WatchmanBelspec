package retrofit.model.get_image_full.request;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "tes:GetImageFull")
public class GetImageFullRequestData {
    @Element(name = "Identifier")
    private int identifier;
    @Element(name = "Index")
    private int index;

    public GetImageFullRequestData(int identifier, int index){
        this.identifier = identifier;
        this.index = index;
    }
}
