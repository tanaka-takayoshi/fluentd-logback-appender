package org.fluentd.logger.sender.appender;

import java.util.HashMap;
import java.util.Map;

import org.fluentd.logger.FluentLogger;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Layout;
import ch.qos.logback.core.UnsynchronizedAppenderBase;

public class FluentAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {

	protected String defaultTag;
	
	protected Layout<ILoggingEvent> layout;

	protected Map<String, ConnectionInfo> connections = new HashMap<String, ConnectionInfo>();
	
	public void setConnectionInfo(ConnectionInfo connectionInfo) {
		connections.put(connectionInfo.getTag(), connectionInfo);
	}
	
	public FluentAppender() {
		super();
	}

	public FluentAppender(final Layout<ILoggingEvent> layout) {
		this.setLayout(layout);
	}

	/**
	 * @param layout
	 **/
	public void setLayout(final Layout<ILoggingEvent> layout) {
		this.layout = layout;
	}

	public String getDefaultTag() {
		return defaultTag;
	}

	public void setDefaultTag(String defaultTag) {
		this.defaultTag = defaultTag;
	}

	@Override
	protected void append(ILoggingEvent eventObject) {
		if (!isStarted())
			return;
		//TODO enable configuring tag
		ConnectionInfo info = connections.get(defaultTag);
		FluentLogger fluentLogger = FluentLogger.getLogger(info.getTag(), info.getHost(), info.getPort());
		Map<String, Object> messages = new HashMap<String, Object>();
		messages.put("level", eventObject.getLevel().levelStr);
		messages.put("loggerName", eventObject.getLoggerName());
		messages.put("MDC", eventObject.getMDCPropertyMap());
		messages.put("thread", eventObject.getThreadName());
		messages.put("message", eventObject.getFormattedMessage());
		//TODO enable configuring label
		fluentLogger.log("label", messages, eventObject.getTimeStamp());
	}

	@Override
	public void start() {
		if (this.layout == null) {
			addError("No layout set for the appender named [" + name + "].");
			return;
		}

		if (connections.isEmpty()) {
			addError("There is no connection info.");
			return;
		}

		super.start();
	}
}
