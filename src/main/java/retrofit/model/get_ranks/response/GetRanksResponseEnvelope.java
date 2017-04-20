package retrofit.model.get_ranks.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;
import retrofit.model.RankItem;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

@Root(name = "Envelope")
public class GetRanksResponseEnvelope {
    @Element(name = "Header", required = false)
    private String header;

    @ElementList(name = "RankList", entry = "RankItem", inline = true)
    @Path("Body/GetRanksResponse/return")
    private List<RankItem> rankItems;

    public List<RankItem> getRankItems(){
        return rankItems;
    }

    public List<String> getRankItemsAsString(){
        ArrayList<String> arrayList = new ArrayList<>();
        if(rankItems != null){
            for(RankItem item: rankItems){
                arrayList.add(item.getName());
            }
        }else{
            arrayList.add("");
        }
        return arrayList;
    }
}
