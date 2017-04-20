package retrofit.model;


import interfaces.ItemWithDepends;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "ColorItem")
public class ColorItem implements ItemWithDepends {
    @Element(name = "Name", required = false)
    private String name;

    @Override
    public List<String> getDependElements() {
        return null;
    }

    @Override
    public String getName(){
        return this.name;
    }
}
