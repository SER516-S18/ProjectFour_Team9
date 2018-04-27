package ser516.project3.server.service;

import org.apache.log4j.Logger;
import ser516.project3.constants.ServerConstants;
import ser516.project3.server.controller.ServerController;
import ser516.project3.server.helper.ServerContainerThread;

import java.util.Observable;
import java.util.Observer;

/**
 * This is the Service class responsible for creating threads
 * for web socket and closing of these threads
 *
 * @author vsriva12
 */
public class ServerConnectionServiceImpl implements ServerConnectionServiceInterface, Observer {
    final static Logger logger = Logger.getLogger(ServerConnectionServiceImpl.class);
    Thread serverContainerThread;
    ServerContainerThread threadInstance;
    Observable observable;

    public ServerConnectionServiceImpl() {

    }

    public ServerConnectionServiceImpl(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }

    /**
     * Override Method responsible for creating threads
     * for web socket
     */
    @Override
    public void initServerEndpoint() {
        threadInstance = new ServerContainerThread(ServerConstants.MAIN_SERVER);
        serverContainerThread = new Thread(threadInstance);
        serverContainerThread.start();
        ServerController.getInstance().getConsoleController().getConsoleModel().
                logMessage(ServerConstants.SERVER_STARTED);
    }

    /**
     * Override Method responsible for closing any thread 
     * instance
     */
    @Override
    public void stopServerEndpoint() {
        if (threadInstance != null || serverContainerThread != null) {
            threadInstance.getServer().stop();
            ServerController.getInstance().getConsoleController().getConsoleModel().
                    logMessage(ServerConstants.SERVER_STOPPED);
            serverContainerThread.interrupt();
        }
        ServerController.getInstance().getTopController().getTopModel().setServerStarted(false);
        ServerController.getInstance().getTopController().getTopModel().
                setSendButtonEnabled(false);
        ServerController.getInstance().getTopController().getTopModel().
                setServerStartStopButtonText(ServerConstants.START_SERVER);
        ServerController.getInstance().getTopController().updateServerStartStopButtonText();
        ServerController.getInstance().getTopController().updateEnableDisableSendButton();
        ServerController.getInstance().getTopController().setBlinking(false);
    }


    @Override
    public void update(Observable serverStatus, Object observerObj) {
        ServiceModel serviceModel = ServiceModel.getInstance();
        if (serviceModel.isServerStatus()) {
            this.initServerEndpoint();
        } else {
            this.stopServerEndpoint();
        }

    }
}
