package ser516.project3.client.thread;

import java.util.Observable;

/**
 * This class helps in forming connection between server and client
 */

public class ClientConnectionThreadObservable extends Observable {
	private String ipAddress;
	private int port;
	private String endpoint;
	private static ClientConnectionThreadObservable instance;

	/**
	 * This method sets ip address and port no during connection
	 * @param ipAddress
	 * @param port
	 * @param endoint
	 */
	public void setConnectionDetails(String ipAddress, int port, String endoint) {
		 this.ipAddress = ipAddress;
		 this.port = port;
		 this.endpoint = endoint;
		 this.setChanged();
		 notifyObservers();
	 }

	/**
	 * This static method gets connection instance
	 * @return instance an object of ClientConnectionThreadObservable
	 */
	public static ClientConnectionThreadObservable getInstance() {
		if (instance == null) {
			instance = new ClientConnectionThreadObservable();
		}
		return instance;
	}

	/**
	 * This method returns IP address
	 * @return ipAddress String 
	 */
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * This method returns port
	 * @return port integer
	 */
	public int getPort() {
		return port;
	}

	/**
	 * This method returns endpoint
	 * @return endpoint String 
	 */
	public String getEndpoint() {
		return endpoint;
	}
	
	
}
