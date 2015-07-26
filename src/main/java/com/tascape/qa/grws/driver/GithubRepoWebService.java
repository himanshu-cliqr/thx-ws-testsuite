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
package com.tascape.qa.grws.driver;

import com.tascape.qa.th.comm.WebServiceCommunication;
import com.tascape.qa.th.driver.EntityDriver;
import java.io.IOException;
import javax.naming.OperationNotSupportedException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * sample web service.
 *
 * @author linsong wang
 */
public class GithubRepoWebService extends EntityDriver {
    private static final Logger LOG = LoggerFactory.getLogger(GithubRepoWebService.class);

    public static final String SYSPROP_HOST = "qa.th.driver.grws.HOST";

    public static final String SYSPROP_PORT = "qa.th.driver.grws.PORT";

    private enum Endpoint {
        STATUS("status"),
        TASCAPE_THX_WS_TESTSUITE("tascape/thx-ws-testsuite"),
        LICENSE("tascape/thx-ws-testsuite/blob/master/LICENSE"),
        POM("tascape/thx-ws-testsuite/blob/master/pom.xml"),
        ISSUES("tascape/thx-ws-testsuite/issues");

        private final String value;

        Endpoint(String endpoint) {
            this.value = endpoint;
        }
    }

    private WebServiceCommunication wsc;

    public void setWebServiceComminication(WebServiceCommunication ws) {
        this.wsc = ws;
    }

    public String getStatus() throws IOException {
//        TODO
//        String res = this.wsc.get(Endpoint.STATUS.value);
//        return res;
        return "OK";
    }

    public String getIssues() throws IOException {
        return this.wsc.get(Endpoint.ISSUES.value);
    }

    public String getPom() throws IOException {
        String res = this.wsc.get(Endpoint.POM.value);
        return res;
    }

    /**
     * This is not implemented.
     *
     * @param issue issue in JSON format
     *
     * @return new issue id
     *
     * @throws OperationNotSupportedException
     */
    public String postIssue(JSONObject issue) throws OperationNotSupportedException {
        throw new OperationNotSupportedException();
//        String id = this.wsc.postJson(Endpoint.ISSUES.value, issue);
//        return id;
    }

    @Override
    public String getName() {
        return GithubRepoWebService.class.getName();
    }

    @Override
    public void reset() throws Exception {
        LOG.debug("NA");
    }
}
