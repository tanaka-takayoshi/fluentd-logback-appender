package org.fluentd.logger.sender.appender;

import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FluentAppenderTest {

	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Test
	public void test() {
		log.info("Test log message2");
	}

}
