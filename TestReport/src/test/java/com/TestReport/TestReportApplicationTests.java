package com.TestReport;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestReportApplicationTests {

	@Test
	void contextLoads() {
		Logger logger = LoggerFactory.getLogger(TestReportApplicationTests.class); 
        logger.info("Hello World");
	}

}
