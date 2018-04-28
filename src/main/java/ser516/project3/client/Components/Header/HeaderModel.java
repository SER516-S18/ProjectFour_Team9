package ser516.project3.client.Components.Header;

import ser516.project3.interfaces.ModelInterface;

/**
 * Class to update the header view of the controller
 *
 * @author Adhiraj Tikku, vishakhasingal
 */
public class HeaderModel implements ModelInterface {

	enum SelectedServer {
		NO_SERVER_SELECTED,
		EMOTIONS_SERVER,
		HEALTH_SERVER
	}
	
    private boolean connectionStatus;
    private double timeStamp;
    private boolean healthConnectionStatus;
    private double healthTimeStamp;
    private SelectedServer openDropdown;
    private SelectedServer connectDropdown;

    /**
     * @return the connectionStatus
     */
    public boolean isConnectionStatus() {
        return connectionStatus;
    }

    /**
     * @param connectionStatus the connectionStatus to set
     */
    public void setConnectionStatus(boolean connectionStatus) {
        this.connectionStatus = connectionStatus;
    }

    /**
     * @return the timeStamp
     */
    public double getTimeStamp() {
        return timeStamp;
    }

    /**
     * @param timeStamp the timeStamp to set
     */
    public void setTimeStamp(double timeStamp) {
        this.timeStamp = timeStamp;
    }

	/**
	 * @return the healthConnectionStatus
	 */
	public boolean isHealthConnectionStatus() {
		return healthConnectionStatus;
	}

	/**
	 * @param healthConnectionStatus the healthConnectionStatus to set
	 */
	public void setHealthConnectionStatus(boolean healthConnectionStatus) {
		this.healthConnectionStatus = healthConnectionStatus;
	}

	/**
	 * @return the healthTimeStamp
	 */
	public double getHealthTimeStamp() {
		return healthTimeStamp;
	}

	/**
	 * @param healthTimeStamp the healthTimeStamp to set
	 */
	public void setHealthTimeStamp(double healthTimeStamp) {
		this.healthTimeStamp = healthTimeStamp;
	}

	/**
	 * @return the openDropdown
	 */
	public SelectedServer getOpenDropdown() {
		return openDropdown;
	}

	/**
	 * @param openDropdown the openDropdown to set
	 */
	public void setOpenDropdown(SelectedServer openDropdown) {
		this.openDropdown = openDropdown;
	}

	/**
	 * @return the connectDropdown
	 */
	public SelectedServer getConnectDropdown() {
		return connectDropdown;
	}

	/**
	 * @param connectDropdown the connectDropdown to set
	 */
	public void setConnectDropdown(SelectedServer connectDropdown) {
		this.connectDropdown = connectDropdown;
	}

    

}
