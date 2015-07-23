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
package com.tascape.qa.template.test;

import com.tascape.qa.th.driver.TestDriver;
import com.tascape.qa.th.test.AbstractTest;
import com.tascape.qa.template.driver.SampleWebService;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author linsong wang
 */
public class SampleTests extends AbstractTest {
    private static final Logger LOG = LoggerFactory.getLogger(SampleTests.class);

    public static final TestDriver SAMPLE_SERVICE = new TestDriver(SampleTests.class, "SAMPLE_SERVICE");

    private final SampleWebService service;

    public SampleTests() {
        this.service = this.getEntityDriver(SAMPLE_SERVICE, SampleWebService.class);
    }

    @Test
    public void testStatus() throws Exception {
        String status = this.service.getStatus();
        Assert.assertEquals("error status", "OK", status);
    }

    @Override
    public String getApplicationUnderTest() {
        return this.service.getName();
    }
}
