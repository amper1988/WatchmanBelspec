package retrofit.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "ModelItem")
public class ModelItem {
    @Element(name = "Name")
    private String name;

    public String getName(){
        return this.name;
    }
}
