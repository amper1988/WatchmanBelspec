package retrofit.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "PolicemanItem")
public class PolicemanItem {
    @Element(name = "Name", required = false)
    private String name;

    public String getName(){
        return  this.name;
    }
}
