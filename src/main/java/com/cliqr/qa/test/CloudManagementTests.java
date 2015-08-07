package com.cliqr.qa.test;

import com.cliqr.qa.driver.CloudManagementWebService;
import com.tascape.qa.th.driver.TestDriver;
import com.tascape.qa.th.test.AbstractTest;
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

    public static final TestDriver SERVICE = new TestDriver(CloudManagementTests.class, "");

    private final CloudManagementWebService service;

    public CloudManagementTests() {
        this.globalTimeout = new Timeout(600, TimeUnit.SECONDS);
        this.service = this.getEntityDriver(SERVICE, CloudManagementWebService.class);
    }

    @Test
    public void testUsers() throws Exception {
        JSONObject res = this.service.getUsers();
        LOG.debug("users\n{}", res.toString(2));
        JSONArray users = res.getJSONArray("users");
        int len = users.length();
        LOG.debug("Verify each password is masked.");
        for (int i = 0; i < len; i++) {
            Assert.assertEquals("user name is not masked,",
                "== red-acted ==",
                users.getJSONObject(i).getString("password"));
        }
    }

    @Override
    public String getApplicationUnderTest() {
        return this.service.getName();
    }
}
