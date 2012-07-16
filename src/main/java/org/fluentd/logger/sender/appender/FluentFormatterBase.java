package org.fluentd.logger.sender.appender;

import java.util.HashMap;
import java.util.Map;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.spi.ContextAwareBase;

public class FluentFormatterBase extends ContextAwareBase implements
		FluentFormatter<ILoggingEvent> {

	private boolean isStarted;

	protected String name;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}
	
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

	@Override
	public void start() {
		isStarted = true;
	}

	@Override
	public void stop() {
		isStarted = false;
	}

	@Override
	public boolean isStarted() {
		return isStarted;
	}

	@Override
	public String toString() {
		return this.getClass().getName() + "[" + name + "]";
	}
}
