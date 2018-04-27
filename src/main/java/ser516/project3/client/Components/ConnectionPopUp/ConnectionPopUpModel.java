package ser516.project3.client.Components.ConnectionPopUp;

import ser516.project3.interfaces.ModelInterface;

/**
 * Model for creating theconnection pop up view
 *
 * @author vishakhasingal
 */
public class ConnectionPopUpModel implements ModelInterface {
    private String ipAddress;
    private int portNumber;
    private boolean connectionStatus;

    /**
     * Constructor to create the connection to the server using initial values
     */
    public ConnectionPopUpModel() {
        ipAddress = "localhost";
        portNumber = 1516;
        connectionStatus = false;
    }

    /**
     * Returns the IP Address configured at the client
     *
     * @return IP Address string
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * updates the IP Address in the model from the view
     *
     * @param ipAddress the ip address to be updated
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     * Returns the port number currently at the client side
     *
     * @return Port number
     */
    public int getPortNumber() {
        return portNumber;
    }

    /**
     * Updates the port number in the model from the view
     *
     * @param portNumber the port number to be updated
     */
    public void setPortNumber(int portNumber) {
        this.portNumber = portNumber;
    }

    /**
     * Returns the connection status in the model from the view
     *
     * @return if the connection to server is true or not
     */
	public boolean isConnectionStatus() {
		return connectionStatus;
	}

	/**
     * Updates the connection status in the model from the view
     *
     * @param connectionStatus if the connection to server is true or not
     */
	public void setConnectionStatus(boolean connectionStatus) {
		this.connectionStatus = connectionStatus;
	}
}
