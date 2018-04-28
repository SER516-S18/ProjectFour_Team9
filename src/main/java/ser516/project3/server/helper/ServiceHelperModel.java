package ser516.project3.server.helper;

/**
 * Service model for notifying the controllers and views 
 * about changes from the service 
 * 
 * @author vishakhasingal
 */
public class ServiceHelperModel extends java.util.Observable {

    private boolean serverStatus;
    private boolean shouldSendData;
    private double timeElapsed; 
    private boolean serverStartedStatus;
    private boolean isServerError;
    private String logMessage;
    private static ServiceHelperModel serviceModel;

    /**
     * Constructor to initialize with default values
     */
    public ServiceHelperModel() {
    	logMessage = "";
    }
    
    /**
     * Static method to create singleton instance
     * 
     * @return returns singleton instance of ServiceHelperModel
     */
    public static ServiceHelperModel getInstance(){
        if (serviceModel == null) {
            serviceModel = new ServiceHelperModel();
        }
        return serviceModel;
    }

    /**
     * sets the variable server status
     * 
     * @param serverStatus the status of the server
     */
    public void setServerStatus(boolean serverStatus) {
        this.serverStatus = serverStatus;
        notifyAllObservers();
    }

    /**
     * get the status of the server
     * 
     * @return boolean value of the server status
     */
    public boolean isServerStatus() {
        return serverStatus;
    }

	public boolean isShouldSendData() {
		return shouldSendData;
	}

	public void setShouldSendData(boolean shouldSendData) {
		this.shouldSendData = shouldSendData;
		notifyAllObservers();
	}

	public double getTimeElapsed() {
		return timeElapsed;
	}

	public void setTimeElapsed(double timeElapsed) {
		this.timeElapsed = timeElapsed;	
		notifyAllObservers();
	}
	
	
	
	/**
	 * @return the serverStartedStatus
	 */
	public boolean isServerStartedStatus() {
		return serverStartedStatus;
	}

	/**
	 * @param serverStartedStatus the serverStartedStatus to set
	 */
	public void setServerStartedStatus(boolean serverStartedStatus) {
		this.serverStartedStatus = serverStartedStatus;
		notifyAllObservers();
	}
	
	

	/**
	 * @return the isServerError
	 */
	public boolean isServerError() {
		return isServerError;
	}

	/**
	 * @param isServerError the isServerError to set
	 */
	public void setServerError(boolean isServerError) {
		this.isServerError = isServerError;
		this.notifyAllObservers();
	}

	/**
	 * @return the logMessage
	 */
	public String getLogMessage() {
		return logMessage;
	}

	/**
	 * @param logMessage the logMessage to set
	 */
	public void setLogMessage(String logMessage) {
		this.logMessage = logMessage;
	}

	/**
	 * method to notify all the observers to update according to the change
	 */
	public void notifyAllObservers() {
		this.setChanged();
        this.notifyObservers();
	}
}
