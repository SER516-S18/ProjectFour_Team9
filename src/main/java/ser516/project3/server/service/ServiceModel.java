package ser516.project3.server.service;

public class ServiceModel extends java.util.Observable {

    private boolean serverStatus;
    private static ServiceModel serviceModel;

    public static ServiceModel getInstance(){
        if (serviceModel == null) {
            serviceModel = new ServiceModel();
        }
        return serviceModel;
    }

    public void setServerStatus(boolean serverStatus) {
        this.serverStatus = serverStatus;
        this.setChanged();
        this.notifyObservers();
    }

    public boolean isServerStatus() {
        return serverStatus;
    }
}
