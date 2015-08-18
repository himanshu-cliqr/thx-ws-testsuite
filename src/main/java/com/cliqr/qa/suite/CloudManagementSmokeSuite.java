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

    private CloudManagementWebService service;

    @Override
    public void setUpTestClasses() {
        this.addTestClass(CloudManagementTests.class);
    }

    @Override
    protected void setUpEnvironment() throws Exception {
        /*
         * The following system properties are required.
         * qa.th.comm.ws.HOST
         * qa.th.comm.ws.PORT
         * qa.th.comm.ws.USER
         * qa.th.comm.ws.PASS
         */
        WebServiceCommunication wsc = WebServiceCommunication.newInstance();
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
    }
}
