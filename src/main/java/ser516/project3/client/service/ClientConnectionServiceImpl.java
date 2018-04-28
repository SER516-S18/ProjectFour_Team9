package ser516.project3.client.service;

import org.apache.log4j.Logger;

import ser516.project3.client.thread.ClientConnectionThreadObservable;

/**
 * Class to register the client and connect it to the server. Also, updates the observers whenever
 * the data is received from server
 *
 * @author Adhiraj Tikku
 */
public class ClientConnectionServiceImpl implements ClientConnectionServiceInterface {
    final static Logger logger = Logger.getLogger(ClientConnectionServiceImpl.class);

    /**
     * Creates a client server connection
     *
     * @param ipAddress the ip address
     * @param port port number
     * @param endpoint endpoint string
     */
    @Override
    public void createClientConnection(final String ipAddress, final int port, final String endpoint) {
    	/*
        threadInstance = new ClientConnectionThread(ipAddress, port, endpoint);
        clientConnectionThread = new Thread(threadInstance);
        clientConnectionThread.start();*/
    	ClientConnectionThreadObservable.getInstance().setConnectionDetails(ipAddress, port, endpoint);
    	
    }

    /**
     * Stops the client server connection.
     */
    @Override
    public void stopClientConnection() {
    	/*try {
            if (threadInstance != null && threadInstance.getClientSession() != null)
                threadInstance.getClientSession().close();
            ClientControllerFactory.getInstance().getClientController().setConnectionStatus(false);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            logger.error("Error while stopping client end point::::" + e.getMessage().toString());
        }
        clientConnectionThread.interrupt();*/
    }
}