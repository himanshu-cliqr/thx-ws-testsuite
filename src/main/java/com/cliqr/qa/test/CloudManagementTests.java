package com.cliqr.qa.test;

import com.cliqr.qa.driver.CloudManagementWebService;
import com.tascape.qa.th.comm.WebServiceException;
import com.tascape.qa.th.driver.TestDriver;
import com.tascape.qa.th.test.AbstractTest;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author NA
 */
public class CloudManagementTests extends AbstractTest {
    private static final Logger LOG = LoggerFactory.getLogger(CloudManagementTests.class);

    public static final TestDriver SERVICE = new TestDriver(CloudManagementTests.class, "CLOUD_MANAGEMENT_SERVICE");

    private final CloudManagementWebService service;

    public CloudManagementTests() {
        this.globalTimeout = new Timeout(600, TimeUnit.SECONDS);
        this.service = this.getEntityDriver(SERVICE, CloudManagementWebService.class);
    }

    @Test
    public void testAllUsers() throws Exception {
        JSONObject res = this.service.getUsers();
        LOG.debug("users\n{}", res.toString(2));
        JSONArray users = res.getJSONArray("users");
        int len = users.length();
        LOG.debug("Verify each password is masked.");
        for (int i = 0; i < len; i++) {
            Assert.assertEquals("password is not masked,",
                "== red-acted ==",
                users.getJSONObject(i).getString("password"));
        }
    }

    @Test
    public void testPostUserNegative() throws Exception {
        String id = UUID.randomUUID().toString();
        JSONObject user = new JSONObject();
        user.put("firstName", "user-" + id);
        user.put("lastName", "Cliqr");
        user.put("password", "cliqr");
        user.put("emailAddr", "user." + id + "@cliqr.com");
        user.put("companyName", "Cliqr, Inc");
        user.put("phoneNumber", "14085467899");
        user.put("externalId", "");
        user.put("tenantId", 1);

        LOG.debug("User\n{}", user.toString(2));
        this.service.postUser(user);

        this.expectedException.expect(WebServiceException.class);
        String errorMsg = "The user with email address " + user.getString("emailAddr") + " already exists.";
        this.expectedException.expectMessage(errorMsg);
        this.service.postUser(user);
    }

    @Test
    public void testPostUser() throws Exception {
        String id = UUID.randomUUID().toString();
        JSONObject user = new JSONObject();
        user.put("firstName", "user-" + id);
        user.put("lastName", "Cliqr");
        user.put("password", "cliqr");
        user.put("emailAddr", "user." + id + "@cliqr.com");
        user.put("companyName", "Cliqr, Inc");
        user.put("phoneNumber", "14085467899");
        user.put("externalId", "");
        user.put("tenantId", 1);

        LOG.debug("User\n{}", user.toString(2));
        this.service.postUser(user);

        JSONObject res = this.service.getUsers();
        JSONArray users = res.getJSONArray("users");
        int len = users.length();
        for (int i = 0; i < len; i++) {
            JSONObject u = users.getJSONObject(i);
            if (u.getString("emailAddr").equals(user.getString("emailAddr"))) {
                LOG.debug("user {} created", u.getString("emailAddr"));
                return;
            }
        }
        Assert.fail("user did not get created");
    }

    @Override
    public String getApplicationUnderTest() {
        return this.service.getName();
    }
}
