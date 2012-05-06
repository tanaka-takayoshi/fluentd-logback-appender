package org.fluentd.logger.sender.appender;

import java.util.HashMap;
import java.util.Map;

import org.fluentd.logger.FluentLogger;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Layout;
import ch.qos.logback.core.UnsynchronizedAppenderBase;

public class FluentAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {

	private FluentLogger fluentLogger;

	protected Layout<ILoggingEvent> layout;

	protected String tag;
	
	protected String host;

	protected int port;
	
	protected String label;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
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

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setPort(int port) {
		this.port = port;
	}

	@Override
	protected void append(ILoggingEvent eventObject) {
		if (!isStarted())
			return;
		
		Map<String, Object> messages = new HashMap<String, Object>();
		messages.put("level", eventObject.getLevel().levelStr);
		messages.put("loggerName", eventObject.getLoggerName());
		messages.put("MDC", eventObject.getMDCPropertyMap());
		messages.put("thread", eventObject.getThreadName());
		messages.put("message", eventObject.getFormattedMessage());
		
		fluentLogger.log(label, messages, eventObject.getTimeStamp());
	}

	@Override
	public void start() {
		if (this.layout == null) {
			addError("No layout set for the appender named [" + name + "].");
			return;
		}

		try {
			fluentLogger = FluentLogger.getLogger(tag, host, port);
		} catch (RuntimeException e) {
			addError("Cannot create FluentLogger.", e);
		}

		super.start();
	}
}
