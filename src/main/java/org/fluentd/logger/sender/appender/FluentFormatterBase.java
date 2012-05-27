package org.fluentd.logger.sender.appender;

import java.util.HashMap;
import java.util.Map;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.spi.ContextAwareBase;

public class FluentFormatterBase extends ContextAwareBase implements FluentFormatter<ILoggingEvent> {


	@Override
	public Map<String, Object> format(ILoggingEvent event) {
		Map<String, Object> message = new HashMap<String, Object>();
		return message;
	}

	@Override
	public long getTimeStamp(ILoggingEvent event) {
		return event.getTimeStamp();
	}

	@Override
	public String getTag(ILoggingEvent event) {
		return "";
	}
	
	@Override
	public String getLabel(ILoggingEvent event) {
		return null;
	}
}
