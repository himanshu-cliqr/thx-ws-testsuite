package com.cliqr.qa.driver;

import com.tascape.qa.th.comm.WebServiceCommunication;
import com.tascape.qa.th.driver.EntityDriver;
import java.io.IOException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * web service.
 *
 * @author NA
 */
public class CloudManagementWebService extends EntityDriver {
    private static final Logger LOG = LoggerFactory.getLogger(CloudManagementWebService.class);

    public static final String SYSPROP_USER = "qa.th.comm.ws.USER";

    public static final String SYSPROP_PASS = "qa.th.comm.ws.PASS";

    private enum Endpoint {
        STATUS("status"),
        V1_USERS("v1/users");

        private final String value;

        Endpoint(String value) {
            this.value = value;
        }
    }

    private WebServiceCommunication wsc;

    public void setWebServiceComminication(WebServiceCommunication wsc) {
        this.wsc = wsc;
    }

    public String getStatus() throws IOException {
//        TODO
//        String res = this.wsc.get(Endpoint.STATUS.value);
//        return res;
        return "OK";
    }

    public JSONObject getUsers() throws IOException {
        return this.wsc.getJsonObject(Endpoint.V1_USERS.value);
    }

    @Override
    public String getName() {
        return CloudManagementWebService.class.getName();
    }

    @Override
    public void reset() throws Exception {
        LOG.debug("NA");
    }
}
