package org.fluentd.logger.sender.appender;

public class ConnectionInfo {
	private String host;

	private int port;
	
	private String tag;
	
	public void setHost(String host) {
		this.host = host;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	@Override
	public String toString() {
		return "ConnectionInfo [host=" + host + ", port=" + port + ", tag="
				+ tag + "]";
	}
	
}
