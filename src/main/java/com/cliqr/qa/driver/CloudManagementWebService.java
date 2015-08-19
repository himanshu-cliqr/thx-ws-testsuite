package com.cliqr.qa.driver;

import com.tascape.qa.th.comm.WebServiceCommunication;
import com.tascape.qa.th.driver.EntityDriver;
import java.io.IOException;
import org.json.JSONArray;
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

    public JSONObject getUsers(int size) throws IOException {
        JSONObject users = this.wsc.getJsonObject(Endpoint.V1_USERS.value, "size=" + size);
        LOG.debug("total {} users", users.getJSONArray("users").length());
        return users;
    }

    public JSONObject getAllUsers() throws IOException {
        return this.getUsers(Integer.MAX_VALUE);
    }

    public void postUser(JSONObject user) throws IOException {
        this.wsc.postJson(Endpoint.V1_USERS.value, user);
    }

    public void deleteUser(String id) throws IOException {
        this.wsc.delete(Endpoint.V1_USERS.value + "/" + id);
    }

    public String getUserId(String email) throws IOException {
        JSONObject res = this.getAllUsers();
        JSONArray users = res.getJSONArray("users");
        int len = users.length();
        LOG.debug("total {} users", len);

        for (int i = 0; i < len; i++) {
            JSONObject user = users.getJSONObject(i);
            if (user.getString("emailAddr").equals(email)) {
                return user.getString("id");
            }
        }
        return null;
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
