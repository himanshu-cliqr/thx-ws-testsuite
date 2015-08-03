
# thx-ws-testsuite
maven template project of test suite for web services testing

## how to run in commandline
1. compile  
   `mvn clean install`

2. run  
  `java -cp target/*:target/th/*:target/dependency/* \`  
        `-Dqa.th.driver.grws.HOST=github.com \`  
        `-Dqa.th.driver.grws.PORT=443 \`  
        `com.tascape.qa.grws.suite.GithubRepoSmokeSuite`  
   

