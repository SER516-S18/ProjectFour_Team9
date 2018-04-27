package ser516.project3.client.thread;

import java.util.Observable;

public class ClientConnectionThreadObservable extends Observable {
	private String ipAddress;
	private int port;
	private String endpoint;
	private static ClientConnectionThreadObservable instance;

	public void setConnectionDetails(String ipAddress, int port, String endoint) {
		 this.ipAddress = ipAddress;
		 this.port = port;
		 this.endpoint = endoint;
		 this.setChanged();
		 notifyObservers();
	 }

	public static ClientConnectionThreadObservable getInstance() {
		if (instance == null) {
			instance = new ClientConnectionThreadObservable();
		}
		return instance;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public int getPort() {
		return port;
	}

	public String getEndpoint() {
		return endpoint;
	}
	
	
}
