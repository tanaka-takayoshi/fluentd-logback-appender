package org.fluentd.logger.sender.appender;

import java.util.Map;

public interface FluentFormatter<E> {

	public Map<String, Object> format(E event);
	
	public long getTimeStamp(E event);
	
	public String getTag(E event);
	
	public String getLabel(E event);
}
