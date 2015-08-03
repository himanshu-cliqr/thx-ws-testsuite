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
package com.tascape.qa.grws.suite;

import com.tascape.qa.grws.test.GithubRepoTests;
import com.tascape.qa.th.suite.AbstractSuite;
import com.tascape.qa.grws.driver.GithubRepoWebService;
import com.tascape.qa.th.comm.WebServiceCommunication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author linsong wang
 */
public class GithubRepoSmokeSuite extends AbstractSuite {
    private static final Logger LOG = LoggerFactory.getLogger(GithubRepoSmokeSuite.class);

    private WebServiceCommunication wsc;

    private GithubRepoWebService adsIngestion;

    @Override
    public void setUpTestClasses() {
        this.addTestClass(GithubRepoTests.class);
    }

    @Override
    protected void setUpEnvironment() throws Exception {
        this.wsc = new WebServiceCommunication(
            SYSCONFIG.getProperty(GithubRepoWebService.SYSPROP_HOST, "localhost"),
            SYSCONFIG.getIntProperty(GithubRepoWebService.SYSPROP_PORT, 8443));
        /*
         * Setup the proper authentication method, based on your service deployment
         *
         * wsc.setClientCertificate("certificate-file-path", "certificate-key-password");
         *
         * wsc.setUsernamePassword("username", "password");
         */
        wsc.connect();

        this.adsIngestion = new GithubRepoWebService();
        adsIngestion.setWebServiceComminication(wsc);
        this.putTestDirver(GithubRepoTests.GITHUB_SERVICE, adsIngestion);
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
