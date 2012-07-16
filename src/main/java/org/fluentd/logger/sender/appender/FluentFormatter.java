package org.fluentd.logger.sender.appender;

import java.util.Map;

import ch.qos.logback.core.spi.LifeCycle;

public interface FluentFormatter<E> extends LifeCycle {

	public Map<String, Object> format(E event);
	
	public long getTimeStamp(E event);
	
	public String getTag(E event);
	
	public String getLabel(E event);
	
	public String getName();
	
	public void setName(String name);
}
