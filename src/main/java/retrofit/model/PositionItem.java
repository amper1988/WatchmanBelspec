package retrofit.model;

import org.simpleframework.xml.Element;


public class PositionItem {
    @Element(name = "Name")
    private String name;

    public String getName(){
        return name;
    }
}
