package retrofit.model.check_update_watchman.request;

import org.simpleframework.xml.*;

@Root(name = "soap12:Envelope")
@NamespaceList({
        @Namespace(prefix = "tes", reference = "uri.com"),
        @Namespace(prefix = "soap12", reference = "http://schemas.xmlsoap.org/soap/envelope/")
})
public class CheckUpdateWatchmanRequestEnvelope {
    @Element(name = "soap12:Header", required = false)
    private String header;

    @Element(name = "tes:Version")
    @Path("soap12:Body/tes:CheckUpdateWatchman")
    private String version;

    public CheckUpdateWatchmanRequestEnvelope(String version){
        this.version = version;
    }
}
