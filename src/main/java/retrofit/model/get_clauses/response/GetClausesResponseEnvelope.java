package retrofit.model.get_clauses.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;
import retrofit.model.ClauseItem;

import java.util.ArrayList;
import java.util.List;

@Root(name = "Envelope")
public class GetClausesResponseEnvelope {
    @Element(name = "Header", required = false)
    private String header;

    @ElementList(name = "return", entry = "ClauseItem", required = false)
    @Path("Body/GetClausesResponse")
    private List<ClauseItem> clauseList;

    public List<ClauseItem> getClauseList(){
        return clauseList;
    }

    public List<String> getClauseListAsString(){
        ArrayList<String> arrayList = new ArrayList<>();
        if(clauseList != null){
            for(ClauseItem item: clauseList){
                arrayList.add(item.getName());
            }
        }else{
            arrayList.add("");
        }
        return arrayList;
    }
}
