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
package com.tascape.qa.grws.test;

import com.tascape.qa.th.driver.TestDriver;
import com.tascape.qa.th.test.AbstractTest;
import com.tascape.qa.grws.driver.GithubRepoWebService;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author linsong wang
 */
public class GithubRepoTests extends AbstractTest {
    private static final Logger LOG = LoggerFactory.getLogger(GithubRepoTests.class);

    public static final TestDriver GITHUB_SERVICE = new TestDriver(GithubRepoTests.class, "GITHUB_SERVICE");

    private final GithubRepoWebService service;

    public GithubRepoTests() {
        this.globalTimeout = new Timeout(10, TimeUnit.SECONDS);
        this.service = this.getEntityDriver(GITHUB_SERVICE, GithubRepoWebService.class);
    }

    @Test
    public void testStatus() throws Exception {
        long start = System.currentTimeMillis();
        String status = this.service.getStatus();
        this.putResultMetric("Github", "load-status-page", System.currentTimeMillis() - start);
        Assert.assertEquals("error status", "OK", status);
    }

    @Test
    public void testIssues() throws Exception {
        long start = System.currentTimeMillis();
        String issues = this.service.getIssues();
        this.putResultMetric("Github", "load-issues-page", System.currentTimeMillis() - start);
        LOG.debug("\n{}", issues);
        Assert.assertTrue("did not find expected info,", issues.contains("New issue"));
    }

    @Override
    public String getApplicationUnderTest() {
        return this.service.getName();
    }
}
