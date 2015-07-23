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
package com.tascape.qa.template.driver;

import com.tascape.qa.th.comm.WebServiceClient;
import com.tascape.qa.th.driver.EntityDriver;
import java.io.IOException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * sample web service.
 *
 * @author linsong wang
 */
public class SampleWebService extends EntityDriver {
    private static final Logger LOG = LoggerFactory.getLogger(SampleWebService.class);

    public static final String SYSPROP_HOST = "qa.th.driver.sample.HOST";

    public static final String SYSPROP_PORT = "qa.th.driver.sample.PORT";

    private enum Endpoint {
        ENTITY("entity"),
        ENTITY_REGISTER("entity/register"),
        ENTITY_LIST("entity/list"),
        STATUS("status");

        private final String value;

        Endpoint(String endpoint) {
            this.value = endpoint;
        }

        String value() {
            return this.value;
        }
    }

    private WebServiceClient wsc;

    public void setWebService(WebServiceClient ws) {
        this.wsc = ws;
    }

    public String getStatus() throws IOException {
        return this.wsc.get(Endpoint.STATUS.value());
    }

    public String getEntity() throws IOException {
        String res = this.wsc.get(Endpoint.ENTITY.value());
        return res;
    }

    public String postEntity(JSONObject entity) throws IOException {
        String id = this.wsc.postJson(Endpoint.ENTITY.value(), entity);
        return id;
    }

    @Override
    public String getName() {
        return SampleWebService.class.getName();
    }

    @Override
    public void reset() throws Exception {
        LOG.debug("NA");
    }
}
