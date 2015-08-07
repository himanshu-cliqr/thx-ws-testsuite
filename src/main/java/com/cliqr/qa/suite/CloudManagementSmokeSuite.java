
package com.cliqr.qa.suite;

import com.cliqr.qa.driver.CloudManagementWebService;
import com.tascape.qa.th.suite.AbstractSuite;
import com.cliqr.qa.test.CloudManagementTests;
import com.tascape.qa.th.comm.WebServiceCommunication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author NA
 */
public class CloudManagementSmokeSuite extends AbstractSuite {
    private static final Logger LOG = LoggerFactory.getLogger(CloudManagementSmokeSuite.class);

    private WebServiceCommunication wsc;

    private CloudManagementWebService service;

    @Override
    public void setUpTestClasses() {
        this.addTestClass(CloudManagementTests.class);
    }

    @Override
    protected void setUpEnvironment() throws Exception {
        this.wsc = new WebServiceCommunication(
            SYSCONFIG.getProperty(WebServiceCommunication.SYSPROP_HOST, "localhost"),
            SYSCONFIG.getIntProperty(WebServiceCommunication.SYSPROP_PORT, 443));
        wsc.setUsernamePassword("cliqradmin", "466A6C0C1B06D65E");
        wsc.connect();

        this.service = new CloudManagementWebService();
        service.setWebServiceComminication(wsc);
        this.putTestDirver(CloudManagementTests.SERVICE, service);
    }

    @Override
    protected void tearDownEnvironment() {
        try {
            this.service.reset();
        } catch (Exception ex) {
            LOG.warn("Error resetting", ex);
        }
        if (this.wsc != null) {
            try {
                this.wsc.disconnect();
            } catch (Exception ex) {
                LOG.warn("Error shutting down connection", ex);
            }
        }
    }
}
