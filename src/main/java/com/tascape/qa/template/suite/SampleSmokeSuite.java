/*
 * Copyright 2015.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tascape.qa.template.suite;

import com.tascape.qa.template.test.SampleTests;
import com.tascape.qa.th.suite.AbstractSuite;
import com.tascape.qa.template.driver.SampleWebService;
import com.tascape.qa.th.comm.WebServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author linsong wang
 */
public class SampleSmokeSuite extends AbstractSuite {
    private static final Logger LOG = LoggerFactory.getLogger(SampleSmokeSuite.class);

    private WebServiceClient wsc;

    private SampleWebService adsIngestion;

    @Override
    public void setUpTestClasses() {
        this.addTestClass(SampleTests.class);
    }

    @Override
    protected void setUpEnvironment() throws Exception {
        // ssh -L8443:localhost:8443 ip-10-123-61-94.ec2.internal
        this.wsc = new WebServiceClient(
            SYSCONFIG.getProperty(SampleWebService.SYSPROP_HOST, "localhost"),
            SYSCONFIG.getIntProperty(SampleWebService.SYSPROP_PORT, 8443));
        wsc.connect();
        this.adsIngestion = new SampleWebService();
        adsIngestion.setWebService(wsc);
        this.putTestDirver(SampleTests.SAMPLE_SERVICE, adsIngestion);
    }

    @Override
    protected void tearDownEnvironment() {
        try {
            this.adsIngestion.reset();
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
