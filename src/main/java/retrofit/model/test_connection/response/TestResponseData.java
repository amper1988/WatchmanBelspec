package retrofit.model.test_connection.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "return")
public class TestResponseData {
    @Element(name = "FullName")
    private String fullName;

    @Element(name = "UserType")
    private  int userType;

    @Element(name = "Organization", required = false)
    private String organization;

    public String getFullName() {
        return fullName;
    }

    public int getUserType() {
        return userType;
    }

    public String getOrganization(){
        return organization;
    }
}
