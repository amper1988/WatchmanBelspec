package retrofit.model;


import interfaces.ItemWithDepends;
import org.simpleframework.xml.Element;

import java.util.List;

public class Parking implements ItemWithDepends{
    @Element(name = "Name", required = false)
    private String name;

    @Override
    public List<String> getDependElements() {
        return null;
    }

    @Override
    public String getName() {
        return name;
    }
}
