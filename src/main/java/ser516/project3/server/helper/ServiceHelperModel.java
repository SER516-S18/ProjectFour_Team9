package ser516.project3.server.helper;

public class ServiceHelperModel extends java.util.Observable {

    private boolean serverStatus;
    private boolean shouldSendData;
    private double timeElapsed; 
    private boolean serverStartedStatus;
    private boolean isServerError;
    private String logMessage;
    private static ServiceHelperModel serviceModel;

    public ServiceHelperModel() {
    	logMessage = "";
    }
    
    public static ServiceHelperModel getInstance(){
        if (serviceModel == null) {
            serviceModel = new ServiceHelperModel();
        }
        return serviceModel;
    }

    public void setServerStatus(boolean serverStatus) {
        this.serverStatus = serverStatus;
        notifyAllObservers();
    }

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

	public void notifyAllObservers() {
		this.setChanged();
        this.notifyObservers();
	}
}
