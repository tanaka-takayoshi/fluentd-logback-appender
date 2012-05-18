package org.fluentd.logger.sender.appender;

import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.joran.JoranConfigurator;

public class FluentAppenderTest {

	private Logger log = LoggerFactory.getLogger(getClass());
	
	//FIXME must test with mock-fluentd, not actual fluetnd.
	@Test
	public void test() {
		log.info("Test log message2");
		log.debug("hogehoge");
		log.warn("warn");
		log.error("ngege");
	}
}
