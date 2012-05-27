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
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			log.info("Test log message" + i);
			log.debug("hogehoge" + i);
			log.warn("warn" + i);
			log.error("ngege" + i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
