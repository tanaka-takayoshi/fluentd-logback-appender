package org.fluentd.logger.sender.appender;

import java.util.HashMap;
import java.util.Map;

import org.fluentd.logger.FluentLogger;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.UnsynchronizedAppenderBase;

public class FluentAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {

	protected FluentFormatterBase formatter;
	
	protected Map<String, ConnectionInfo> connections = new HashMap<String, ConnectionInfo>();
	
	public void setConnectionInfo(ConnectionInfo connectionInfo) {
		connections.put(connectionInfo.getTag(), connectionInfo);
	}
	
	public FluentAppender() {
		super();
	}

	public FluentAppender(final FluentFormatterBase formatter) {
		super();
		setFormatter(formatter);
	}

	public void setFormatter(FluentFormatterBase formatter) {
		this.formatter = formatter;
	}
	
	@Override
	protected void append(ILoggingEvent eventObject) {
		if (!isStarted())
			return;
		//TODO enable configuring tag
		String tag = formatter.getTag(eventObject);
		ConnectionInfo info = connections.get(tag);
		FluentLogger fluentLogger = FluentLogger.getLogger(info.getTag(), info.getHost(), info.getPort());
		Map<String, Object> messages = formatter.format(eventObject);
		long timeStamp = formatter.getTimeStamp(eventObject);
		//TODO enable configuring label
		fluentLogger.log(formatter.getLabel(eventObject), messages, timeStamp);
	}

	@Override
	public void start() {
		if (this.formatter == null) {
			addError("No formatter set for the appender named [" + name + "].");
			return;
		}

		if (connections.isEmpty()) {
			addError("There is no connection info.");
			return;
		}

		super.start();
	}
}
