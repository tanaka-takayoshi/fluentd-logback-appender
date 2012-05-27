package org.fluentd.logger.sender.appender;

import java.util.HashMap;
import java.util.Map;

import ch.qos.logback.classic.spi.ILoggingEvent;

public class TestFluentFormatter extends FluentFormatterBase {

	private String tag;
	
	private String label;

	@Override
	public String getTag(ILoggingEvent event) {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	@Override
	public String getLabel(ILoggingEvent event) {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	@Override
	public Map<String, Object> format(ILoggingEvent event) {
		Map<String, Object> messages = new HashMap<String, Object>();
		messages.put("level", event.getLevel().levelStr);
		messages.put("loggerName", event.getLoggerName());
		messages.put("MDC", event.getMDCPropertyMap());
		messages.put("thread", event.getThreadName());
		messages.put("message", event.getFormattedMessage());
		messages.put("time", event.getTimeStamp());
		return messages;
	}
}
