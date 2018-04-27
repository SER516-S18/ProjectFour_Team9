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
public class ServerConnectionServiceImpl implements ServerConnectionServiceInterface {
    final static Logger logger = Logger.getLogger(ServerConnectionServiceImpl.class);
    Thread serverContainerThread;
    ServerContainerThread threadInstance;
    Observable observable;

    public ServerConnectionServiceImpl() {

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
        
    }

    /**
     * Override Method responsible for closing any thread 
     * instance
     */
    @Override
    public boolean stopServerEndpoint() {
        if (threadInstance != null || serverContainerThread != null) {
            threadInstance.getServer().stop();
            serverContainerThread.interrupt();
            return true;
        }
        return false;
    }
}
